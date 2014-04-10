package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.util.Comparator;
import java.util.List;

/**
 * List of Lists of Integers Comparator
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class ListOfListOfIntegersComparator implements
		Comparator<List<List<Integer>>> {

	@Override
	public int compare(List<List<Integer>> p1, List<List<Integer>> p2) {
		// Cannot have different size, so size comparison skipped.
		int result;
		Comparator<List<Integer>> tmpComparator = new ListOfIntegersComparator();
		for (int i = 0; i < p1.size(); ++i) {
			result = tmpComparator.compare(p1.get(i), p2.get(i));
			if(result!=0){
				return result;
			}
		}
		return 0;
	}
}