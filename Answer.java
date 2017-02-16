public class Answer {
    private Value valueTrue;
    private Value valueFalse;

    public Boolean evaluateAnserbyInput(String input){
        for (String value : valueTrue.getInputPattern()){
            if (input.equals(value)){
                return true;
            }
        }
        for (String value : valueFalse.getInputPattern()){
            if (input.equals(value)){
                return false;
            }
        }
        return null;
    }

    public void addValue(Value valueTrue, Value valueFalse){
        this.valueTrue = valueTrue;
        this.valueFalse = valueFalse;
    }
}
