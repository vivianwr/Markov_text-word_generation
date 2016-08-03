/**
 * Generate random text by giving the number of order
 * the larger order, the more like-a-word
 * 
 * @author Vivian
 * @version 08/03/2016
 */
import java.util.*;

public class MarkovModel {
    private String myText;
	private Random myRandom;
	private int charNum;
	
	public MarkovModel(int num) {
		myRandom = new Random();
		charNum = num;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public ArrayList<String> getFollows(String key) {
	    ArrayList<String> list = new ArrayList<>();
	    int pos = 0;
	    while(pos < myText.length()) {
	        int start = myText.indexOf(key, pos);
	        if (start == -1) break;
	        if (start + key.length() >= myText.length()) break;
	        String next = myText.substring(start + key.length(), start + key.length() + 1);
	        list.add(next);
	        pos = start + key.length();
	    }
	    return list;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-charNum);
		String key = myText.substring(index, index+charNum);
		sb.append(key);
		
		for(int k=0; k < numChars-charNum; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) break;
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		
		return sb.toString();
	}

}
