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
    private Document document;

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

    public RuleRepository getRuleRepository(){
        loadXmlDocument("src/rules.xml");
        Map<String, String> questionMap = new HashMap<String, String>();
        Map<String, Map<String, String[][]>> valueMap = new HashMap<String, Map<String, String[][]>>();
        Map<String, String[][]> innerMap = new HashMap<String, String[][]>();
        NodeList nodeList = document.getElementsByTagName("Rule");
        for (int i = 0; i < nodeList.getLength(); i++){
            Element ruleElement = (Element) nodeList.item(i);
            String ruleID = ruleElement.getAttribute("id");
            NodeList childList = ruleElement.getElementsByTagName("Question");
            for (int index = 0; index < childList.getLength(); index++){
                if (childList.item(index).getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) childList.item(index);
                    questionMap.put(ruleID, childElement.getTextContent());
                }
            }
            NodeList answers = ruleElement.getElementsByTagName("Answer");
            Element answer = (Element) answers.item(0);
            NodeList selectionList = answer.getElementsByTagName("Selection");
            String[][] selectionResults = new String[2][1];

            for (int index = 0; index < selectionList.getLength(); index++){
                if (selectionList.item(index).getNodeType() == Node.ELEMENT_NODE) {
                    Element selectionValue = (Element) selectionList.item(index);
                    NodeList singleValues = selectionValue.getElementsByTagName("SingleValue");
                    NodeList multipleValues = selectionValue.getElementsByTagName("MultipleValue");
                    if (multipleValues.item(0) == null) {
                        Element singleValue = (Element) singleValues.item(0);
                        selectionResults[index][0] = singleValue.getNodeName();
                    }else {
                        Element multipleValue = (Element) multipleValues.item(0);
                        selectionResults[index][0] = multipleValue.getNodeName();
                    }
                }
            }
            innerMap.put(ruleID, selectionResults);
            valueMap.put(ruleID, innerMap);
            innerMap = new HashMap<String, String[][]>();
        }

        return null;
    }

    public static void main(String[] args){
        RuleParser ruleParser = new RuleParser();
        ruleParser.getRuleRepository();
    }

}
