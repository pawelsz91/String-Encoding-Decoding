package ie.gmit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


/**
 * 
 * @author Pawel
 * 
 * This Class provides Base64, Base32, Hex and RunLength encoding. It uses external libraries
 * such as apache commons and JSoup to get extra functionality. This class also extends
 * FileReaderWriter class to provide text manipulation as well as few extra input/output methods.
 *
 */
public class Encoding extends FileReaderWriter {

	private static RunLength runL = new RunLength();
	//file input + encoding for BASE64 encoding
	/**
	 * Encodes binary data using Base64 algorithm.
	 * 
	 * @param plainText
	 * 		tring text that will be converted to bytes and encoded
	 * @return byte[] containing Base64 characters in their UTF-8 representation.
	 */
	public static byte[] Base64Encoded(String plainText){		
		//String (text==inputText) = "original String before base64 encoding in Java";
        //encoding  byte array into base 64
        byte[] encoded = Base64.encodeBase64(plainText.getBytes());
		return encoded;
	}
	
	//decoding byte array into BASE64
	/**
	 * Decodes Base64 data into octets.
	 * 
	 * @param encodedText
	 * 		String containing Base64 data
	 * @return byte[] containing decoded data.
	 */
	public static byte[] Base64Decoded(String encodedText){		
		byte[] decoded = Base64.decodeBase64(encodedText);  	
		return decoded;
	}

	//file input + encoding for HEX encoding
	/**
	 * Converts an array of bytes into an array of characters representing the hexadecimal values of each byte in order.
     * The returned array will be double the length of the passed array, as it takes two characters to represent any
     * given byte.
     * 
	 * @param plainText
	 * 		String to convert to hex characters
	 * @return A char[] containing hexadecimal characters
	 * @throws Exception
	 */
	public static char[] HexEncoded(String plainText) throws Exception {		
		//String (text==inputText) = "original String before base64 encoding in Java";
        //encoding  byte array into base 64
        //byte[] encoded = Base64.encodeBase64(plainText.getBytes());
		char[] encoded = Hex.encodeHex(plainText.getBytes());	
		return encoded;
	}
	
	//decoding byte array into HEX
	/**
	 * Converts an String of characters representing hexadecimal values into an array of bytes of those same values. The
     * returned array will be half the length of the passed array, as it takes two characters to represent any given
     * byte. 
     * 
	 * @param encodedText
	 * 		A String of characters containing hexadecimal digits
	 * @return	A byte array containing binary data decoded from the supplied char array.
	 * @throws Exception An exception is thrown if the passed char array has an odd number of elements.
	 */
	public static byte[] HexDecoded(String encodedText) throws Exception {
		char [] encodedChar = encodedText.toCharArray();
		byte[] decoded = Hex.decodeHex(encodedChar);  
		return decoded;
	}
	
	//file input + encoding for BASE32 encoding
	/**
	 * Encodes a String containing binary data, into a byte[] containing characters in the alphabet.
	 * 
	 * @param plainText
	 * 		A String containing binary data.
	 * @return	A byte array containing only the basen alphabetic character data.
	 */
	public static byte[] Base32Encoded(String plainText){		
		//String (text==inputText) = "original String before base64 encoding in Java";
        //encoding  byte array into base 64
		Base32 base = new Base32();
        byte[] encoded = base.encode(plainText.getBytes());	
		return encoded;
	}
	
	//decoding byte array into BASE32
	/**
	 * Decodes a String containing characters in the Base32 alphabet.
	 * 
	 * @param encodedText
	 * 		A String containing Base32 character data.
	 * @return	A byte[] containing binary data.
	 */
	public static byte[] Base32Decoded(String encodedText){	
		Base32 base = new Base32();
		byte[] decoded = base.decode(encodedText);
		return decoded;
	}
	
	//RUN_LENGTH encoding
	/**
	 * Encodes a String of repetitive alphabet characters to decrease the 
	 * size of a file.
	 * 
	 * @param text
	 * 		A String of repetitive alphabet characters.
	 * @param outputFile
	 * 		File object in which the encoded String will be stored.
	 * @return Encoded String of characters and digits that show how many 
	 * occurrences of a certain character there was.
	 * @throws Exception Throws exception if there were any digits in text.
	 */
	public static String RunLengthEncoded(String text, File outputFile) throws Exception {
        String encoded = runL.encode(text, outputFile);
        return encoded;
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
    public static String RunLengthDecoded(String text, File outputFile) throws Exception{
        String decoded = runL.decode(text, outputFile);
        return decoded;
    }
}
