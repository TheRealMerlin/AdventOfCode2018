import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.nio.file.Files;

import static java.lang.System.*;

public class Day4 {
	public static void main(String[] args) throws IOException {
		HashMap<String, TimeLog> guards = new HashMap<String, TimeLog>(); // Part 1
		List<String> input = Files.readAllLines(new File("day4.dat").toPath());
		Collections.sort(input);
		String currentGuard = "";
		int start = 0;
		for(String line : input) {
			int time = Integer.parseInt(line.substring(15, 17));
			if(line.contains("Guard")) {
				currentGuard = line.substring(line.indexOf("#"), line.indexOf(" ", line.indexOf("#")));
			}
			if(!guards.containsKey(currentGuard)) {
				TimeLog tl = new TimeLog();
				guards.put(currentGuard, tl);
			}
			if(line.contains("sleep")) {
				start = time;
			}
			if(line.contains("wake")) {
				for(int i = start; i < time; i++) {
					TimeLog tl = guards.get(currentGuard);
					tl.addToMinute(i);
				}
			}
		}
		Iterator<Entry<String, TimeLog>> it = guards.entrySet().iterator();
		String bestGuard = "";
		int bestSum = 0;
		while(it.hasNext()) {
			Entry<String, TimeLog> entry = it.next();
			int sum = entry.getValue().getSum();
			if(sum > bestSum) {
				bestSum = sum;
				bestGuard = entry.getKey();
			}
		}
		int bestMinute = guards.get(bestGuard).getMaxMinute()[0];
		out.println(Integer.parseInt(bestGuard.substring(1)) * bestMinute); // End of Part 1
		it = guards.entrySet().iterator(); // Part 2
		String mostSleptGuard = "";
		int[] mostSlept = new int[2];
		while(it.hasNext()) {
			Entry<String, TimeLog> entry = it.next();
			if(entry.getValue().getMaxMinute()[1] > mostSlept[1]) {
				mostSleptGuard = entry.getKey();
				mostSlept = entry.getValue().getMaxMinute();
			}
		}
		out.println(Integer.parseInt(mostSleptGuard.substring(1)) * mostSlept[0]); // End of Part 2
	}
}

class TimeLog {
	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public TimeLog() {
		for(int i = 0; i < 60; i++) {
			this.map.put(i, 0);
		}
	}
	
	public void addToMinute(Integer id) {
		this.map.put(id, this.map.get(id)+1);
	}
	
	public int getSum() {
		int sum = 0;
		Iterator<Map.Entry<Integer, Integer>> it = this.map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			sum += pair.getValue();
		}
		return sum;
	}
	
	public int[] getMaxMinute() {
		int maxKey = 0;
		int maxValue = 0;
		Iterator<Map.Entry<Integer, Integer>> it = this.map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			if(pair.getValue() > maxValue) {
				maxKey = pair.getKey();
				maxValue = pair.getValue();
			}
		}
		int[] arr = {maxKey, maxValue};
		return arr;
	}
}
