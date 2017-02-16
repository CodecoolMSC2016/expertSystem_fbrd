import java.util.HashMap;
import java.util.Set;

public class Fact {
    
    private String ID;
    private String description;
    private HashMap<String, Boolean> valuesMap;
    
    public Fact(String description){
    	this.description = description;    	
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
