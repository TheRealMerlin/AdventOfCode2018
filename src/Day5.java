import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Day5 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("day5.dat")); // Part 1
		LinkedList<Character> list = new LinkedList<Character>();
		String input = in.next();
		in.close();
		for(int i = 0; i < input.length(); i++) {
			list.add(input.charAt(i));
		}
		out.println(react(list)); // End of Part 1
		Character c = 'A';
		int bestSize = input.length();
		while(c <= 'Z') {
			list.clear();
			for(int i = 0; i < input.length(); i++) {
				if(input.charAt(i) != c && input.charAt(i) != (char)(c + 32)) {
					list.add(input.charAt(i));
				}
			}
			int listSize = react(list);
			if(listSize < bestSize) {
				bestSize = listSize;
			}
			c++;
		}
		out.println(bestSize); // End of Part 2
	}
	
	public static int react(LinkedList<Character> list) {
		int index = 0;
		while(index < list.size()-1) {
			if(Math.abs(list.get(index) - list.get(index+1)) == 32) {
				list.remove(index);
				list.remove(index);
				if(index != 0) {
					index--;
				}
			}else {
				index++;
			}
		}
		return list.size();
	}
}
