package pl.edu.pw.elka.pjastrz2.mbi.tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;

/**
 * Unit test designed to estimate speed of solution based on different set sizes
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class SpeedTest {
	private static final int SIZE_OF_ALPHABET = 6;
	private static final int NUMBER_OF_SUSPECTS = 2;
	private long startTime;

	/**
	 * TEST CONSTRAINTS:
	 * sizeof(alphabet) >= sizeof(mandatorySize)
	 * sizeof(marker)*numberOfSuspects >= sizeof(mandatorySize)
	 * numberOfSuspects >= 1
	 * sizeof(marker) >= 1
	 * sizeof(mandatorySize) >= 0
	 * sizeof(alphabet) >= sizeof(marker)
	 * 
	 * MAPPING:
	 * a = sizeof(alphabet): <1; +INF)
	 * b = sizeof(mandatorySize): <0; a>
	 * c = sizeof(marker): <1; a>
	 * d = numberOfSuspects: <1; +INF)
	 */
	@Test
	public void test() {
		fail("Should not be checked - just performance test");
		for (int a = 1; a <= SIZE_OF_ALPHABET; ++a) {
			for (int b = 0; b <= a; ++b) {
				for (int c = 1; c <= a; ++c) {
					for (int d = 1; d <= NUMBER_OF_SUSPECTS; ++d) {
						if (c * d >= b) {
							// To ensure all structures are empty
							EvidenceContainer testContainer = new EvidenceContainer();

							// due to peopleNum-readPeopleNum used in a
							// generatePerms
							testContainer.setPeopleNum(d);
							testContainer.setReadPeopleNum(0);

							List<Integer> alphabet = new ArrayList<>();
							for (int x = 1; x <= a; ++x) {
								alphabet.add(x);
							}
							List<Integer> mandatorySet = alphabet.subList(0, b);

							startTime = System.currentTimeMillis();
							testContainer.generatePerms(mandatorySet, alphabet,
									c);
							System.out.println(a + "," + b + "," + c + "," + d
									+ ","
									+ (System.currentTimeMillis() - startTime));
						}
					}
				}
			}
		}
	}
}
