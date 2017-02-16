import java.util.Map;
import java.util.Set;

public class ESProvider {
	private RuleRepository ruleRepository;
	private FactRepository factRepository;
	private Map<String, Answer> answers;

	public ESProvider(FactParser factParser, RuleParser ruleParser){
		ruleRepository = ruleParser.getRuleRepository();
		factRepository = factParser.getFactRepository();
	}
	
	public void collectAnswers(){
		ruleRepository.createQuestionArray();
		for (Iterator iter = ruleRepository.getIterator(); iter.hasNext();){
			System.out.println(((Question)iter.next()).getQuestion());
		}
	}
	
	public boolean getAnswerByQuestion(String questionID){
		return false;
	}
	
	public String evaluate(){
		System.out.println("---------------------------------------");
		factRepository.fillFactIDs();
		factRepository.setEvalsToFact();
		Fact[] facts = factRepository.getFacts();
		
		for(int i = 0; i < facts.length; i++){
			System.out.println(facts[i].getID());
			System.out.println(facts[i].getDescription());
			
			Set<String> evalSet = facts[i].getIDSet();
			for(String evalID : evalSet){
				System.out.println(evalID +": "+ facts[i].getValueByID(evalID));
			}
			

			System.out.println("--------");
		}
			
		
		
		
		for (Iterator iter = factRepository.getIterator(); iter.hasNext();){
			System.out.println(((Fact)iter.next()).getDescription());		
		}
		
		return null;
	}
}
