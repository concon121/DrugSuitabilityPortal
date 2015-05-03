package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;

public class SearchControllerTest {
	
	@Mock
	private DataAccessObject dao;
	
	private SearchController controller;
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		model = new ExtendedModelMap();
		
		controller = new SearchController();
		controller.setDao(dao);
	}
	
	@Test
	public void testSearchMethodAddsErrorMessagesToModelWhenValidationFails() throws InstantiationException, IllegalAccessException {
		
		SearchForm form = new SearchForm();
		form.setEntity(null);
		form.setSearchString("a search string");
		
		controller.search(form, model);
		
		boolean containsError = model.containsAttribute("error");
		assertTrue(containsError);
		
	}
	
	@Test
	public void testIndexDataMethodCallsDaoMethodIndexData() throws InterruptedException {
		
		controller.indexData(model);
		
		verify(dao).indexData();
		
	}

}
