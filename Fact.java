import java.util.HashMap;
import java.util.Set;

public class Fact {
    
    private String ID;
    private String description;
    private HashMap<String, Boolean> evalMap;
    
    public Fact(String description){
    	this.description = description;   
    	this.evalMap = new HashMap<String, Boolean>();
    }

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFactValueByID(String id, boolean value){
        evalMap.put(id, value);
    }

    public Set<String> getIDSet(){
        return evalMap.keySet();
    }

    public boolean getValueByID(String id){
        return evalMap.get(id);
    }

    public String getDescription(){
        return description;
    }
}
