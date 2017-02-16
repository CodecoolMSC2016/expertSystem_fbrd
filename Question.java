public class Question {
    private String question;
    private String[][] questionValues;
    private String questionDesc;
    private Answer answer;
    private final int TRUE = 0;
    private final int FALSE = 1;

    public Question(String question, String[][] questionValues, String questionDesc){
        this.question = question;
        this.questionValues = questionValues;
        this.questionDesc = questionDesc;

    }
    public String getQuestionDesc(){
        return questionDesc;
    }
    public String getQuestion(){
        return question;
    }

    public void setAnswerEvaluator(Answer answer){
        this.answer = answer;
        Value trueValue;
        Value falseValue;
        if (questionValues[TRUE].length > 1){
            trueValue = new MultipleValue(questionValues[TRUE]);
            falseValue = new MultipleValue(questionValues[FALSE]);
        }else {
            trueValue = new SingleValue(questionValues[TRUE]);
            falseValue = new SingleValue(questionValues[FALSE]);

        }
        this.answer.addValue(trueValue, falseValue);
    }

    public Boolean getEvaluatedAnswer(String input){
        return answer.evaluateAnswerbyInput(input);
    }
}
