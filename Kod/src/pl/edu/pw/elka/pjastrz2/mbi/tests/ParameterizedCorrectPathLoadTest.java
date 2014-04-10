package pl.edu.pw.elka.pjastrz2.mbi.tests;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;
import pl.edu.pw.elka.pjastrz2.mbi.utils.FileOperator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ParsingException;
import pl.edu.pw.elka.pjastrz2.mbi.utils.StructurePrinter;

/**
 * Multiple input files test with CORRECT content
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
@RunWith(Parameterized.class)
public class ParameterizedCorrectPathLoadTest {

	// Creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "pass1" }, { "pass2" },
				{ "pass3" }, { "pass4" }, { "pass5" }, { "pass6" } };
		return Arrays.asList(data);
	}

	private String filePath;

	public ParameterizedCorrectPathLoadTest(String testParameter) {
		this.filePath = testParameter;
	}

	@Test
	public void testLoad() {
		Log.i("ParameterizedCorrectPathLoadTest::testLoad() start");
		String prefix = "data/";
		String suffix = ".txt";
		try {
			EvidenceContainer evidenceCon = new EvidenceContainer();
			assertNotNull("Loading",
					evidenceCon = FileOperator.load(prefix + filePath + suffix));
			/* Just to calculate the time spent */
			Map<String, List<List<List<Integer>>>> result = evidenceCon.find();
			FileOperator.save(prefix + filePath + suffix,
					StructurePrinter.print(result));
		} catch (ParsingException e) {
			System.err.println(e.getMessage());
			Log.e("ParameterizedCorrectPathLoadTest::testLoad() stop");
			return;
		}
		Log.i("ParameterizedCorrectPathLoadTest::testLoad() stop");
	}

}
