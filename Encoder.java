/**
 * 
 */
package fileEncoder;

/**
  * @author Angela Pak
 * Final Project
 * Purpose: To keep track of usernames and passwords and save them as encoded text files.
 */
public class Encoder  {

	private String newString ="";
	private String finalString ="";
	private char newchar;
public String encodeString(String eString, String encodeLevel)
{
	//while(hasValidEncodingType())
	if (encodeLevel.equals("E1"))
	{
		int intStep1Alteration = 3;
		int intStep2Alteration = 2;
		//Loop to create a new String with first step encoding
		String newString ="";
		String finalString = "";
		for (int lcv=0;lcv<eString.length();lcv++)
		{
			char let = eString.charAt(lcv);
			int letVal = (int) let;
			letVal -= intStep1Alteration;
			newchar = (char)letVal;
			newString += newchar;
		}
	
		//Loop to create a new String with second step encoding
		
		 for (int lcv2=0;lcv2<newString.length()-1;lcv2+=2)
		 {
			 
			 char let = newString.charAt(lcv2+1);
			 int letVal = (int) let;
			 letVal -= intStep2Alteration;
			 newchar = (char) letVal;
			 finalString += newString.charAt(lcv2);
			 finalString += newchar;
		 }
		 return finalString;			
	}
	
	if (encodeLevel.equals("E2"))
	{
		int intStep1Alteration = 5;
		int intStep2Alteration = 2;
		//Loop to create a new String with first step encoding
		for (int lcv=0;lcv<eString.length();lcv++)
		{
			char let = eString.charAt(lcv);
			int letVal = (int) let;
			letVal += intStep1Alteration;
			newchar = (char)letVal;
			newString += newchar;
		}
}
	return eString;

}
public String decodeString(String eString, String encodeLevel)
{
	if (encodeLevel.equals("E1"))
	{
		int step1Alteration = 2;
		int step2Alteration = 3;
		//Loop to create a new String with first step decoding
		for (int lcv2=0;lcv2<eString.length()-1;lcv2+=2)
		{
			char letter = eString.charAt(lcv2+1);
			int letVal = (int) letter;
			letVal += step1Alteration;
			newchar = (char) letVal;
			newString += newchar;
		}
		
		//Loop to create a new String with second step decoding
		for (int lcv=0;lcv<eString.length();lcv++)
		{
			char let = eString.charAt(lcv);
			int letVal = (int) let;
			letVal += step2Alteration;
			newchar = (char)letVal;
			finalString += newchar;
		}
		return finalString;
	}
	
	
	return encodeLevel;

}
}
