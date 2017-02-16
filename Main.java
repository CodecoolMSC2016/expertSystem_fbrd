public class Main {

	public static void main(String[] args) {
		FactParser factParser = new FactParser();
		RuleParser ruleParser = new RuleParser();
		ESProvider esProvider = new ESProvider(factParser, ruleParser);
		System.out.println("################################");
		System.out.println("Welcome to the IT Expert System!");
		System.out.println("################################");
		System.out.println("\nPlease answer the following questions to get a diagnostic of your computer.\n");
		esProvider.collectAnswers();
		String matchedFacts = esProvider.evaluate();
		if (matchedFacts == null){
			System.out.println("Something went wrong. Please try again.");
		}else {
			System.out.println("\nDiagnostic based on your answers:");
			System.out.print(matchedFacts+ "\n");
			System.out.println("Goodbye!");
		}
	}
}
