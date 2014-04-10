package pl.edu.pw.elka.pjastrz2.mbi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import pl.edu.pw.elka.pjastrz2.mbi.utils.FileOperator;
import pl.edu.pw.elka.pjastrz2.mbi.utils.Log;
import pl.edu.pw.elka.pjastrz2.mbi.utils.ParsingException;
import pl.edu.pw.elka.pjastrz2.mbi.utils.StructurePrinter;

/**
 * Main class of the program
 * 
 * @author piotr.jastrzebski@gmail.com
 * @author marcin@nazimek.pl
 * 
 */
public class Main {
	private static String path = "";

	public static void main(String[] args) {
		Log.i("Main::main start");

		System.out.println("Type the file path: ");
		InputStreamReader converter = new InputStreamReader(System.in);
		try {
			BufferedReader in = new BufferedReader(converter);
			path = in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EvidenceContainer evidenceCon = new EvidenceContainer();
		try {
			/**
			 * LOAD IT!
			 */
			evidenceCon = FileOperator.load(path);

			/**
			 * DO IT!
			 */
			Map<String, List<List<List<Integer>>>> result = evidenceCon.find();

			/**
			 * PRINT IT!
			 */
			System.out.print(StructurePrinter.print(result));

			/**
			 * SAVE IT!
			 */
			FileOperator.save(path, StructurePrinter.print(result));
		} catch (ParsingException e) {
			System.err.println(e.getMessage());
			Log.e("Main::main stop");
			return;
		}

		Log.i("Main::main stop");
	}
}
