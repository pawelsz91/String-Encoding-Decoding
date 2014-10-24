1. Instructions
Library can be used for encdoing functionality or can be runned from terminal because there
is a Runner class attached. Runner class is only to show what have been implemented and it
is NOT a part of API, rather a test class. 
	To see library functionality open unpack encoder.jar, input2.txt, runLengthTest.txt
and runLengthTestEnc.txt in directory(it is necessary to have them files for Runner to 
show test correctly). When thats done, go to the terminal, in terminal go to the directory
where the files have been unpacked and type in following command 
-> java -cp ./encoder.jar ie.gmit.Runner

After this Runner class will show most usage of the API in order:
- RunLength encoding/decoding
- Base64 encoding/decoding
- Base64 encoding/decoding using url text element
- Base32 encoding/decoding
- Hex encoding/decoding
- usage of implemented JSoup API(shows list of links from a URL eg. http://en.wikipedia.org)

2. Documents
There is a doc folder that contains all javadoc for relevant classes inside the project.

3. Unit testing and Ant
Unit testing for Encoding.java class is provided. I was unable to create proper Ant build...

4. UML
Basic UML diagram showing the layout of an API should be there as well.

5. Minor bugs/extra info
Because I'm using Input/Output stream and I'm calling writeToFile method 2x one after another,
I think it causes concurrency problems, and thats why I need 2 text files for RunLength to
show how it works with writeToFileMethod. For other methods I've used only input from input2.txt
without actually saving anything to file, thou it can be done with the methods that I provide if 
used propertly.
There are only 4 encoding methods and they are probably the easier ones, but I hope that should
be enough to show my API is working as intended. I was able to get Huffman encoding however I 
couldn't get the encoding done(not enough time to properly understand algorithm, and rebuild 
the tree to get decoding done.)
	Source code from scr folder will obviously show errors if you will try to run it as it is
due to lack of external Libraries. So just in case I'm adding to the folder two libraries that I
was using (commons-codec-1.9.jar, jsoup-1.7.3.jar). If you want to run source code you need to add
them into your build path.(obviously you know that, but just in case....)

6. Author
Pawel Szulc, G00280690