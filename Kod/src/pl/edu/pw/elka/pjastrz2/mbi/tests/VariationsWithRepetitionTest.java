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
 * Unit test of Variations maker (n^k)
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class VariationsWithRepetitionTest {

	@Test
	public void doIt() {
		Log.i("VarationsWithRepetitionTest::doIt() start");
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		int k = 3;

		ICombinatoricsVector<Integer> originalVector = Factory
				.createVector(ArrayMaker.convertIntegers(l));

		Generator<Integer> gen = Factory
				.createPermutationWithRepetitionGenerator(originalVector, k);

		for (ICombinatoricsVector<Integer> perm : gen) {
			System.out.println(perm);
		}
		// Size checked. Content of generated set checked visually
		assertEquals("Variation size", (int) Math.pow(l.size(), k),
				gen.getNumberOfGeneratedObjects());
		Log.i("VarationsWithRepetitionTest::doIt() start");
	}
}
