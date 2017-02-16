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

	private String roundValue(String usrInput){
		if (usrInput.matches("[0-9]+")){
			int inputNum = Integer.parseInt(usrInput.trim());
			inputNum = (int)(Math.floor(inputNum/10)*10);
			usrInput = String.valueOf(inputNum);
		}
		return usrInput;
	}
	public void collectAnswers(){
		ruleRepository.createQuestionArray();
		for (Iterator iter = ruleRepository.getIterator(); iter.hasNext();){
			Question question = (Question)iter.next();
			String questionID = question.getQuestion();
			Scanner scanner = new Scanner(System.in);
			System.out.println("----------\n" + question.getQuestionDesc());
			String usrInput = scanner.nextLine();
			usrInput = roundValue(usrInput);
			while (question.getEvaluatedAnswer(usrInput) == null){
				String[][] questionValues = question.getQuestionValues();
				System.out.print("Wrong input.\nPlease enter a value from below: \n");
				for(String[] values: questionValues){
					for(String value: values){
						System.out.print(" "+ value);
					}
					System.out.println();
				}
				usrInput = scanner.nextLine();
			}
		Boolean questionValue = question.getEvaluatedAnswer(usrInput);
		answers.put(questionID, questionValue);
		}
	}

	public String evaluate(){
		String correctFacts = null;
		factRepository.fillFactIDs();
		factRepository.setEvalsToFact();
		Fact[] facts = factRepository.getFacts();

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
				if (correctFacts == null){
					correctFacts = new String();
				}
				correctFacts += " ~ " + facts[i].getDescription() + "\n";
			}
		}
		return correctFacts;
	}
}
