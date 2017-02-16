public class MultipleValue implements Value {
    private String[] inputPattern;

    public MultipleValue(String[] inputPatter){
        this.inputPattern = inputPatter;
    }

    public String[] getInputPattern(){

        return inputPattern;
    }
}
