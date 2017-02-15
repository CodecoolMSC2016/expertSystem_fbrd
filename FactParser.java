import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FactParser implements XmlParser{

	private Document document;
	
	//       ID     Descr
	HashMap<String,String> factMap = new HashMap<String,String>();
	//       ID           EvalID | Boolean
	HashMap<String, HashMap<String,Boolean>> factEvalMap = new HashMap<String,HashMap<String,Boolean>>();
	
	HashMap<String,Boolean> innerEvalMap = new HashMap<String,Boolean>();
	
	@Override
	public void loadXmlDocument(String fullPath){
        try {
        	
            File inputFile = new File(fullPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(inputFile);
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
	
	
	public FactRepository getFactRepository(){
		
		NodeList nList = document.getElementsByTagName("Fact");
        for (int i = 0; i < nList.getLength(); i++) {
        	Node nNode = nList.item(i);
        	Element eElement = (Element) nNode;
        	System.out.println("Fact : " + eElement.getAttribute("id"));
        	
        	NodeList nList2 = eElement.getElementsByTagName("Desctription"); 
        	Node nNode2 = nList2.item(0);
        	Element eElement2 = (Element) nNode2;
    		System.out.println("Desc : " + eElement2.getAttribute("value"));
    		factMap.put(eElement.getAttribute("id"), eElement2.getAttribute("value"));

        	
        	NodeList nList3 = eElement.getElementsByTagName("Eval");
        	
        	for(int j = 0; j < nList3.getLength(); j++){
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {            			
        			Element eElement3 = (Element) nList3.item(j);
            		System.out.println("Evals : " + eElement3.getAttribute("id"));
            		System.out.println("bool : " + eElement3.getTextContent());
            		String igaz = "true";
            		innerEvalMap.put(eElement3.getAttribute("id"), igaz.equalsIgnoreCase(eElement3.getTextContent()));
            	}
        	}
    		System.out.println(innerEvalMap);
    		factEvalMap.put(eElement.getAttribute("id"), innerEvalMap);
    		innerEvalMap = new HashMap<String,Boolean>();
        }
		
		System.out.println(factMap);
		System.out.println(factEvalMap);
		return null;
	}
	
	public static void main(String[] args){
        FactParser factParser = new FactParser();
        factParser.loadXmlDocument("src/facts.xml");
        factParser.getFactRepository();
    }

}
