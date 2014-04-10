package pl.edu.pw.elka.pjastrz2.mbi.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import pl.edu.pw.elka.pjastrz2.mbi.utils.ArrayMaker;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;

/**
 * Unit test of Combinations maker (k+n-1 choose k)
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class CombinationsWithRepetitionsTest {

	@Test
	public void doIt() {
		Log.i("CombinationsWithRepetitionsTest::doIt start");
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		int k = 3;

		ICombinatoricsVector<Integer> originalVector = Factory
				.createVector(ArrayMaker.convertIntegers(l));

		Generator<Integer> gen = Factory.createMultiCombinationGenerator(
				originalVector, k);

		for (ICombinatoricsVector<Integer> perm : gen) {
			System.out.println(perm);
		}
		// Size checked.  Content of generated set checked visually
		assertEquals("Variation size", newton(k + l.size() - 1, k),
				gen.getNumberOfGeneratedObjects());
		Log.i("CombinationsWithRepetitionsTest::doIt stop");
	}

	private int newton(int n, int k) {
		int result = 1;

		if ((k < 0) || (k > n)) {
			return 0;
		}

		if (k > n - k) {
			k = n - k;
		}

		for (int i = 1; i <= k; ++i) {
			result = result * (n - i + 1) / i;
		}

		return result;
	}
}
