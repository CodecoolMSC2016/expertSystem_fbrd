import java.util.HashMap;
import java.util.Map;

public class RuleRepository {
	private Map<String, String[][]> valueMap;
	private Map<String, String> questionMap;
	private Question[] questionArray;
	private QuestionIterator questionIterator;


	public RuleRepository(Map<String, String> questionMap, Map<String, String[][]> valueMap){
		this.valueMap = valueMap;
		this.questionMap = questionMap;
		questionArray = new Question[questionMap.size()];
		questionIterator = new QuestionIterator();
	}

	public void createQuestionArray(){
		int counter = 0;
		for (Map.Entry entry: questionMap.entrySet()){
			String questionID = (String) entry.getKey();
			String questionDesc = (String) entry.getValue();
			String[][] questionValues = valueMap.get(questionID);
			Question question = new Question(questionID, questionValues, questionDesc);
			question.setAnswerEvaluator(new Answer());
			questionArray[counter] = question;
			counter++;
		}
	}
	private class QuestionIterator implements Iterator{
		int index;

		@Override
		public boolean hasNext() {
			if (index < questionArray.length){
				return true;
			}else {
				return false;
			}
		}

		@Override
		public Object next() {
			if (this.hasNext()){
				return questionArray[index++];
			}
			return null;
		}

	}

	public Question addQuestion(String id, String question){

		return null;
	}

	public Iterator getIterator(){

		return questionIterator;
	}

}
