package pl.edu.pw.elka.pjastrz2.mbi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Describes single evidence. If one is a victim and one's genetic trace.
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
@SuppressWarnings("serial")
public class Evidence extends HashMap<String, List<Integer>> {
	private boolean isMixture;

	public Evidence() {
		this.isMixture = false;
	}

	public Evidence(boolean isMixture) {
		this.isMixture = isMixture;
	}

	/**
	 * Creates a deep copy of evidence.
	 * 
	 * @param evidence
	 *            Evidence to be copied
	 */
	public Evidence(Evidence evidence) {
		this.isMixture = evidence.isMixture;
		for (String key : evidence.keySet()) {
			List<Integer> tmpList = new ArrayList<>();
			for (Integer val : evidence.get(key)) {
				tmpList.add(val);
			}
			this.put(key, tmpList);
		}
	}

	/**
	 * Checks if size of all lists is same as given
	 */
	public boolean checkSize(List<Integer> sizes) {
		if (sizes.size() != this.size()) {
			return false;
		} else {
			int i = 0;
			for (String key : this.keySet()) {
				if (this.get(key).size() != sizes.get(i++)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isMixture() {
		return isMixture;
	}

	public void setMixture(boolean isMixture) {
		this.isMixture = isMixture;
	}

}
