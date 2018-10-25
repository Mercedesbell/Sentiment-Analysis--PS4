import java.util.ArrayList;

public class TFIDF {

	public double tf(  ArrayList<ArrayList<String>> words, String word ) throws Exception {
		
		double count = 0;
		for ( ArrayList<String> line : words ) {
			for(String w : line) {
				if(word.equalsIgnoreCase(w)) {
					count++;
				}
			}
		}	
		return  ( (double)count / words.size() );
	}
	
	public double idf( ArrayList<ArrayList<String>> words, String word ) throws Exception {
		double count = 0;
		for(ArrayList<String> list : words) {
			if ( list.contains(word) ) {
				count++;
			}	
		}
		return Math.log( (double)words.size() / count);
	}
	
	public double tfidf(ArrayList<ArrayList<String>> words, String w) throws Exception {
		return tf(words, w) * idf(words, w);
	}
	

}
