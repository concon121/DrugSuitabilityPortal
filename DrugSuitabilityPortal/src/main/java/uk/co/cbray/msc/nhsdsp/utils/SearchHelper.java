package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Searchable;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;

public class SearchHelper {

	public static final Logger LOG = LoggerFactory
			.getLogger(SearchHelper.class);

	public static <T extends IEntity & Searchable> void search(
			DataAccessObject dao, SearchForm form, Model model, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {

		List<T> results = Collections.EMPTY_LIST;
		results = dao.search(clazz, form.getSearchString());
		LOG.debug("User Results found in search: " + results.size());
		
		for (T t : results) {
			if (t instanceof User) {
				User u = (User) t;
				u.setDisplayName(u.getForename() + " " + u.getSurname());
			} else if (t instanceof Drug) {
				Drug d = (Drug) t;
				d.setDisplayName(d.getName());
			} else if (t instanceof Illness) {
				Illness i = (Illness) t;
				i.setDisplayName(i.getName());
			} else if (t instanceof Effect) {
				Effect e = (Effect) t;
				e.setDisplayName(e.getName());
			}

		}
		if (clazz == User.class) {
			List<User> users = new ArrayList<User>();
			for (T t : results) {
				if (t instanceof User) {
					User u = (User) t;
					users.add(u);
				}
			}
			model.addAttribute("user", users);
		} else if (clazz == Drug.class) {
			List<Drug> drugs = new ArrayList<Drug>();
			for (T t : results) {
				if (t instanceof Drug) {
					Drug d = (Drug) t;
					drugs.add(d);
				}
			}
			model.addAttribute("drug", drugs);
		} else if (clazz == Illness.class) {
			List<Illness> illnesses = new ArrayList<Illness>();
			for (T t : results) {
				if (t instanceof Illness) {
					Illness i = (Illness) t;
					illnesses.add(i);
				}
			}
			model.addAttribute("illness", illnesses);
		} else if (clazz == Effect.class) {
			List<Effect> effects = new ArrayList<Effect>();
			for (T t : results) {
				if (t instanceof Effect) {
					Effect i = (Effect) t;
					effects.add(i);
				}
			}
			model.addAttribute("effect", effects);
		} else {
			model.addAttribute("error", "No instance");
		}

		Validator.validateIsEmpty(results, model);

	}

	public static <T extends IEntity & Searchable> void searchPatientsOnly(
			DataAccessObject dao, SearchForm form, Model model)
			throws InstantiationException, IllegalAccessException {

		search(dao, form, model, User.class);
		Map<String, Object> modelMap = model.asMap();

		for (Entry<String, Object> entry : modelMap.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("user")) {
				List<User> users = (List<User>) entry.getValue();
				List<User> toRemove = new ArrayList<User>();

				for (User u : users) {
					if (!u.getRoleName().equalsIgnoreCase("PATIENT")) {
						toRemove.add(u);
					}
				}

				users.removeAll(toRemove);
				model.addAttribute("user", users);
			}
		}

	}

}
