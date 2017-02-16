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
    private void addQuestionMapEntry(Element ruleElement, String ruleID, Map<String, String> questionMap){
        NodeList childList = ruleElement.getElementsByTagName("Question");
        for (int index = 0; index < childList.getLength(); index++){
            if (childList.item(index).getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childList.item(index);
                questionMap.put(ruleID, childElement.getTextContent());
            }
        }
    }

    private void addValueMapEntry(Element ruleElement, String ruleID, Map<String, String[][]> valueMap){
        NodeList answers = ruleElement.getElementsByTagName("Answer");
        String[][] selectionResults = new String[2][1];

        for (int index = 0; index < answers.getLength(); index++){
            Element answer = (Element) answers.item(index);
            NodeList selectionList = answer.getElementsByTagName("Selection");

            for (int index2 = 0; index2 < selectionList.getLength(); index2++){
                if (selectionList.item(index2).getNodeType() == Node.ELEMENT_NODE) {
                    Element selectionValue = (Element) selectionList.item(index2);
                    NodeList singleValues = selectionValue.getElementsByTagName("SingleValue");
                    NodeList multipleValues = selectionValue.getElementsByTagName("MultipleValue");
                    if (multipleValues.item(0) == null) {
                        Element singleValue = (Element) singleValues.item(0);
                        selectionResults[index2] = singleValue.getAttribute("value").split(",");
                    }else {
                        Element multipleValue = (Element) multipleValues.item(0);
                        selectionResults[index2] = multipleValue.getAttribute("value").split(",");
                    }
                }
            }
            valueMap.put(ruleID, selectionResults);
            selectionResults = new String[2][];
        }
    }

    public RuleRepository getRuleRepository(){
        loadXmlDocument("rules.xml");
        NodeList nodeList = document.getElementsByTagName("Rule");
        Map<String, String> questionMap = new HashMap<String, String>();
        Map<String, String[][]> valueMap = new HashMap<String, String[][]>();

        for (int i = 0; i < nodeList.getLength(); i++){
            Element ruleElement = (Element) nodeList.item(i);
            String ruleID = ruleElement.getAttribute("id");
            addQuestionMapEntry(ruleElement, ruleID, questionMap);
            addValueMapEntry(ruleElement, ruleID, valueMap);
        }
        return new RuleRepository(questionMap, valueMap);
    }
}
