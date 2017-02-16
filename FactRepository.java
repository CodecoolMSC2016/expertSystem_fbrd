import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FactRepository {
	private Fact[] facts;
	private HashMap<String,String> factMap;
	private HashMap<String, HashMap<String,Boolean>> factEvalMap;
	private FactIterator factIterator;
	
	public FactRepository(HashMap<String,String> factMap, HashMap<String, HashMap<String,Boolean>> factEvalMap){
		this.factMap = factMap;
		this.factEvalMap = factEvalMap;
		facts = new Fact[factMap.size()];
		factIterator = new FactIterator();
	}
	
	public void fillFactIDs(){
		int index = 0;
		for(Map.Entry entry: factMap.entrySet()){
			Fact fact = new Fact((String) entry.getValue());
			facts[index] = fact;
			fact.setID((String) entry.getKey());
			index++;
		}
	}
	
	public void setEvalsToFact(){
		for(int i = 0; i < facts.length; i++){
			String factID = facts[i].getID();
			HashMap<String,Boolean> evalMap = factEvalMap.get(factID);
			Set<String> evalIDs = evalMap.keySet();
			for(String ID : evalIDs){
				facts[i].setFactValueByID(ID, evalMap.get(ID));
			}
		}
	}

	public class FactIterator implements Iterator{
		int index;
		@Override
		public boolean hasNext() {
			if(index < facts.length){
	            return true;
	         }
	         return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()){
	            return facts[index++];
	         }
	         return null;
		}
	}
	

    public Iterator getIterator(){
    	return factIterator;
    }
    
    public Fact[] getFacts(){
    	return facts;
    }

    public void addFact(Fact fact){

    }
}
