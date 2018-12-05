import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Day1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("day1.dat")); // Part 1
		int sum = 0;
		while(in.hasNext()) {
			String str = in.next();
			if(str.charAt(0) == '+') {
				sum += Integer.parseInt(str.substring(1));
			}else {
				sum -= Integer.parseInt(str.substring(1));
			}
		}
		out.println(sum); // End of Part 1
		long start = System.currentTimeMillis();
		HashSet<Integer> set = new HashSet<Integer>(); // Part 2
		int cFreq = 0;
		do {
			in = new Scanner(new File("day1.dat"));
			while(in.hasNext()) {
				set.add(cFreq);
				String str = in.next();
				if(str.charAt(0) == '+') {
					cFreq += Integer.parseInt(str.substring(1));
				}else {
					cFreq -= Integer.parseInt(str.substring(1));
				}
				if(set.contains(cFreq)) {
					break;
				}
			}
		}while(!set.contains(cFreq));
		out.println(cFreq); // End of Part 2
		long end = System.currentTimeMillis();
		long totalTime = end - start;
		out.println(totalTime + " ms");
		in.close();
	}
}
