package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugAllergy;
import uk.co.cbray.msc.nhsdsp.entity.DrugEffect;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;
import uk.co.cbray.msc.nhsdsp.utils.Converter;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value={Converter.class})
public class DrugRepositoryTest {
	
	@Mock
	private DataAccessObject dao;
	@Mock
	private EffectRepository effectRepo;
	private DrugRepository repo;
	
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Converter.class);
		MockitoAnnotations.initMocks(this);
		repo = new DrugRepository();
		repo.setDao(dao);
		repo.setEffectRepo(effectRepo);
	}
	
	
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		Drug d = TestFactory.getDrugInstance();
		
		repo.create(d);
		
		verify(dao).create(d);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		Drug d = TestFactory.getDrugInstance();
		
		repo.update(d);
			
		verify(dao).update(d, Drug.class);
	}
	
	@Test
	public void testFindByIdMethodCallsDaoFindMethod() {
		BigDecimal id = new BigDecimal(1);
		
		repo.findById(id);
		
		verify(dao).find(id, Drug.class);
	}
	
	@Test
	public void testCreateNewDrugMethodAlsoAddsAllergiesAndEffectsAsWellAsTheNewDrug() {

		NewDrugForm form = TestFactory.getNewDrugFormInstance();

		Mockito.when(effectRepo.findByName(anyString())).thenReturn(TestFactory.getEffectInstance());
		Mockito.when(Converter.convert(form)).thenReturn(TestFactory.getDrugInstance());
		
		repo.createNewDrug(form);
		
		verify(dao).create((Drug)anyObject());
		verify(dao).updateAll(anyListOf(DrugAllergy.class), eq(DrugAllergy.class));
		verify(dao).createAll(anySetOf(DrugEffect.class));
	}
	

}
