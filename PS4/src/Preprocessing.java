import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocessing {
	
	public ArrayList<ArrayList<String>> positiveList;
	public ArrayList<ArrayList<String>> negativeList;
	public ArrayList<ArrayList<String>> neutralList;

	public ArrayList<ArrayList<String>> posOutput = new ArrayList<>();
	public ArrayList<ArrayList<String>> negOutput = new ArrayList<>();
	public ArrayList<ArrayList<String>> neutralOutput = new ArrayList<>();
	
	public ArrayList<ArrayList<String>> readFile( File f ) throws Exception {
		ArrayList<ArrayList<String>> words = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(f));
		String rec;
		
		
		while ( (rec = br.readLine() ) != null ) {
			ArrayList<String> data = new ArrayList<>();
			String[] line = rec.split(" ");
			for(String word : line)
				data.add(word);
			words.add(data);
		}
		
		br.close();
		
		return words;
	}
	
	public void tfidf(ArrayList<ArrayList<String>> words, int emotion) throws Exception {
		
		double threshold = .11;
		TFIDF tfidf = new TFIDF();
		for(ArrayList<String> list : words) {
			ArrayList<String> passed = new ArrayList<>();
			for(String w : list) {
				double weight = tfidf.tfidf(words, w);
				//System.out.println(w + " " + weight + " " + emotion);
				if(threshold > weight && !w.equals(""))
					passed.add(w);		
			}

			if(!passed.isEmpty() && emotion == 1 ) {
				posOutput.add(passed);
			}
			if(!passed.isEmpty() && emotion == -1 ) {
				negOutput.add(passed);
			}
			if(!passed.isEmpty() && emotion == 0 ) {
				neutralOutput.add(passed);
			} 
		}
	}
	
	
	public void loadFile() throws IOException {
		BufferedWriter bw  = new BufferedWriter(new FileWriter("posOutput.txt"));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("negOutput.txt"));
		BufferedWriter bw2 = new BufferedWriter(new FileWriter("neutralOutput.txt"));

		for(ArrayList<String> words : posOutput) {
			for( String w : words ) { 
				bw.write(w + " ");
			}
			bw.newLine();
		}
		for(ArrayList<String> words : negOutput) {
			for( String w : words ) { 
				bw1.write(w + " ");
			}
			bw1.newLine();
		}for(ArrayList<String> words : neutralOutput) {
			for( String w : words ) { 
				bw2.write(w + " ");
			}
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
