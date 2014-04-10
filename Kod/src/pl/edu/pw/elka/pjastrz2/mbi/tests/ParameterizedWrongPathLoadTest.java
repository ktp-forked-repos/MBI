package pl.edu.pw.elka.pjastrz2.mbi.tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;
import pl.edu.pw.elka.pjastrz2.mbi.utils.FileOperator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ParsingException;

/**
 * Multiple input files test with INCORRECT content
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
@RunWith(Parameterized.class)
public class ParameterizedWrongPathLoadTest {

	// Creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "fail1" }, { "fail2" }, { "fail3" } };
		return Arrays.asList(data);
	}

	private String filePath;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	public ParameterizedWrongPathLoadTest(String testParameter) {
		this.filePath = testParameter;
	}

	@Test(expected = ParsingException.class)
	public void testLoad() throws ParsingException {
		Log.i("ParameterizedWrongPathLoadTest::testLoad() start");
		String prefix = "data/";
		String suffix = ".txt";
		EvidenceContainer evidenceCon = new EvidenceContainer();
		evidenceCon = FileOperator.load(prefix + filePath + suffix);
		evidenceCon.find();

		Log.i("ParameterizedWrongPathLoadTest::testLoad() stop");
	}
}
