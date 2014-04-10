package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.util.Iterator;
import java.util.List;

/**
 * Makes an Integer array of a list
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class ArrayMaker {

	public static Integer[] convertIntegers(List<Integer> integers) {
		Log.i("ArrayMaker::convertIntegers() start");
		Integer[] result = new Integer[integers.size()];
		Iterator<Integer> iterator = integers.iterator();
		for (int i = 0; i < result.length; i++) {
			result[i] = iterator.next().intValue();
		}
		Log.i("ArrayMaker::convertIntegers() stop");
		return result;
	}
}
