import java.io.File;
import java.util.ArrayList;

public class runPreprocess {

	public static void main(String[] args) throws Exception {
		Preprocessing p = new Preprocessing();

		File pos = new File("positive.txt");
		File neg = new File("negative.txt");
		File neutral = new File("neutral.txt");
		ArrayList<ArrayList<String>> positiveList = p.readFile(pos);
		ArrayList<ArrayList<String>> negativeList = p.readFile(neg);
		ArrayList<ArrayList<String>> neutralList = p.readFile(neutral);
		
		p.positiveList = positiveList;
		p.negativeList = negativeList;
		p.neutralList = neutralList;
		p.tfidf(positiveList, 1);
		p.tfidf(negativeList, -1);
		p.tfidf(neutralList, 0);
		
		
		p.loadFile();
		Bayes bayes = new Bayes();
		System.out.println(bayes.sentiment("I just tried the most amazing chocolate cake ever it was truly amazing really it was amazing"));
		
		
		LogisticRegression lr = new LogisticRegression();
		System.out.println(lr.sentiment("I was not offended by this cake quite the opposite actually"));
	}

}
