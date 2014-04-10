package pl.edu.pw.elka.pjastrz2.mbi.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.edu.pw.elka.pjastrz2.mbi.Evidence;
import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;
import pl.edu.pw.elka.pjastrz2.mbi.utils.FileOperator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ParsingException;

/**
 * Unit test of EvidenceContainer
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class EvidenceContainerTest {

	/**
	 * Unit test checking proper subsets/permutations generating
	 */
	@SuppressWarnings("serial")
	@Test
	public void testGeneratePerms() {
		Log.i("EvidenceContainerTest::testGeneratePerms() start");
		ArrayList<Integer> alphabet = new ArrayList<Integer>() {
			{
				add(1);
				add(2);
				add(3);
				add(4);
			}
		};
		ArrayList<Integer> mandatorySet = new ArrayList<Integer>() {
			{
				add(2);
				add(3);
				add(4);
			}
		};
		int size = 2;
		EvidenceContainer eC = new EvidenceContainer();
		eC.setReadPeopleNum(2);
		eC.setPeopleNum(3);
		List<List<List<Integer>>> actual = eC.generatePerms(mandatorySet,
				alphabet, size);

		List<List<List<Integer>>> expected = new ArrayList<>(); // Inception!
		// 12,34
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(1);
						add(2);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(3);
						add(4);
					}
				});
			}
		});
		// 13,24
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(1);
						add(3);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(4);
					}
				});
			}
		});
		// 14,23
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(1);
						add(4);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(3);
					}
				});
			}
		});
		// 22,34
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(2);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(3);
						add(4);
					}
				});
			}
		});
		// 23,24
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(3);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(4);
					}
				});
			}
		});
		// 23,34
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(3);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(3);
						add(4);
					}
				});
			}
		});
		// 23,44
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(3);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(4);
						add(4);
					}
				});
			}
		});
		// 24,33
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(4);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(3);
						add(3);
					}
				});
			}
		});
		// 24,34
		expected.add(new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>() {
					{
						add(2);
						add(4);
					}
				});
				add(new ArrayList<Integer>() {
					{
						add(3);
						add(4);
					}
				});
			}
		});
		assertEquals(expected, actual);
		Log.i("EvidenceContainerTest::testGeneratePerms() stop");
	}

	/**
	 * Unit test checking generating set of values which have to be found in the
	 * genetic profile of the suspect
	 */
	@SuppressWarnings("serial")
	@Test
	public void testGetMandatorySet() {
		Log.i("EvidenceContainerTest::testGetMandatorySet() start");
		try {
			EvidenceContainer evidenceCont = FileOperator
					.load("data/pass2.txt");
			Evidence evidence = evidenceCont.createMandatorySet();
			Evidence evidenceToCompare = new Evidence(false);

			evidenceToCompare.put("A", new ArrayList<Integer>() {
				{
					add(2);
					add(3);
					add(4);
				}
			});
			evidenceToCompare.put("B", new ArrayList<Integer>() {
				{
					add(3);
				}
			});
			evidenceToCompare.put("C", new ArrayList<Integer>() {
				{
					add(2);
				}
			});

			assertEquals("Mixture bool", true, evidence.isMixture());
			assertEquals("HashMap size", 3, evidence.size());
			for (String key : evidenceToCompare.keySet()) {
				assertEquals(evidenceToCompare.get(key), evidence.get(key));
			}
		} catch (ParsingException e) {
			e.printStackTrace();
		}
		Log.i("EvidenceContainerTest::testGetMandatorySet() stop");
	}
}
