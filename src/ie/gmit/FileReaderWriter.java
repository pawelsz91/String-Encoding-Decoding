package ie.gmit;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.*;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.BaseNCodec;

/**
 * 
 * @author Pawel
 * 
 * This class provides methods that can read strings from files and convert
 * them to byte[]. It can also write text to file using Input and Output
 * stream. This class uses also JSoup API to provide reading text elements
 * from URL.
 *
 */
public class FileReaderWriter {
	
	//file input for encoding
	/**
	 * Takes input from a file as a String and converts it to a byte[].
	 * 
	 * @param fileName
	 * 		Name of a file to take input from.
	 * @param charset
	 * 		Charset specification eg. UTF_8
	 * @return String representation of a file.
	 * @throws Exception
	 */
	public static String getFileInput(File fileName, Charset charset) throws Exception{
		//String fileInput = readFileRunLength("input.txt", StandardCharsets.UTF_8);
		String fileNameString = fileName.toString();
		byte[] encoded = Files.readAllBytes(Paths.get(fileNameString));    	
		String fileInput = charset.decode(ByteBuffer.wrap(encoded)).toString();	
		return fileInput;
	}
	
	//Creating a text file (note that this will overwrite the file if it already exists):
	/**
	 * Writes to file using Input and Output stream.
	 * 
	 * @param in
	 * 		Takes in InputStream object with data.
	 * @param file
	 * 		File object in which data will be stored.
	 * @throws Exception
	 */
	public static void writeToFile(InputStream in, File file) throws Exception {
		OutputStream out = new FileOutputStream(file);
		// if both are file streams, use channel IO
	    if ((out instanceof FileOutputStream) && (in instanceof FileInputStream)) {
	    	System.out.println("instanceof TRUE");
			FileChannel target = ((FileOutputStream)out).getChannel();
			FileChannel source = ((FileInputStream)in).getChannel();
			
			source.transferTo(0, Integer.MAX_VALUE, target);
			
			source.close();
			target.close();
	    }
	                        
		byte[] buf = new byte[8192];
		while (true) {
			int length = in.read(buf);
			if (length < 0)
				break;
			out.write(buf, 0, length);
	        }
			
	        try {
	        	in.close();
	        } 
	        catch (IOException ignore) {
	        	ignore.printStackTrace();
	        }
	        try {
	        	out.close();
	        } 
	        catch (IOException ignore) {
	        	ignore.printStackTrace();
	        }			
	}

	//Creating a binary file (will also overwrite the file):
/*
		byte dataToWrite[] = //...
		FileOutputStream out = new FileOutputStream("the-file-name");
		out.write(dataToWrite);
		out.close();
*/

	
	//URL file inpuit
	/**
	 * Connecting to a URL and takes specified URL data as a text.
	 * 
	 * @param url
	 * 		URL address eg. http://www.google.com
	 * @return String representing text from a URL.
	 * @throws Exception
	 */
	public static String getURLFile(String url) throws Exception{
		//String fileInput = new Scanner(new URL("http://www.google.com").openStream(), "UTF-8").useDelimiter("\\A").next();	
		String fileInput = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
		return fileInput;
	}	
	
	//get URL text using Jsoup API
	/**
	 * Gets URL element using JSoup API and converts it to text.
	 * 
	 * @param url
	 * 		URL address of a website eg. http://en.wikipedia.org/
	 * @param pageElement
	 * 		URL element that will be taken from website and converted
	 * to text, eg. <td></td>, #mp-itn b a
	 * @return URL element in text form.
	 * @throws Exception
	 */
	public static String getURLTextElement(String url, String pageElement) throws Exception{
		//Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
		Document doc = Jsoup.connect(url).get();
		//Elements textElement = doc.select("#mp-itn b a");
		Elements textElement = doc.select(pageElement);
		return textElement.text();
	}
	
	/**
	 * Shows list of links that the URL is using.
	 * 
	 * @param url
	 * 		URL address of a website eg. http://en.wikipedia.org/
	 * @return null
	 * @throws IOException
	 */
	public static String listLinks(String url) throws IOException {
        //Validate.isTrue(url.length() == 1, "usage: supply url to fetch");
        String webURL = url;
        print("Fetching %s...", webURL);

        Document doc = Jsoup.connect(webURL).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        
        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.tagName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s> (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
		return null;
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
