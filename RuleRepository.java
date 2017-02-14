import java.util.Map;

public class RuleRepository {
	private Map<String, Question> ruleMap;

	public RuleRepository(){
		QuestionIterator questionIterator = new QuestionIterator();
	}
	private class QuestionIterator implements Iterator{

		public QuestionIterator(){

		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	
	public Question addQuestion(String id, Question question){
		return null;
	}
	
	public Iterator getIterator(){
		return null;
	}

}
