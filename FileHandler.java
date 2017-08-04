/**
 * 
 */
package fileEncoder;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/**
 * @author Angela Pak
 * Final Project
 * Purpose: To keep track of usernames and passwords and save them as encoded text files.
 *
 */
public class FileHandler extends Encoder {
//TODO Save Record > Save File > Encode > Decode
	//Variables
	public String[] descList;
	public String encodingType;
	public int numLines;
	public String[][]fileData;
	private String[][] newData = new String [numLines][4];
	public int newRecCount;
	public String fileStatus;
	private String fileName;
	private String filePath;
	//public static Scanner fileIn;

	public static Scanner fileIn;
	public static PrintWriter fileOut;
	public FileHandler()
	{

		descList = null;
		encodingType = null;
		//fileData = null;
		numLines = 0;
		newData = null;
		newRecCount = 0;
		fileStatus = "N";
		fileName = null;
		filePath = null;
	}
	
	//Accessors and Mutators
	
	public String getEncodingType()
	{
		return encodingType;
	}
	public void setEncodingType()
	{
		//encodingType = this.encodingType;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath()
	{
		this.getFilePath();
	}
	//Methods
	public boolean selectAndOpenFile() {
		// TODO Auto-generated method stub
		Scanner stdIn = new Scanner(System.in);
		//Scanner fileIn;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(fileChooser);
		File a = fileChooser.getSelectedFile();
		fileName = a.getName();
		try
		{
			fileIn = new Scanner(new FileReader(fileName));
			String firstLine = fileIn.next();
			int index = firstLine.lastIndexOf(";");
			encodingType = firstLine.substring(0,2);
			numLines = Integer.parseInt(firstLine.substring(3,index));
			fileData   = new String[numLines][4];
			
			
			if(encodingType.equals("NA"))
			{
				String nextLine = fileIn.nextLine();
			while (fileIn.hasNextLine())
			{
			for (int r =0; r<numLines;r++)
			{
				String inLine = fileIn.nextLine();
				String[] split = inLine.split(";");
				fileData[r][0] = split[0];
				fileData[r][1] = split[1];
				fileData[r][2] = split[2];
				fileData[r][3] = split[3];
				
			}

			loadDescriptionList();
			}
			}
			else if (encodingType != "NA")
			{
				String fLine = fileIn.nextLine();
				//String[] split = inLine.split(";");
				while(fileIn.hasNextLine())
				{
				
				for (int r =0; r<numLines;r++)
				{
					for (int c=0;c<numLines;c++)
					{
					
					String next = fileIn.nextLine();	
					String decoded = decodeString(next, this.getEncodingType());
					String[] split = decoded.split(";");
					fileData[r][0] = split[0];
					fileData[r][1] = split[1];
					fileData[r][2] = split[2];
					fileData[r][3] = split[3];
					fileData[r][c] = decoded;
					
					}
				}
				
				}
			
			}
			//fileIn.close();

		}
	
		catch (Exception e) {
            System.out.println("Problems. "+e.getMessage());
		}
		
	       return true; 
	      }


	
	private void loadDescriptionList() {
		// TODO Auto-generated method stub
		descList  = new String[numLines];
		int start = 0;
		for (int b=0;b<numLines;b++)
		{
			
			descList[b] = fileData[start][0];
			start++;

		}
	}

	public boolean saveRecord(String desc, String username, String password, String notes, char addEdit)
	{
		
		try {		
		
		if (addEdit == 'A')
		{
			int cnt = 0;
			newRecCount = numLines +1;
			newData   = new String[1][4];
			newData[0][cnt] = desc;
			cnt++;
			newData[0][cnt] = username;
			cnt++;
			newData[0][cnt] = password;
			cnt++;
			newData[0][cnt] = notes;
		
		
		numLines+=1;
		for (int row=0;row<numLines;row++)
		{
			for (int col=0;col<5;col++)
			{
				if (newData[row][col]!= null)
				{
					fileData[numLines-1][0] = newData[row][col];
				}
			}
		}
		}
		
		if (addEdit == 'E')
		{
			int position = findDescriptionLineNum(desc);
				fileData[position][0] = desc;
				fileData[position][1] = username;
				fileData[position][2] = password;
				fileData[position][3] = notes;
			
		}
		}
		catch (Exception e)
		{
			System.out.println("Problems. "+e.getMessage());
		}
		
		//}
		return true;
		
		
	}
	
	public int findDescriptionLineNum(String description)
	{
		int pos = -1;
		for (int lcv=0;lcv<descList.length;lcv++)
		{
			if (description.equals(descList[lcv]))
			{
				pos = lcv;
			}
			
		}
		return pos;
	}
	
	public boolean saveFile(String[][] fileData, String fileEncType)
	{
			try {
				String outFile = "";
				for (int lcv = 0; lcv < fileName.length(); lcv++) {
					if ((fileName.substring(lcv, lcv + 1).equals("."))) {

						outFile = fileName.substring(0, lcv) + "_new"
								+ fileName.substring(lcv);

					}
				}
				
				fileOut = new PrintWriter(new FileWriter(outFile));
				fileIn = new Scanner(new FileReader(fileName));
				String first = fileIn.nextLine();
				fileOut.println(first);
				while(fileIn.hasNextLine())
				{
				String next = fileIn.nextLine();	
				String encoded = encodeString(next, fileEncType);
				fileOut.println(encoded);
				
				}
				
				
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
			
			
			
	}
	
	public boolean hasValidEncodingType(String encodingLevel)
	{
		if ((encodingLevel.equals("E1"))|| (encodingLevel.equals("E2")) || (encodingLevel.equals("E3")) || (encodingLevel.equals("NA")))
		{
			return true;
		}
		else {
			return false;
		}
	}

	

	public void closeFile() {
		// TODO Auto-generated method stub
		fileIn.close();
		fileOut.close();
	}
	
	
}
