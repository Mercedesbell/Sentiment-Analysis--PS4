import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocessing {
	
	public ArrayList<String> positiveList;
	public ArrayList<String> negativeList;
	public ArrayList<String> neutralList;

	public ArrayList<String> posOutput = new ArrayList<String>();
	public ArrayList<String> negOutput = new ArrayList<String>();
	public ArrayList<String> neutralOutput = new ArrayList<String>();
	
	public ArrayList<String> readFile( File f ) throws Exception {
		ArrayList<String> words = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String rec;
		
		
		while ( (rec = br.readLine() ) != null ) {
			String[] splitWords = rec.split(" ");
			for( String wordsInput : splitWords) {
				words.add(wordsInput);				
			}
		}
		
		br.close();
		
		return words;
	}
	
	public void tfidf(String w, int emotion) throws Exception {
		
		double threshold = 0.0;
		
		TFIDF tfidf = new TFIDF();
		
		double weight = tfidf.tfidf(positiveList, negativeList, neutralList, w, emotion);
	
		if( emotion == 1 ) {
			if(threshold != weight)
				posOutput.add(w);
		}
		if( emotion == -1 ) {
			if(threshold != weight)
				negOutput.add(w);
		}
		if( emotion == 0 ) {
			if(threshold != weight)
				neutralOutput.add(w);
		}
	}
	
	
	public void loadFile() throws IOException {
		BufferedWriter bw  = new BufferedWriter(new FileWriter("posOutput.txt"));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("negOutput.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("neutralOutput.txt"));


		for( String w : posOutput ) { 
			bw.write(w);
			bw.newLine();
		}
		for( String w : negOutput ) { 
			bw1.write(w);
			bw1.newLine();
		}
		for( String w : neutralOutput ) { 
			bw2.write(w);
			bw2.newLine();
		}
		
		bw.flush();
		bw.close();
		
		bw1.flush();
		bw1.close();
		
		bw2.flush();
		bw2.close();
		
	}

}
