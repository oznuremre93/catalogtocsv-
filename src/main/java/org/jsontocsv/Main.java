package org.jsontocsv;

import java.util.List;
import java.util.Map;

import org.jsontocsv.writer.CSVWriter;

public class Main {

	public static void main(String[] args) {

		if (args != null && args.length != 2) {
			System.out
					.println("JAVA -JAR catalogToCsv.JAR sourcefile.catalog  destinationfile.CSV ");
			System.out
					.println("file of catalog and file of jar should be same packages. ");
			return;
		}
		String inputFile = args[0];
		System.out.println("Catalog File: " + args[0]);
		String outputFile = args[1];
		System.out.println("Csv File: " + args[1]);
		System.out.println("Please see 'Finished!' and check your file. ");

		List<Map<String, String>> flatCatalog = null;
		try {
			flatCatalog = ReadMainCatalog.readCatalogFile(inputFile);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			CSVWriter.writeToFile(CSVWriter.getCSV(flatCatalog, ";"),
					outputFile);
			System.out.println("Writing finished-----" + "Writing finished");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}