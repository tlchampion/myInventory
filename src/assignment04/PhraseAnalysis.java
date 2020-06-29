package assignment04;

import java.io.IOException;

public class PhraseAnalysis  {
	
//declare attributes
	private String phrase;  //phrase as provided to constructor
	private String lowerCasePhrase; //phrase after being reduced to lower case and having non-letter characters removed
	private double score; //sum of the frequency of each letter in the phrase

	
	public PhraseAnalysis(String phrase) throws IOException {
		this.phrase = phrase;
		
		this.lowerCasePhrase = phrase.replaceAll("[^A-Za-z]+", "").toLowerCase();
		this.score = calculateScore(lowerCasePhrase);
				
	}
	
	private double calculateScore(String lowerCasePhrase) throws IOException { //calculate frequency score for phrase
		double score = 0;
	
	
		
	
		
		double[] scores = loadScores();  //create array to hold scores for military grade decryption
		
		
	
		//obtain counts for the number of occurences of each letter in the phrase and save to an array
		int[] counts = new int[26];
		for(int i = 0; i < lowerCasePhrase.length(); i++) {
			char ch = lowerCasePhrase.charAt(i);
			if(Character.isLetter(ch)) {
				counts[ch - 'a']++;
				
				
			}
			
			}
		//convert letter counts to frequency values
		double[] frequency = new double[26];
		for(int i = 0; i < 26; i++) {
			frequency[i] = (double)counts[i]/lowerCasePhrase.length();
			
		}
		
		//score the phrase based upon the frequencies found in the phrase vs standard frequency for letter in the alphabet
		
		for(int i = 0; i < 26; i++) {
		
			score += Math.abs(frequency[i] - scores[i]);
		}

		
		return score;
	}
	


	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) throws IOException {
		this.phrase = phrase;
		this.lowerCasePhrase = phrase.replaceAll("[^A-Za-z]+", "").toLowerCase();
		this.score = calculateScore(lowerCasePhrase);
	}

	public String getLowerCasePhrase() {
		return lowerCasePhrase;
	}

	

	public double getScore() {
		return score;
	}
	
	private double[] loadScores() {
		double[] scores = new double[26];
		
		
		scores[0] = 0.0817;
		scores[1] = 0.0149;
		scores[2] = 0.0278;
		scores[3] = 0.0425;
		scores[4] = 0.127;
		scores[5] = 0.0223;
		scores[6] = 0.0202;
		scores[7] = 0.0609;
		scores[8] = 0.07;
		scores[9] = 0.0015;
		scores[10] = 0.0077;
		scores[11] = 0.0403;
		scores[12] = 0.0241;
		scores[13] = 0.0675;
		scores[14] = 0.0751;
		scores[15] = 0.0193;
		scores[16] = 0.001;
		scores[17] = 0.0599;
		scores[18] = 0.0633;
		scores[19] = 0.0906;
		scores[20] = 0.0276;
		scores[21] = 0.0098;
		scores[22] = 0.0236;
		scores[23] = 0.0015;
		scores[24] = 0.0197;
		scores[25] = 0.0007;
		
		
	
		
		
		
		return scores;
	}


	
	

}
