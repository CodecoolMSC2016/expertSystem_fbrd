public class MultipleValue implements Value {
    private String param;

    public MultipleValue(String param){

        this.param = param;
    }

    public String[] getInputPattern(){
        return null;
    }
}
