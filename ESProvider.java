import java.util.*;

public class ESProvider {
	private RuleRepository ruleRepository;
	private FactRepository factRepository;
	private Map<String, Boolean> answers;

	public ESProvider(FactParser factParser, RuleParser ruleParser){
		ruleRepository = ruleParser.getRuleRepository();
		factRepository = factParser.getFactRepository();
		answers = new HashMap<>();
	}
	
	public void collectAnswers(){
		ruleRepository.createQuestionArray();
		for (Iterator iter = ruleRepository.getIterator(); iter.hasNext();){
			Question question = (Question)iter.next();
			String questionID = question.getQuestion();
			Scanner scanner = new Scanner(System.in);
			System.out.println(question.getQuestionDesc() + "??????????????");
			String usrInput = scanner.nextLine();
			Boolean questionValue = question.getEvaluatedAnswer(usrInput);
			System.out.println(questionValue);
			answers.put(questionID, questionValue);
		}
	}



	public boolean getAnswerByQuestion(String questionID){

		return false;
	}
	
	public String evaluate(){
		ArrayList<String> matchedResults = new ArrayList<String>();
		boolean factValue;
		String entryID;

		factRepository.fillFactIDs();
		factRepository.setEvalsToFact();
		Boolean evaleted = null;
		Fact[] facts = factRepository.getFacts();
		/*for(Iterator iter = factRepository.getIterator(); iter.hasNext();){
			Fact fact = (Fact)iter.next();
			Set<String> evalSet = fact.getIDSet();
			System.out.println(evalSet);
			System.out.println("fasz");
			for(String evalID : evalSet){
				factValue = fact.getValueByID(evalID);

				//System.out.println("evalID: " + answers.get(evalID) + ", factValue: " + factValue);
				Boolean answerValue = answers.get(evalID);
				if(!(answerValue == null)) {
					if (!answers.get(evalID).equals(factValue)) {
							continue;
					}
				}
			}
			matchedResults.add(fact.getDescription());
			System.out.println(fact.getDescription());
		}
		for (String result: matchedResults){
			//System.out.println(result);
		}*/
		for(int i = 0; i < facts.length; i++){
			Set<String> evalSet = facts[i].getIDSet();
			int counter =0;
			for(String evalID : evalSet){
				for (Map.Entry entry: answers.entrySet()){
					if (evalID.equals(entry.getKey())){
						if((facts[i].getValueByID(evalID) == (boolean) entry.getValue())){
							counter++;
						}
					}

				}
			}
			if(counter == evalSet.size()){
				System.out.println(facts[i].getDescription());
			}
		}
		return null;
	}
}
