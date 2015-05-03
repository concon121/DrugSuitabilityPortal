package uk.co.cbray.msc.nhsdsp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugIllness;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidParameterException;
import uk.co.cbray.msc.nhsdsp.forms.IncidentForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/incident")
public class IncidentController {

	private static final Logger LOG = LoggerFactory.getLogger(IncidentController.class);
	
	@Autowired
	private DataAccessObject dao;

	@ModelAttribute(value = "form")
	public IncidentForm getIncidentForm() {
		return new IncidentForm();
	}

	private void populateModel(Model model, HttpSession session) {
		Set<Drug> availableDrugs = new HashSet<Drug>();
		Set<Effect> availableEffects = new HashSet<Effect>();

		// Retrieve all the drugs attributed for a given user
		BigDecimal userId = new BigDecimal(
				(Integer) session.getAttribute("userId"));
		// Get all diagnosis for user
		String jpqlDiagnosis = "from Diagnosis d where d.userId = ?";
		List<Diagnosis> diagnoses = getDao().executeJpqlQueryWithParameters(
				jpqlDiagnosis, Diagnosis.class, userId.intValue());
		LOG.debug("Diagnoses found: " + diagnoses.size());
		// Get all drugs for diagnosis.illnessId
		String jpqlDrugs = "from DrugIllness di where di.illnessId = ?";
		for (Diagnosis diagnosis : diagnoses) {
			LOG.debug("IllnessID: " + diagnosis.getIllness());
			List<DrugIllness> drugsForIllness = getDao()
					.executeJpqlQueryWithParameters(jpqlDrugs,
							DrugIllness.class, diagnosis.getIllness());
			LOG.debug("Drugs for Illness found: " + drugsForIllness.size());
			List<BigDecimal> drugIds = new ArrayList<BigDecimal>();
			for (DrugIllness di : drugsForIllness) {
				drugIds.add(new BigDecimal(di.getDrugId()));
			}
			LOG.debug("Drug Ids found: " + drugIds.size());
			availableDrugs.addAll(getDao().findAll(drugIds, Drug.class));
		}
		
		LOG.debug("Available Drugs: " + availableDrugs.size());

		List<Effect> effects = getDao().executeNamedQuery("Effect.findAll",
				Effect.class);
		availableEffects.addAll(effects);

		model.addAttribute("availableDrugs", availableDrugs);
		model.addAttribute("availableEffects", availableEffects);
	}

	@RequestMapping(value = "/new")
	public String newIncident(Model model, HttpSession session) {
		populateModel(model, session);
		return PageEnum.NEW_INCIDENT.getName();
	}

	@RequestMapping(value = "/new/persist")
	public String persistIncident(IncidentForm form, Model model,
			HttpSession session) {

		List<String> errorMessages = Validator.validate(form);
		if (errorMessages.size() > 0) {
			populateModel(model, session);
			model.addAttribute("formContent", form);
			model.addAttribute("error", errorMessages);
			return PageEnum.NEW_INCIDENT.getName();
		} else {
			try {
				Incident incident = Converter.convert(form, getDao(), session);
				getDao().create(incident);
				model.addAttribute("success", "Successfully recorded incident.");
				return PageEnum.HOME.getName();
			} catch (InvalidParameterException ex) {
				model.addAttribute("error", ex.getMessage());
				model.addAttribute("formContent", form);
				populateModel(model, session);
				return PageEnum.NEW_INCIDENT.getName();
			}
		}

	}
	
	@RequestMapping(value="view")
	public String viewIncidents(Model model, HttpSession session) { 
		
		try {
			BigDecimal userId = new BigDecimal((Integer) session.getAttribute("userId"));
			String jpql = "from Incident i where i.user.id = ?";
			List<Incident> incidents = getDao().executeJpqlQueryWithParameters(jpql, Incident.class, userId);
			List<IncidentForm> formItems = Converter.convert(incidents, IncidentForm.class);
			
			List<String> errorMessages = Validator.validate(incidents, formItems);
			if (errorMessages.isEmpty()) {
				model.addAttribute("items", formItems);
			} else {
				model.addAttribute("error", errorMessages);
			}
			return PageEnum.VIEW_INCIDENTS.getName();

		} catch (InstantiationException instantiation) {
			LOG.error("InstantiationException during conversion from Incident.class to IncidentForm.class in IncidentController.viewIncidents", instantiation);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (IllegalAccessException access) {
			LOG.error("IllegalAccessException during conversion from Incident.class to IncidentForm.class in IncidentController.viewIncidents", access);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (InvalidEntityConversionTypeException conversion) {
			LOG.error("InvalidEntityConversionTypeException during conversion from Incident.class to IncidentForm.class in IncidentController.viewIncidents", conversion);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (Exception e) {
			LOG.error("Unknown Exception during conversion from Incident.class to IncidentForm.class in IncidentController.viewIncidents", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		}
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
