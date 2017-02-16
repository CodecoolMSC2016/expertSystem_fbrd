public class Question {
    private String question;
    private String[][] questionValues;
    private Answer answer;
    private final int TRUE = 0;
    private final int FALSE = 1;

    public Question(String question, String[][] questionValues){
        this.question = question;
        this.questionValues = questionValues;
        answer = new Answer();


    }
    public String getQuestion(){
        return question;
    }

    public void setAnswerEvaluator(Answer answer){
        Value trueValue;
        Value falseValue;
        if (questionValues[TRUE].length > 1){
            trueValue = new MultipleValue(questionValues[TRUE]);
            falseValue = new MultipleValue(questionValues[FALSE]);
        }else {
            trueValue = new SingleValue(questionValues[TRUE]);
            falseValue = new SingleValue(questionValues[FALSE]);

        }
        answer.addValue(trueValue, falseValue);
    }

    public boolean getEvaluatedAnswer(String input){
        return answer.evaluateAnswerbyInput(input);
    }
}
