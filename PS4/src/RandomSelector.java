import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class RandomSelector {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("positive.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("negative.txt"));
		BufferedReader br3 = new BufferedReader(new FileReader("neutral.txt"));
		FileWriter fw = new FileWriter("positiveTest.txt");
		FileWriter fw2 = new FileWriter("negativeTest.txt");
		FileWriter fw3 = new FileWriter("neutralTest.txt");
		FileWriter fw4 = new FileWriter("positiveTraining.txt");
		FileWriter fw5 = new FileWriter("negativeTraining.txt");
		FileWriter fw6 = new FileWriter("neutralTraining.txt");
		ArrayList<String> posWords = new ArrayList<String>();
		ArrayList<String> negWords = new ArrayList<String>();
		ArrayList<String> neutralWords = new ArrayList<String>();
		ArrayList<String> posTest = new ArrayList<String>();
		ArrayList<String> negTest = new ArrayList<String>();
		ArrayList<String> neutralTest = new ArrayList<String>();
		String line = "";
		while((line = br.readLine()) !=null) {
			posWords.add(line);
		}
		while((line = br2.readLine()) !=null) {
			negWords.add(line);
		}
		while((line = br3.readLine()) !=null) {
			neutralWords.add(line);
		}
		br.close();
		br2.close();
		br3.close();
		for(int i=0; i<(.2 * posWords.size());i++) {
			int rand = (int) ((Math.random())*posWords.size());
			posTest.add(posWords.get(rand));
			posWords.remove(rand);
		}
		for(int i=0; i<(.2 * negWords.size());i++) {
			int rand = (int) ((Math.random())*negWords.size());
			negTest.add(negWords.get(rand));
			negWords.remove(rand);
		}
		for(int i=0; i<(.2 * neutralWords.size());i++) {
			int rand = (int) ((Math.random())*neutralWords.size());
			neutralTest.add(neutralWords.get(rand));
			neutralWords.remove(rand);
		}
		for(String word : posWords) {
			fw4.write(word + "\n");
		}
		for(String word : negWords) {
			fw5.write(word + "\n");
		}
		for(String word : neutralWords) {
			fw6.write(word + "\n");
		}
		for(String word : posTest) {
			fw.write(word + "\n");
		}
		for(String word : negTest) {
			fw2.write(word + "\n");
		}
		for(String word : neutralTest) {
			fw3.write(word + "\n");
		}
		fw.close();
		fw2.close();
		fw3.close();
		fw4.close();
		fw5.close();
		fw6.close();
	}
}
