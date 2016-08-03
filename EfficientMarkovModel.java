
/**
 *EfficientMarkovModel by using HashMap
 * 
 * @author Vivian
 * @version 1.0
 */
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int charNum;
    private HashMap<String, ArrayList<String>> map;
	
	public EfficientMarkovModel(int num) {
		myRandom = new Random();
		charNum = num;
		map = new HashMap<String, ArrayList<String>>();
	}
	
	public void buildMap(String key) {
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
	    map.put(key, list);
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public ArrayList<String> getFollows(String key) {
	    if (map.containsKey(key)) return map.get(key);
	    else {
	         buildMap(key);
	         return map.get(key);
	    }
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
