package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;

/**
 * Thread used while generating permutations
 * 
 * @author piotr.jastrzebski@gmail.com 
 * @author marcin@nazimek.pl
 * 
 */
public class PermThread extends Thread {

	private List<Integer> _ms;
	private List<Integer> _al;
	private EvidenceContainer _ec;
	private String _key;
	private List<List<List<Integer>>> result;

	public PermThread(List<Integer> ms, List<Integer> al, EvidenceContainer ec,
			String key) {
		_ms = ms;
		_al = al;
		_ec = ec;
		_key = key;
		result = new ArrayList<>();
	}

	public String getKey() {
		return _key;
	}

	public List<List<List<Integer>>> getResult() {
		return result;
	}

	public void run() {
		Log.d("Thread " + _key + " has been started");
		result = _ec.generatePerms(_ms, _al, _ec.get(0).get(_key).size());
		Log.d("Thread " + _key + " has been stopped");
	}
}