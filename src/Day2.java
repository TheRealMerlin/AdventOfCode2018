import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Day2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("day2.dat")); // Part 1
		int twos = 0;
		int threes = 0;
		while(in.hasNext()) {
			String str = in.next();
			boolean[] twoThree = findLetterCount(str);
			if(twoThree[0] == true) {
				twos++;
			}
			if(twoThree[1] == true) {
				threes++;
			}
		}
		out.println(twos * threes); // End of Part 1
		in = new Scanner(new File("day2.dat")); // Part 2
		boolean isCommonFound = false;
		while(!isCommonFound) {
			String str = in.next();
			Scanner in2 = new Scanner(new File("day2.dat"));
			while(in2.hasNext()) {
				String str2 = in2.next();
				int differences = 0;
				String commons = "";
				for(int i = 0; i < str.length(); i++) {
					if(str.charAt(i) == str2.charAt(i)) {
						commons += str.charAt(i);
					}else {
						differences++;
					}
					if(differences > 1) {
						break;
					}
				}
				if(differences == 1) {
					isCommonFound = true;
					out.println(commons);
					break;
				}
			}
			in2.close();
		} // End of Part 2
		in.close();
	}
	
	public static boolean[] findLetterCount(String str) { // Part 1 Function
		boolean[] twoThree = new boolean[2];
		HashMap<String, Integer> letterCount = new HashMap<String, Integer>();
		for(int i = 0; i < str.length(); i++) {
			if(!letterCount.containsKey(str.substring(i, i+1))) {
				int count = 0;
				for(int j = 0; j < str.length(); j++) {
					if(str.substring(j, j+1).equals(str.substring(i, i+1))) {
						count++;
					}
				}
				letterCount.put(str.substring(i, i+1), count);
			}
		}
		if(letterCount.containsValue(2)) {
			twoThree[0] = true;
		}
		if(letterCount.containsValue(3)) {
			twoThree[1] = true;
		}
		return twoThree;
	}
	
	
}
