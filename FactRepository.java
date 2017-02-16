import java.util.HashMap;
import java.util.Map;

public class FactRepository {
	private Fact[] factIDs;
	private HashMap<String,String> factMap;
	private HashMap<String, HashMap<String,Boolean>> factEvalMap;
	
	public FactRepository(HashMap<String,String> factMap, HashMap<String, HashMap<String,Boolean>> factEvalMa){
		this.factMap = factMap;
		this.factEvalMap = factEvalMap;
		factIDs = new Fact[factMap.size()];
	}
	
	public void fillFactIDs(){
		int index = 0;
		for(Map.Entry entry: factMap.entrySet()){
			factIDs[index] = new Fact((String) entry.getValue());
		}
	}

	public class FactIterator implements Iterator{
		int index;
		@Override
		public boolean hasNext() {
			if(index < factIDs.length){
	            return true;
	         }
	         return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()){
	            return factIDs[index++];
	         }
	         return null;
		}
	}
	

    public Iterator getIterator(){
    	return null;
    }

    public void addFact(Fact fact){

    }
}
