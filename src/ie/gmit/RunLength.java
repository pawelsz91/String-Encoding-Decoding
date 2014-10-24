package ie.gmit;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLength {
	
	//RUN_LENGTH encoding
		/**
		 * Encodes a String of repetitive alphabet characters to decrease the 
		 * size of a file.
		 * 
		 * @param text
		 * 		A String of repetitive alphabet characters.
		 * @param outputFile
		 * 		File object in which the encoded String will be stored.
		 * @return Encoded String of characters and digits that shows how many 
		 * occurrences of a certain character there was.
		 * @throws Exception Throws exception if there were any digits in text.
		 */
		public static String encode(String text, File outputFile) throws Exception {
	        StringBuffer dest = new StringBuffer();
	        for (int i = 0; i < text.length(); i++) {
	            int runLength = 1;
	            while (i+1 < text.length() && text.charAt(i) == text.charAt(i+1)) {
	                runLength++;
	                i++;
	            }
	            dest.append(runLength);
	            dest.append(text.charAt(i));
	        }
	        return dest.toString();
	    }
	 
		//RUN_LENGTH decoding
		/**
		 * Decodes a String using RunLength algorithm.
		 * 
		 * @param text
		 * 		String of encoded characters.
		 * @param outputFile
		 * 		File object in which the decoded String will be stored.
		 * @return Decoded String of characters.
		 * @throws Exception
		 */
	    public static String decode(String text, File outputFile) throws Exception{
	        StringBuffer dest = new StringBuffer();
	        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-z\\P{L}]", Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(text);
	        while (matcher.find()) {
	            int number = Integer.parseInt(matcher.group());
	            matcher.find();
	            while (number-- != 0) {
	                dest.append(matcher.group());
	            }
	        }
	        return dest.toString();
	    }

}
