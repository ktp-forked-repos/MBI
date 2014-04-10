package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.util.Comparator;
import java.util.List;

/**
 * List of Integers Comparator
 * 
 * @author piotr.jastrzebski@gmail.com 
 * @author marcin@nazimek.pl
 * 
 */
public class ListOfIntegersComparator implements Comparator<List<Integer>> {

	@Override
	public int compare(List<Integer> p1, List<Integer> p2) {
		// Cannot have different size, so size comparison skipped.
		int p1sum = 0, p2sum = 0;
		for (int i = 0; i < p1.size(); ++i) {
			p1sum += p1.get(i) * Math.pow(10, p1.size() - 1 - i);
			p2sum += p2.get(i) * Math.pow(10, p1.size() - 1 - i);
		}
		if (p1sum > p2sum)
			return 1;
		if (p1sum < p2sum)
			return -1;
		return 0;

	}
}