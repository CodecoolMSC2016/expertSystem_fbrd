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
	private HashMap<String,String> factMap = new HashMap<String,String>();
	private HashMap<String, HashMap<String,Boolean>> factEvalMap = new HashMap<String,HashMap<String,Boolean>>();
	private HashMap<String,Boolean> innerEvalMap = new HashMap<String,Boolean>();
	
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
		loadXmlDocument("facts.xml");
		
		NodeList nList = document.getElementsByTagName("Fact");
        for (int i = 0; i < nList.getLength(); i++) {
        	Node nNode = nList.item(i);
        	Element eElement = (Element) nNode;
        	NodeList nList2 = eElement.getElementsByTagName("Desctription"); 
        	Node nNode2 = nList2.item(0);
        	Element eElement2 = (Element) nNode2;
    		factMap.put(eElement.getAttribute("id"), eElement2.getAttribute("value"));

        	
        	NodeList nList3 = eElement.getElementsByTagName("Eval");
        	for(int j = 0; j < nList3.getLength(); j++){
        		if (nNode.getNodeType() == Node.ELEMENT_NODE) {            			
        			Element eElement3 = (Element) nList3.item(j);
            		String igaz = "true";
            		innerEvalMap.put(eElement3.getAttribute("id"), igaz.equalsIgnoreCase(eElement3.getTextContent()));
            	}
        	}
    		factEvalMap.put(eElement.getAttribute("id"), innerEvalMap);
    		innerEvalMap = new HashMap<String,Boolean>();
        }
		return new FactRepository(factMap,factEvalMap);
	}

}
