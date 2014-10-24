package ie.gmit;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;

public class Runner {
	
	//private static Encoding enc = new Encoding();
	//private static String runLengthTest;
	//private static String runLengthEncTest;
	private static File runLengthFile = new File("runLengthTest.txt");
	private static File runLengthFileEnc= new File("runLengthTestEnc.txt");
	private static String urlText = "http://en.wikipedia.org";
	private static File input2 = new File("input2.txt");
	
	public static void main(String[] args) throws Exception {	
		//getFileinput("input.txt", UTF_8);
		
		String runLengthTest = FileReaderWriter.getFileInput(runLengthFile, StandardCharsets.UTF_8);
		String runLengthTestEnc = FileReaderWriter.getFileInput(runLengthFileEnc, StandardCharsets.UTF_8);
			
		String encoded = Encoding.RunLengthEncoded(runLengthTest, runLengthFileEnc);
		InputStream enc = new ByteArrayInputStream(encoded.getBytes("UTF-8"));        
		FileReaderWriter.writeToFile(enc, runLengthFileEnc);
		enc.close();
		
		String decoded = Encoding.RunLengthDecoded(runLengthTestEnc, runLengthFile);
		InputStream dec = new ByteArrayInputStream(decoded.getBytes("UTF-8"));        
		FileReaderWriter.writeToFile(dec, runLengthFile);
		dec.close();
		
		//RunLength test
		System.out.println("**************************************************************************");
		System.out.println("RUN_LENGTH_ENCODING_TEST");
		System.out.println();
		System.out.println("Original String: " + runLengthTest);;
		System.out.println("RunLength Encoded String: " + encoded);	
		//Thread.sleep(5000); doesnt help to output the files without errors...
		System.out.println("RunLength Decoded String: " + decoded);		
		System.out.println();
		System.out.println();
		
		/*
		 * Because of the problems that I think are caused with this runner class calling writeToFile() 
		 * before it finishes and therefore not finishing before next call (only because encoding and decoding
		 * is right after another), I will not use writeToFile() in next encodings just to simplify things,
		 * however the method writeToFile() itself seems to work fine anyways. 
		 */

		
		//Base64 test
		
		String input2TestB64 = FileReaderWriter.getFileInput(input2, StandardCharsets.UTF_8);
		String input2TestB64Enc = new String(Encoding.Base64Encoded(input2TestB64));
		System.out.println("**************************************************************************");
		System.out.println("BASE64_ENCODING_TEST");
		System.out.println();
		System.out.println("Original String: " + input2TestB64);
        System.out.println("Base64 Encoded String: " + input2TestB64Enc);
        System.out.println("Base64 Decoded String: " + new String(Encoding.Base64Decoded(input2TestB64Enc)));
        System.out.println();
        
        
        String urlElement = Encoding.getURLTextElement("http://en.wikipedia.org", "#mp-itn b a");
        String urlElementEnc = new String(Encoding.Base64Encoded(urlElement));
        System.out.println();
		System.out.println("Original String: " + urlElement);
        System.out.println("Base64 Encoded String: " + urlElementEnc);
        System.out.println("Base64 Decoded String: " + new String(Encoding.Base64Decoded(urlElementEnc)));
        System.out.println();
        
        
        //Base32 test
		String input2TestB32 = FileReaderWriter.getFileInput(input2, StandardCharsets.UTF_8);
		String input2TestB32Enc = new String(Encoding.Base32Encoded(input2TestB32));
        System.out.println("**************************************************************************");
        System.out.println("BASE32_ENCODING_TEST");
        System.out.println();
        System.out.println("Original String: " + input2TestB32);
        System.out.println("Base32 Encoded String: " + input2TestB32Enc);
        System.out.println("Base32 Decoded String: " + new String(Encoding.Base32Decoded(input2TestB32Enc)));
        System.out.println();
        

        //HEX test
        String input2TestHex = FileReaderWriter.getFileInput(input2, StandardCharsets.UTF_8);
        String input2TestHexEnc = new String(Encoding.HexEncoded(input2TestHex));
  		System.out.println("**************************************************************************");
  		System.out.println("HEX_ENCODING_TEST");
  		System.out.println();
  		System.out.println("Original String: " + input2TestHex);
  		System.out.println("Hex Encoded String: " + input2TestHexEnc);
  		System.out.println("Hex Decoded String: " + new String(Encoding.HexDecoded(input2TestHexEnc)));
  		System.out.println();
        
        //select the headlines from the In the news section
        //System.out.println(getURLTextElement("http://en.wikipedia.org", "#mp-itn b a"));
        System.out.println("**************************************************************************");
  		System.out.println("Using implemente JSoup api to get list of links from http://en.wikipedia.org");

  		FileReaderWriter.listLinks(urlText);
        

	}

}
