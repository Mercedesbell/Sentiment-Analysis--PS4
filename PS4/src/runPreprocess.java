import java.io.File;
import java.util.ArrayList;

public class runPreprocess {

	public static void main(String[] args) throws Exception {
		Preprocessing p = new Preprocessing();

		File pos = new File("positive.txt");
		File neg = new File("negative.txt");
		File neutral = new File("neutral.txt");
		ArrayList<String> positiveList = p.readFile(pos);
		ArrayList<String> negativeList = p.readFile(neg);
		ArrayList<String> neutralList = p.readFile(neutral);
		
		p.positiveList = positiveList;
		p.negativeList = negativeList;
		p.neutralList = neutralList;
		
		for( String w : positiveList ) {
			p.tfidf(w, 1);
		}
		
		for( String w : negativeList ) {
			p.tfidf(w, -1);
		}
		
		for( String w : neutralList ) {
			p.tfidf(w, 0);
		}
		
		
		p.loadFile();
		Bayes bayes = new Bayes();
		System.out.println(bayes.sentiment("This super delicious cake was yummy and amazing"));
		
	}

}
