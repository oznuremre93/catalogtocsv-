package org.jsontocsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadMainCatalog {

	public static List<Map<String, String>> readCatalogFile(String inputFile) {
		FileInputStream fInputStream = null;
		BufferedReader in = null;
		int counter = 0;
		List<Map<String, String>> flatCatalog = null;
		try {
			final File file = new File(inputFile);
			if (!file.exists()) {

				return null;
			}
			fInputStream = new FileInputStream(file);
			in = new BufferedReader(
					new InputStreamReader(fInputStream, "UTF-8"));
			boolean write = false;
			boolean _urunBilgi = false;
			String line = null;
			int satirNo = 0;
			int limit = 30000;

			flatCatalog = new ArrayList<Map<String, String>>();
			HashMap<String, String> _product = new HashMap<String, String>();
			while ((line = in.readLine()) != null) {
				satirNo++;

				String _okunanSatir = line.trim();
				if (_okunanSatir != null && _okunanSatir.length() > 0) {
					if (_okunanSatir.contains("product")
							&& _okunanSatir.contains("{")) {
						_urunBilgi = true;
						counter++;
						_product = new HashMap<String, String>();
						_okunanSatir = _okunanSatir.replace("  ", " ");
						String[] name = _okunanSatir.split(" ");
						_product.put(name[0], name[1]);
						// System.out.println("------------------------------------------");
						// System.out.println(name[0]+" = "+name[1] );
					} else if (_urunBilgi && _okunanSatir.contains("}")) {
						_urunBilgi = false;
						flatCatalog.add(_product);
						if (counter > limit) {
							// System.out.println("------- Adet = "+flatCatalog.size());
							return flatCatalog;
						}
					} else if (_urunBilgi) {
						// System.out.println(satirNo+" nolu Satir  = "+_okunanSatir);
						if (_okunanSatir.contains(":")) {
							String[] preperty = _okunanSatir.split("\\:", -1);
							String key = preperty[0];
							String value = preperty[1];
							if (preperty[1] != null) {
								value = value.replace(" locale", "");
							}
							_product.put(key, value);
							// System.out.println(key+"->>"+ value);
						} else {
							// System.out.println(satirNo+" nolu Satir  = "+_okunanSatir);
						}
					}
				}
				/*
				 * out.write(line); out.write("\n");
				 */

			}

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {

		}
		return flatCatalog;
	}

}
