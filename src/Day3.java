import java.util.*;
import java.io.*;
import static java.lang.System.*;

public class Day3 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("day3.dat")); // Part 1
		String[][] arr = new String[1000][1000];
		for(int i = 0; i < 1000; i++) {
			for(int j = 0; j < 1000; j++) {
				arr[i][j] = "";
			}
		}
		while(in.hasNext()) {
			String id = in.next().substring(1);
			in.next(); // skips @
			String[] loc = in.next().split(",");
			for(int i = 0; i < loc.length; i++) {
				loc[i] = loc[i].replaceAll(":", "");
			}
			String[] size = in.next().split("x");
			int col = Integer.parseInt(loc[0]);
			int row = Integer.parseInt(loc[1]);
			int xLength = Integer.parseInt(size[0]);
			int yLength = Integer.parseInt(size[1]);
			for(int i = 0; i < yLength; i++) {
				for(int j = 0; j < xLength; j++) {
					if(arr[row+i][col+j].equals("")) {
						arr[row+i][col+j] = id;
					}else {
						arr[row+i][col+j] = "X";
					}
				}
			}
		}
		int amtOverlap = 0;
		for(String[] a: arr) {
			for(String b: a) {
				if(b.equals("X")) {
					amtOverlap++;
				}
			}
		}
		out.println(amtOverlap); // End of Part 1
		in = new Scanner(new File("day3.dat")); // Part 2
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		while(in.hasNext()) {
			String id = in.next().substring(1);
			boolean isOverlapping = false;
			in.next(); // skips @
			String[] loc = in.next().split(",");
			for(int i = 0; i < loc.length; i++) {
				loc[i] = loc[i].replaceAll(":", "");
			}
			String[] size = in.next().split("x");
			int col = Integer.parseInt(loc[0]);
			int row = Integer.parseInt(loc[1]);
			int xLength = Integer.parseInt(size[0]);
			int yLength = Integer.parseInt(size[1]);
			for(int i = 0; i < yLength; i++) {
				for(int j = 0; j < xLength; j++) {
					if(arr[row+i][col+j].equals("X")) {
						isOverlapping = true;
						break;
					}
				}
				if(isOverlapping) {
					break;
				}
			}
			map.put(id, isOverlapping);
		}
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if((boolean)pair.getValue() == false) {
				out.println(pair.getKey()); // End of Part 2
			}
		}
		in.close();
	}
}
