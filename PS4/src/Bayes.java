import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Bayes {
	//Currently only uses Unigrams
	 HashMap<String, Integer> mapPos; 
	 HashMap<String, Integer> mapNeg; 
	 HashMap<String, Integer> mapNeutral;
	 int posTotal = 0;
	 int negTotal = 0;
	 int neutralTotal = 0;
	
	public String sentiment(String sentence) throws Exception {
		loadHashMap();
		String[] words = sentence.split(" ");
		double[] probs = calcProbs(words);
		String answer = "";
		if(probs[0] > probs[1] && probs[0] > probs[2])
			answer = "positive";
		if(probs[1] > probs[0] && probs[1] > probs[2])
			answer = "negative";
		if(probs[2] > probs[0] && probs[2] > probs[1])
			answer = "neutral";
		String output = "Sentiment: " + answer + " Positive: " + probs[0] + " Negative: " + probs[1] + " Neutral: " + probs[2];
		return output;
	}
	
	public double[] calcProbs(String[] words) {
		//For the probabilities, I'm not sure on some of the parts if it wants the total of Unique words, 
		// or the total of every word. 
		//posTotal is all positive words while mapPos.size() is all unique positive words
		
		double[] probs = new double[3];
		double probPos = ((double)posTotal / (posTotal + negTotal + neutralTotal ));
		double probNeg = ((double)negTotal / (posTotal + negTotal + neutralTotal ));
		double probNeutral = ((double)neutralTotal / (posTotal + negTotal + neutralTotal ));
// 		or this?		
//		double probPos = (double)(mapPos.size() / (mapPos.size() + mapNeg.size() + mapNeutral.size()) );
//		double probNeg = (double)(mapNeg.size() / (mapPos.size() + mapNeg.size() + mapNeutral.size()) );
//		double probNeutral = (double)(mapNeg.size() / (mapPos.size() + mapNeg.size() + mapNeutral.size()) );
		
		for (String word : words) {
			if(mapPos.containsKey(word))
				probPos *= (double)(mapPos.get(word)+1.0)/(posTotal + mapPos.size()); //with smoothing
			else
				probPos *= (double)(1.0)/(posTotal + mapPos.size());
			if(mapNeg.containsKey(word))
				probNeg *= (double)(mapNeg.get(word)+1.0)/(negTotal + mapNeg.size());
			else
				probNeg *= (double)(1.0)/(negTotal + mapNeg.size());
			if(mapNeutral.containsKey(word))
				probNeutral *= (double)(mapNeutral.get(word)+1.0)/(neutralTotal + mapNeutral.size());
			else
				probNeutral *= (double)(1.0)/(neutralTotal + mapNeutral.size());
		}
		probs[0] = (probPos/(probPos + probNeg + probNeutral));
		probs[1] = (probNeg/(probPos + probNeg + probNeutral));
		probs[2] = (probNeutral/(probPos + probNeg + probNeutral));
		return probs;
	}
	
	public void loadHashMap() throws Exception{
		mapPos = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader("posOutput.txt"));
		String key = "";
		while((key = br.readLine())!=null) {
			posTotal++;
			if(mapPos.containsKey(key)) {
				mapPos.put(key, mapPos.get(key) + 1);
			} else {
				mapPos.put(key,1);
			}
		}
		br.close();
		
		mapNeg = new HashMap<>();
		BufferedReader br2 = new BufferedReader(new FileReader("negOutput.txt"));
		while((key = br2.readLine())!=null) {
			negTotal++;
			if(mapNeg.containsKey(key)) {
				mapNeg.put(key, mapNeg.get(key) + 1);
			} else {
				mapNeg.put(key,1);
			}
		}
		br2.close();
		
		mapNeutral = new HashMap<>();
		BufferedReader br3 = new BufferedReader(new FileReader("neutralOutput.txt"));
		while((key = br3.readLine())!=null) {
			neutralTotal++;
			if(mapNeutral.containsKey(key)) {
				mapNeutral.put(key, mapNeutral.get(key) + 1);
			} else {
				mapNeutral.put(key,1);
			}
		}
		br3.close();
	}

}
