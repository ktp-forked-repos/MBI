package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.util.List;
import java.util.Map;

/**
 * Print final results in a nice way
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class StructurePrinter {

	/**
	 * 
	 * @param structure
	 *            Result of MBI algorithm
	 * @return String with nicely formatted content of structure
	 */
	public static String print(Map<String, List<List<List<Integer>>>> structure) {
		Log.i("StructurePrinter::print() start");
		String result = "";
		for (String key : structure.keySet()) {
			result += structure.get(key).size()
					+ " possible combinations of variants for marker '" + key
					+ "'\n";
			for (List<List<Integer>> line : structure.get(key)) {
				for (List<Integer> elements : line) {
					for (int i = 0; i < elements.size(); ++i) {
						if (i != 0) {
							result += ",";
						}
						result += elements.get(i);
					}
					result += "\t";
				}
				result += "\n";
			}
			result += "\n";
		}
		Log.i("StructurePrinter::print() stop");
		return result;
	}

}
