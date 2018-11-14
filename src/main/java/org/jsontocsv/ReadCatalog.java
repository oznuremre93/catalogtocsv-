package org.jsontocsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCatalog {
	 public static final String  FILE_ENCODING      = "UTF-8";

	  public static List<String> readExistingFile(final String pattern, final String filePath) {
	        final List<String> nameList = new ArrayList<String>();
	        FileInputStream fInputStream = null;
	        BufferedReader in = null;
	        try {
	            final File file = new File(filePath);
	            if (!file.exists()) {
	              
	                return null;
	            }
	            fInputStream = new FileInputStream(file);
	            in = new BufferedReader(new InputStreamReader(fInputStream, FILE_ENCODING));
	            final Pattern p = Pattern.compile(pattern);
	            String line = null;
	            while ((line = in.readLine()) != null) {
	                final Matcher m = p.matcher(line.trim());
	                if (m.matches()) {
	                    final String regex = "\\s{2,}";
	                    nameList.add(m.group(1).trim().replaceAll(regex, " "));
	                }
	            }
	        } catch (final Exception e) {
	        } finally {
	            try {
	                fInputStream.close();
	            } catch (final Exception e2) {
	            }
	            try {
	                in.close();
	            } catch (final Exception e2) {
	            }
	        }
	        return nameList;
	    }
	  

}
