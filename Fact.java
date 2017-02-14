import java.util.Set;

/**
 * Created by imre_meszesan on 14.02.17.
 */
public class Fact {
    private String description;
    private String ID;
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Fact(String description){
        this.description = description;
    }

    public void getFactValueByID(String id, boolean value){
    }

    public Set<String> getIDSet(){
        return null;
    }

    public boolean getValueByID(String id){
        return false;
    }

    public String getDescription(){
        return null;
    }
}
