import java.io.File;
import java.util.ArrayList;

public class run {

	public static void main(String[] args) throws Exception{
		Preprocessing p = new Preprocessing();

		File pos = new File("positiveTraining.txt");
		File neg = new File("negativeTraining.txt");
		File neutral = new File("neutralTraining.txt");
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
        bayes.loadHashMap();
        LogisticRegression lr = new LogisticRegression();
        lr.loadHashMap();
		runPreprocess run = new runPreprocess();
//		run.testing(bayes, p);
		run.testing(lr, p);
	}

}
