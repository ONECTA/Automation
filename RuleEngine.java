package Autosys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RuleEngine {
	public ArrayList<String> lines = null;
	public int InputCtr = 0;
	ArrayList<String> Report = new ArrayList<String>();
	String StrUnderMatch = null;
	public RuleEngine() throws IOException{
		lines= new ArrayList<String>();
		
		readFile();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				RuleEngine re = new RuleEngine();
				re.getConfig();
	}
	
	public void readFile() throws IOException
	{
		// Open the file
		FileInputStream fstream = new FileInputStream("Input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	    Integer NumberOfExtraction = 0;
		String strLine;
	    //Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		 	lines.add(strLine);
		 	Report.add("Report for Line" + (NumberOfExtraction + 1 ) +"\n");
		    NumberOfExtraction++;
		}
		//Close the input stream
		br.close();
		InputCtr = NumberOfExtraction;
		System.out.println("Count of Lines:"+NumberOfExtraction);
		
	}

	public void getConfig() {
        try {
        	String DType = null;
        	int DLen  = 0;
        	int lastindex = 0;
        	
            String tempReport = null;
        	File fXmlFile = new File("RuleEngine.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Rule");
            System.out.println("---------  Loading Rules --------");
            int temp = 0;
            while (temp < nList.getLength()) {
                Node nNode = nList.item(temp);
                System.out.println("\n ***********Rule NUMBER :" + (temp + 1) + "**************");
                if (nNode.getNodeType() == 1) {
                    Element eElement = (Element)nNode;
                    DType = eElement.getElementsByTagName("DataType").item(0).getTextContent();
                    DLen  = Integer.parseInt(eElement.getElementsByTagName("Length").item(0).getTextContent(), 10);
                    for(int ictr=0;ictr<InputCtr;ictr++){
                    	if(!DType.equalsIgnoreCase("String")){
                    	System.out.println("DataType:"+ DType);
                        System.out.println("Length:" + DLen);
                        if(!validateRule(lastindex, DLen, ictr)){
                        	tempReport = Report.get(ictr) + "Voilation in Rule Number " + (temp + 1) + " Expected a integer found String:"+ StrUnderMatch + "\n";
                        	Report.set(ictr, tempReport );
                        	
                        }
                        
                    }
                    }
                    
                    lastindex = lastindex + DLen;
                }
                ++temp;
            }
            for(int ictr=0;ictr<InputCtr;ictr++){
            	if(lastindex != lines.get(ictr).length()){
            		System.out.println("Mismatch in Length of Rule and Line Number:" + (ictr + 1 ));
            	}
            }
            
            System.out.println(Report);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

public boolean validateRule(int lastindex,int charLen ,int inputLineNumber)
{
	 // String to be scanned to find the pattern.
    boolean result = false;
	String pattern = "([0-9]{"+ charLen +"})";
    System.out.println("Pattern-" + pattern);
    // Create a Pattern object
    Pattern r = Pattern.compile(pattern);
    // Now create matcher object.
    StrUnderMatch = lines.get(inputLineNumber).substring(lastindex, lastindex + charLen);
    System.out.println("StrUnderMatch:"+ StrUnderMatch + ":");
    Matcher m = r.matcher(StrUnderMatch);
     try{
    	 if (m.find()) {
     
      // System.out.println("Found value: " + m.group(0) );
      // result=  m.group(0).substring(3);
        result = true;
    	 }
    	 else{
    		 
    	result = false;
    	 }
    	 }
     catch(Exception e){
    	 e.printStackTrace();
    	 result = false;
     }
     System.out.println("MatchResult:" + result);
    return result;
}
}
