import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class LogisticRegression {
	HashMap<String, Integer> mapPos;
	HashMap<String, Integer> mapNeg;
	boolean negation = false;
	boolean intensifier = false;
	
	public String sentiment(String sentence) throws Exception {
		loadHashMap();
		String answer ="neutral";
		String[] words = sentence.split(" ");
		double[] probs = new double[2];
		int posSum = 0;
		int negSum = 0;
		for( String word : words) {
			word = word.toLowerCase();
			posSum += sumOfWeight(word, 1);
			negSum += sumOfWeight(word, -1);
			if(word.equals("not")) {
				negation = true;
			}
			if(word.equals("very") || word.equals("really")) {
				intensifier = true;
			}
		}
		
		
		argmax ( sum ( log (  P (y ^ )) ) - sum ( w^2 ) )  
		
		probs[0] = (double)posSum / (posSum + negSum);
		probs[1] = (double)negSum / (posSum + negSum);
		if(probs[0] > probs[1])
			answer = "positive";
		if(probs[1] > probs[0])
			answer = "negative";
		String output = "Sentiment: " + answer + " Positive: " + probs[0] + " Negative: " + probs[1];
		return output;
	}
	
	public int sumOfWeight(String word, int emotion) {
		int weight = 0;
		
		if(emotion == -1) {
			if(mapNeg.containsKey(word)) {
				weight = mapNeg.get(word);
				if(negation) {
					weight *= -1;
					negation = false;
				}
				if(intensifier) {
					weight *= 2;
					intensifier = false;
				}
			}
		} else if ( emotion == 1 ) {
			if(mapPos.containsKey(word)) {
				weight = mapPos.get(word);
				if(negation) {
					weight *= -1;
					negation = false;
				}
				if(intensifier) {
					weight *= 2;
					intensifier = false;
				}
			}
		}
		
		return weight;
	}
	
	public void loadHashMap() throws Exception{
		mapPos = new HashMap<>();
			
		BufferedReader br = new BufferedReader(new FileReader("posFeatures.txt"));
		String line = "";
		while((line = br.readLine())!=null) {
			String[] words = line.split(" ");
			mapPos.put(words[0], Integer.parseInt(words[1]) );
		}
		br.close();
		
		mapNeg = new HashMap<>();
		BufferedReader br2 = new BufferedReader(new FileReader("negFeatures.txt"));
		while((line = br2.readLine())!=null) {
			String[] words = line.split(" ");
			mapNeg.put( words[0], Integer.parseInt(words[1]) );
		}
		br2.close();
	}

}
