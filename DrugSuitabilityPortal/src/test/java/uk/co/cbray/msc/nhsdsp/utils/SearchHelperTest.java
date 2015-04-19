package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidParameterException;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.test.utils.InvalidSearchableEntity;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class SearchHelperTest {
	
	@Mock
	private DataAccessObject dao;
	private SearchForm form;
	private Model model;
	
	private List<User> users;
	private List<Drug> drugs;
	private List<Illness> illnesses;
	
	@Before
	public void setUp() throws InstantiationException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
		
		users = TestFactory.getUserListInstance();
		drugs = TestFactory.getDrugListInstance();
		illnesses = TestFactory.getIllnessListInstance();
		
		form = new SearchForm();
		form.setSearchString("any random string");
		
		model = new ExtendedModelMap();
		
		when(dao.search(eq(User.class), anyString())).thenReturn(users);
		when(dao.search(eq(Drug.class), anyString())).thenReturn(drugs);
		when(dao.search(eq(Illness.class), anyString())).thenReturn(illnesses);
	}
	
	@Test
	public void testSearchAddsListOfUsersToModelWhenSearchingForUsers() throws InstantiationException, IllegalAccessException {
		
		form.setEntity("user");
		SearchHelper.search(dao, form, model, User.class);
		
		assertTrue(model.containsAttribute("user"));
		assertFalse(model.containsAttribute("drug"));
		assertFalse(model.containsAttribute("illness"));
		
		Map<String, Object> modelMap = model.asMap();
		for (Entry<String, Object> entry : modelMap.entrySet()) {
			String key = entry.getKey();
			if(key.equalsIgnoreCase("user")) {
				List<User> value = (List<User>)entry.getValue();
				assertFalse(value.isEmpty());
			}
		}
		
	}
	
	@Test
	public void testSearchAddsListOfDrugsToModelWhenSearchingForDrugs() throws InstantiationException, IllegalAccessException {
		form.setEntity("drug");
		SearchHelper.search(dao, form, model, Drug.class);
		
		assertFalse(model.containsAttribute("user"));
		assertTrue(model.containsAttribute("drug"));
		assertFalse(model.containsAttribute("illness"));
		
		Map<String, Object> modelMap = model.asMap();
		for (Entry<String, Object> entry : modelMap.entrySet()) {
			String key = entry.getKey();
			if(key.equalsIgnoreCase("drug")) {
				List<Drug> value = (List<Drug>)entry.getValue();
				assertFalse(value.isEmpty());
			}
		}
	}
	
	@Test
	public void testSearchAddsListOfIllnessesToModelWhenSearchingForIlnesses() throws InstantiationException, IllegalAccessException {
		form.setEntity("illness");
		SearchHelper.search(dao, form, model, Illness.class);
		
		assertFalse(model.containsAttribute("user"));
		assertFalse(model.containsAttribute("drug"));
		assertTrue(model.containsAttribute("illness"));
		
		Map<String, Object> modelMap = model.asMap();
		for (Entry<String, Object> entry : modelMap.entrySet()) {
			String key = entry.getKey();
			if(key.equalsIgnoreCase("illness")) {
				List<Illness> value = (List<Illness>)entry.getValue();
				assertFalse(value.isEmpty());
			}
		}
	}
	
	@Test
	public void testSearchAddsErrorMessageToTheModelWhenSearchingForAnInvalidEntity() throws InstantiationException, IllegalAccessException {
		form.setEntity("invalid");
		SearchHelper.search(dao, form, model, InvalidSearchableEntity.class);
		
		assertTrue(model.containsAttribute("error"));
	}
	
	@Test
	public void testSearchAddsErrorMessageToTheModelWhenNoResultsAreFound() throws InstantiationException, IllegalAccessException {
		
		when(dao.search(eq(User.class), anyString())).thenReturn(Collections.EMPTY_LIST);
		
		form.setEntity("user");
		SearchHelper.search(dao, form, model, User.class);
		
		assertTrue(model.containsAttribute("error"));
		
	}
	
	@Test
	public void testSearchPatientsOnlyRemovesAllNonPatients() throws InstantiationException, IllegalAccessException, InvalidParameterException {
		
		form.setEntity("user");
		SearchHelper.searchPatientsOnly(dao, form, model);
		
		assertTrue(model.containsAttribute("user"));
		assertFalse(model.containsAttribute("drug"));
		assertFalse(model.containsAttribute("illness"));
		
		Map<String, Object> modelMap = model.asMap();
		for (Entry<String, Object> entry : modelMap.entrySet()) {
			String key = entry.getKey();
			if(key.equalsIgnoreCase("user")) {
				List<User> value = (List<User>)entry.getValue();
				assertTrue(value.isEmpty());
			}
		}
		
	}
	
}

