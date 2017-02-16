import java.util.Map;

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
		factRepository.fillFactIDs();
		for (Iterator iter = factRepository.getIterator(); iter.hasNext();){
			//System.out.println(((Fact)iter.next()).getDescription());
		}
		
		
		return null;
	}
}
