package pl.edu.pw.elka.pjastrz2.mbi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import pl.edu.pw.elka.pjastrz2.mbi.utils.ArrayMaker;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ListOfIntegersComparator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ListOfListOfIntegersComparator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ParsingException;
import pl.edu.pw.elka.pjastrz2.mbi.utils.PermThread;

/**
 * Container designed to store evidences collection.
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
@SuppressWarnings("serial")
public class EvidenceContainer extends ArrayList<Evidence> {
	private Evidence mixture;

	private List<Integer> correctSize;

	private List<Integer> mixtureCorrectSize;

	/**
	 * Number of people involved.
	 */
	private int peopleNum;
	/**
	 * Number of people known.
	 */
	private int readPeopleNum;

	public EvidenceContainer() {
		super();
		this.peopleNum = 0;
		this.readPeopleNum = -1;
		this.correctSize = new ArrayList<>();
		this.mixtureCorrectSize = new ArrayList<>();
	}

	public void addMixtureSize(int size) {
		this.mixtureCorrectSize.add(size);
	}

	public void addSize(int size) {
		this.correctSize.add(size);
	}

	/**
	 * Creates a mandatory set of values which have to be in suspect profile
	 * 
	 * @return Needed values in a form of Evidence
	 */
	public Evidence createMandatorySet() {
		Log.i("EvidenceContainer::createMandatorySet() start");
		Evidence deepCopyOfmixture = new Evidence(mixture);

		for (int i = 0; i < this.size(); ++i) {
			for (String key : mixture.keySet()) {
				deepCopyOfmixture.get(key).removeAll(this.get(i).get(key));
			}
		}
		Log.i("EvidenceContainer::createMandatorySet() stop");
		return deepCopyOfmixture;
	}

	/**
	 * Main function for suspect finder.
	 * 
	 * @return All sets of markers which when combined include suspect profile.
	 */
	public Map<String, List<List<List<Integer>>>> find()
			throws ParsingException {
		Log.i("EvidenceContainer::find() start");
		Map<String, List<List<List<Integer>>>> result = new HashMap<>();

		HashMap<String, List<Integer>> mandatorySet = this.createMandatorySet();
		/* Check if mandatory set is not to big for number of suspects */
		int i = 0;
		for (String key : mandatorySet.keySet()) {
			if (mandatorySet.get(key).size() > (peopleNum - readPeopleNum)
					* correctSize.get(i++)) {
				throw new ParsingException(
						"Error while parsing - mandatory set is to big to be possible!");
			}
		}

		List<PermThread> threadsList = new ArrayList<>();
		for (String key : mixture.keySet()) {
			List<Integer> al = new ArrayList<>(mixture.get(key));
			List<Integer> mS = new ArrayList<>(mandatorySet.get(key));
			PermThread t = new PermThread(mS, al, this, key);
			t.start();
			threadsList.add(t);
		}

		for (PermThread t : threadsList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			result.put(t.getKey(), t.getResult());
		}
		Log.i("EvidenceContainer::find() stop");
		return result;
	}

	/**
	 * Generates all permutations
	 * 
	 * @param mandatorySet
	 *            Values which have to be included
	 * @param alphabet
	 *            Values which are used to describe all markers
	 * @param size
	 *            Size of permutation (marker)
	 * @return All permutations
	 */
	public List<List<List<Integer>>> generatePerms(
			final List<Integer> mandatorySet, List<Integer> alphabet, int size) {
		Log.i("EvidenceContainer::generatePerms() start");

		ICombinatoricsVector<Integer> originalVector = Factory
				.createVector(ArrayMaker.convertIntegers(alphabet));
		Generator<Integer> generator = Factory
				.createPermutationWithRepetitionGenerator(
						originalVector,
						Math.abs(size * (peopleNum - readPeopleNum)
								- mandatorySet.size()));

		Set<List<List<Integer>>> result = new HashSet<List<List<Integer>>>();

		for (ICombinatoricsVector<Integer> perm : generator) {
			perm.getVector().addAll(mandatorySet);

			ICombinatoricsVector<Integer> originalVector2 = Factory
					.createVector(ArrayMaker.convertIntegers(perm.getVector()));
			Generator<Integer> generator2 = Factory
					.createPermutationGenerator(originalVector2);

			for (ICombinatoricsVector<Integer> perma : generator2) {
				List<List<Integer>> tmpResult = new ArrayList<>();
				for (int i = 0; i < perma.getSize(); i += size) {
					Collections.sort(perma.getVector().subList(i, i + size));
					tmpResult.add(perma.getVector().subList(i, i + size));

				}
				Collections.sort(tmpResult, new ListOfIntegersComparator());
				result.add(tmpResult);
			}
		}
		List<List<List<Integer>>> finalResult = new ArrayList<>(result);
		Collections.sort(finalResult, new ListOfListOfIntegersComparator());
		Log.i("EvidenceContainer::generatePerms() stop");

		return finalResult;
	}

	public List<Integer> getCorrectSize() {
		return correctSize;
	}

	public Evidence getMixture() {
		return mixture;
	}

	public List<Integer> getMixtureCorrectSize() {
		return mixtureCorrectSize;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public int getReadPeopleNum() {
		return readPeopleNum;
	}

	public void setCorrectSize(List<Integer> correctSize) {
		this.correctSize = correctSize;
	}

	public void setMixture(Evidence mixture) {
		this.mixture = mixture;
	}

	public void setMixtureCorrectSize(List<Integer> mixtureCorrectSize) {
		this.mixtureCorrectSize = mixtureCorrectSize;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public void setReadPeopleNum(int readPeopleNum) {
		this.readPeopleNum = readPeopleNum;
	}

}
