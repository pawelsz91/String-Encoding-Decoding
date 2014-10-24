package ie.gmit;

import java.io.File;

import org.apache.commons.codec.DecoderException;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EncodingTest {
	private Encoding encoding = null;
	private static File runLengthFile = new File("runLengthTest.txt");
	
	@Before
	public void createPart(){
		encoding = new Encoding();
	}
	
	@After
	public void destroyPart(){
		encoding = null;
	}
	
	@Test
	public void validateBase64Input() throws Exception{
		encoding.Base64Encoded("quick fox over a lazy dogs");
		assertNotNull(encoding.toString());
	}
	
	@Test
	public void validateBase32Input() throws Exception{
		encoding.Base32Encoded("quick fox over a lazy dogs");
		assertNotNull(encoding.toString());
	}
	
	@Test
	public void validateHexInput() throws Exception{
		encoding.HexEncoded("quick fox over a lazy dogs");
		assertNotNull(encoding.toString());
	}
	
	
	@Test
	public void validateRunLengthInput() throws Exception{
		encoding.RunLengthEncoded("AAAAWWWWWDDDDEEEEEOOOWW", runLengthFile );
		assertNotNull(encoding.toString());
	}
	
	@Test(expected=Exception.class)
	public void validateBadRunLengthOutput() throws Exception{
		Encoding.RunLengthDecoded("123qawdsadq232r1qfaw-r3134", runLengthFile);
	}
}
