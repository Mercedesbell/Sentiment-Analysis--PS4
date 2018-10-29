import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class runPreprocess {

	public String[] main(String sentence) throws Exception {
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
        //testing(bayes, p);
        String[] output = new String[6];
        String[] words = sentence.split(" ");
        double[] probs = bayes.calcProbs(words);
		String answer = bayes.sentiment(probs);
		double[] probs2 = lr.calcProbs(words);
		String answer2 = lr.sentiment(probs);
		output[0] = answer;
		output[1] = probs[0]+"";
		output[2] = probs[1]+"";
		output[4] = answer2;
		output[5] = probs2[0]+"";
		output[6] = probs2[1]+"";
        return output;
	}
	public void testing(Bayes bayes, Preprocessing p) throws Exception {
        System.out.println("POSITIVE-------------");
		BufferedReader br = new BufferedReader(new FileReader("positiveTest.txt"));
		String rec = "";
		while((rec = br.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = bayes.calcProbs(words);
			String answer = bayes.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f\n",rec,answer,probs[0],probs[1]);
		}
		br.close();
		System.out.println("NEGATIVE-------------");
		BufferedReader br2 = new BufferedReader(new FileReader("negativeTest.txt"));
		while((rec = br2.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = bayes.calcProbs(words);
			String answer = bayes.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f \n",rec,answer,probs[0],probs[1]);
		}
		br2.close();
		System.out.println("NEUTRAL-------------");
		BufferedReader br3 = new BufferedReader(new FileReader("neutralTest.txt"));
		while((rec = br3.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = bayes.calcProbs(words);
			String answer = bayes.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f \n",rec,answer,probs[0],probs[1]);
		}
		br3.close();
		
	}
	
	public void testing(LogisticRegression lr, Preprocessing p) throws Exception {
        System.out.println("POSITIVE-------------");
		BufferedReader br = new BufferedReader(new FileReader("positiveTest.txt"));
		String rec = "";
		while((rec = br.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = lr.calcProbs(words);
			String answer = lr.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f \n",rec,answer,probs[0],probs[1]);
		}
		br.close();
		System.out.println("NEGATIVE-------------");
		BufferedReader br2 = new BufferedReader(new FileReader("negativeTest.txt"));
		while((rec = br2.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = lr.calcProbs(words);
			String answer = lr.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f \n",rec,answer,probs[0],probs[1]);
		}
		br2.close();
		System.out.println("NEUTRAL-------------");
		BufferedReader br3 = new BufferedReader(new FileReader("neutralTest.txt"));
		while((rec = br3.readLine())!=null) {
			String[] words = rec.split(" ");
			double[] probs = lr.calcProbs(words);
			String answer = lr.sentiment(probs);
			System.out.printf("%s | Sentiment: %s Positive: %.2f Negative: %.2f\n",rec,answer,probs[0],probs[1]);
		}
		br3.close();
		
	}

}
