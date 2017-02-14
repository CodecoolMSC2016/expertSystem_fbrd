public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FactParser factParser = new FactParser();
		RuleParser ruleParser = new RuleParser();
		ESProvider esProvider = new ESProvider(factParser, ruleParser);
	}

}
