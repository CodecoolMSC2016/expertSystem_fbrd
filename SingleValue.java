public class SingleValue implements Value {
    private String[] inputPattern;

    public SingleValue(String[] inputPattern){
        this.inputPattern = inputPattern;
    }

    public String[] getInputPattern(){
        return inputPattern;
    }
}
