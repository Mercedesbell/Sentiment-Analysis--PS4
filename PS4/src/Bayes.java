import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Bayes {
	//Currently only uses Unigrams
	 HashMap<String, Integer> mapPos; 
	 HashMap<String, Integer> mapNeg; 
	 HashMap<String, Integer> mapNeutral;
	 ArrayList<String> wordsPos;
	 ArrayList<String> wordsNeg;
	 ArrayList<String> wordsNeutral;
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
		/*if(probs[2] > probs[0] && probs[2] > probs[1])
			answer = "neutral";
			*/
		String output = "Sentiment: " + answer + " Positive: " + probs[0] + " Negative: " + probs[1] /* + " Neutral: " + probs[2]*/;
		return output;
	}
	
	public double[] calcProbs(String[] words) {

		double[] probs = new double[3];
		double probPos = ((double)wordsPos.size()/ (wordsPos.size() + wordsNeg.size()  /*+ wordsNeutral.size()*/));
		double probNeg = ((double)wordsNeg.size() / (wordsPos.size() + wordsNeg.size() /*+ wordsNeutral.size()*/));
		//double probNeutral = ((double)wordsNeutral.size() / (wordsPos.size() + wordsNeg.size() + wordsNeutral.size()));

		
		for (String word : words) {
			if(mapPos.containsKey(word))
				probPos *= (double)(mapPos.get(word)+1.0)/(posTotal + mapPos.size()); //with smoothing
			else
				probPos *= (double)(1.0)/(posTotal + mapPos.size());
			if(mapNeg.containsKey(word))
				probNeg *= (double)(mapNeg.get(word)+1.0)/(negTotal + mapNeg.size());
			else
				probNeg *= (double)(1.0)/(negTotal + mapNeg.size());
			/*
			if(mapNeutral.containsKey(word))
				probNeutral *= (double)(mapNeutral.get(word)+1.0)/(neutralTotal + mapNeutral.size());
			else
				probNeutral *= (double)(1.0)/(neutralTotal + mapNeutral.size());
				*/
		}
		probs[0] = (probPos/(probPos + probNeg /*+ probNeutral*/));
		probs[1] = (probNeg/(probPos + probNeg /*+ probNeutral*/));
		//probs[2] = (probNeutral/(probPos + probNeg + probNeutral));
		return probs;
	}
	
	public void loadHashMap() throws Exception{
		mapPos = new HashMap<>();
		wordsPos = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader("posOutput.txt"));
		String line = "";
		while((line = br.readLine())!=null) {
			wordsPos.add(line);
			String[] words = line.split(" ");
			for(String key : words) {
				posTotal++;
				if(mapPos.containsKey(key)) {
					mapPos.put(key, mapPos.get(key) + 1);
				} else {
					mapPos.put(key,1);
				}
			}
		}
		br.close();
		
		mapNeg = new HashMap<>();
		wordsNeg = new ArrayList<>();
		BufferedReader br2 = new BufferedReader(new FileReader("negOutput.txt"));
		while((line = br2.readLine())!=null) {
			wordsNeg.add(line);
			String[] words = line.split(" ");
			for(String key : words) {
				negTotal++;
				if(mapNeg.containsKey(key)) {
					mapNeg.put(key, mapNeg.get(key) + 1);
				} else {
					mapNeg.put(key,1);
				}
			}
		}
		br2.close();
		/*
		mapNeutral = new HashMap<>();
		wordsNeutral = new ArrayList<>();
		BufferedReader br3 = new BufferedReader(new FileReader("neutralOutput.txt"));
		while((line = br3.readLine())!=null) {
			wordsNeutral.add(line);
			String[] words = line.split(" ");
			for(String key : words) {
				neutralTotal++;
				if(mapNeutral.containsKey(key)) {
					mapNeutral.put(key, mapNeutral.get(key) + 1);
				} else {
					mapNeutral.put(key,1);
				}
			}
		}
		br3.close();
		*/
	}

}


/*
 * boolean NEGATION = false;
 * boolean INTENSIFIER = false;
 * array[] words;
 * 
 *	for ( word){
 * 		if word == 'not';
 * 			NEGATION = true;
 * 		if(NEGATION && weight != 0){
 * 			word.weight * -1;
 * 			NEGATION = FALSE;
 * 		}
 * 	}
 * 
 * 
 * 
 * 
 */
