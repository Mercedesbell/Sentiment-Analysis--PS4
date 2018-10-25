import java.util.ArrayList;

public class TFIDF {

	public double tf(  ArrayList<String> words, String word ) throws Exception {
		
		double count = 0;
		for ( String w : words ) {
			if(word.equalsIgnoreCase(w)) {
				count++;
			}
		}	
		return (double) ( count / words.size() );
	}
	
	public double idf( ArrayList<String> positiveList, ArrayList<String> negativeList, ArrayList<String> neutralList, String word ) throws Exception {
		double count = 0;
		
		if ( positiveList.contains(word) ) {
			count++;
		}		
		if ( negativeList.contains(word) ) {
			count++;
		}
		if ( neutralList.contains(word) ) {
			count++;
		}
		return (double) Math.log( 3 / count);
	}
	
	public double tfidf(ArrayList<String> positiveList, ArrayList<String> negativeList,ArrayList<String> neutralList, String w, int emotion) throws Exception {
		if(emotion == -1) {
			
			return tf(negativeList, w) * idf(positiveList, negativeList, neutralList, w);
			
		} else if(emotion == 0) {
			
			return tf(neutralList, w) * idf(positiveList, negativeList, neutralList, w);
			
		} else {
			
			return tf(positiveList, w) * idf(positiveList, negativeList, neutralList, w);
			
		}
		
		
	}
	

}
