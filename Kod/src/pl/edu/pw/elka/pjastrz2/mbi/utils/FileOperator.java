package pl.edu.pw.elka.pjastrz2.mbi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pl.edu.pw.elka.pjastrz2.mbi.Evidence;
import pl.edu.pw.elka.pjastrz2.mbi.EvidenceContainer;

/**
 * Class providing read/write file methods
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class FileOperator {

	/**
	 * Loading and parsing method. Spaghetti code below. Watch out!
	 * 
	 * @param filePath
	 *            The path data should be read from.
	 * @return Container with evidences
	 */
	public static EvidenceContainer load(String filePath)
			throws ParsingException {
		Log.i("FileOperator::load() start");
		BufferedReader br = null;
		EvidenceContainer pplCont = new EvidenceContainer();
		StringTokenizer semicolonTok = null;
		StringTokenizer comaTok = null;
		StringTokenizer equTok = null;

		try {
			String sCurrentLine = "";
			br = new BufferedReader(new FileReader(filePath));

			// First line - single number = number of people involved
			if ((sCurrentLine = br.readLine()) != null) {
				pplCont.setPeopleNum(Integer.parseInt(sCurrentLine));
			}

			// Second and other lines - mixture found and suspects
			int mixture = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				Evidence tmpEvidence = new Evidence(mixture == 0 ? true : false);
				pplCont.setReadPeopleNum(pplCont.getReadPeopleNum() + 1);
				sCurrentLine = sCurrentLine.replaceAll("\\s", ""); // removes
																	// all
																	// whitespaces
				// Divide string looking for semicolon
				semicolonTok = new StringTokenizer(sCurrentLine, ";");
				while (semicolonTok.hasMoreElements()) {
					String token = semicolonTok.nextToken();
					// Divide string looking for equal sign
					equTok = new StringTokenizer(token, "=");
					String name = equTok.nextToken();
					// Divide string looking for coma
					comaTok = new StringTokenizer(equTok.nextToken(), ",");
					List<Integer> tmpList = new ArrayList<>();
					while (comaTok.hasMoreElements()) {
						String singleNumber = comaTok.nextToken();
						tmpList.add(Integer.parseInt(singleNumber));
					}
					tmpEvidence.put(name, tmpList);
				}
				if (mixture == 0) {
					for (String key : tmpEvidence.keySet()) {
						pplCont.addMixtureSize(tmpEvidence.get(key).size());
					}
					pplCont.setMixture(tmpEvidence);
				} else {
					if (mixture == 1) {
						for (String key : tmpEvidence.keySet()) {
							pplCont.addSize(tmpEvidence.get(key).size());
						}
						/*
						 * Checks if mixture has the same size as suspects
						 * markers
						 */
						if (pplCont.getMixture().size() == tmpEvidence.size()) {
							pplCont.add(tmpEvidence);
						} else {
							throw new ParsingException(
									"Error while parsing - size of mixture and sample do not match!");
						}

					} else {
						/* Checks if it has correct size */
						if (tmpEvidence.checkSize(pplCont.getCorrectSize())) {
							pplCont.add(tmpEvidence);
						} else {
							throw new ParsingException(
									"Error while parsing - some sizes do not match!");
						}
					}
				}
				++mixture;
			}

		} catch (IOException e) {
			throw new ParsingException("Error while parsing - no file found!");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return null;
			}

		}
		Log.i("FileOperator::load() stop");
		return pplCont;
	}

	/**
	 * Save the results to the file
	 * 
	 * @param filePath
	 *            The path results should be written to.
	 * @return <code>true</code> when everything is ok. <code>false</code> if
	 *         exception caught.
	 */
	public static boolean save(String filePath, String content) {

		int i = filePath.lastIndexOf('.');

		String path = filePath.substring(0, i);
		String extension = filePath.substring(i + 1);

		BufferedWriter bw = null;
		Log.i("FileOperator::save() start");
		try {
			bw = new BufferedWriter(
					new FileWriter(path + "_result." + extension));
			bw.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		System.out.println("Results saved as: " + path + "_result." + extension);
		Log.i("FileOperator::save() stop");
		return true;
	}
}
