import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class RuleParser implements XmlParser{

    public void loadXmlDocument(String fullPath){
        try {
            File inputFile = new File(fullPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            document.getDocumentElement().normalize();
            System.out.println(document.getDocumentElement().getNodeName());
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
