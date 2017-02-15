import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RuleParser implements XmlParser{

    public void loadXmlDocument(String fullPath){
        try {
            Map<String, String> ruleMap = new HashMap<String, String>();
            File inputFile = new File(fullPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            NodeList nodeList = document.getElementsByTagName("Rule");
            for (int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                NodeList childList = element.getChildNodes();
                for (int j = 0; j < childList.getLength(); j++){
                    Node childElement = (Node) childList.item(j);
                    ruleMap.put(element.getAttribute("id"), childElement.getTextContent().trim());
                    System.out.println(childElement.getTextContent().replace("\n", ""));
                }

                //Element child = (Element) element.getFirstChild();
                //System.out.println(element.getAttribute("id") + " " + child.getTextContent());
            }
            for (Map.Entry<String, String> entry: ruleMap.entrySet()) {
                System.out.print(entry.getValue());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public RuleRepository getRuleRepository(){

        return null;
    }

    public static void main(String[] args){
        RuleParser ruleParser = new RuleParser();
        ruleParser.loadXmlDocument("src/rules.xml");
    }

}
