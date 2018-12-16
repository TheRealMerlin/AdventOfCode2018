import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import static java.lang.System.*;

public class Day7 {
	public static HashMap<String, ArrayList<String>> toBeCompleted = new HashMap<String, ArrayList<String>>();
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("day7.dat"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String value = line.substring(5, 6);
			String key = line.substring(36, 37);
			if(!toBeCompleted.containsKey(key)) {
				ArrayList<String> newArr = new ArrayList<String>();
				newArr.add(value);
				toBeCompleted.put(key, newArr);
			}else {
				toBeCompleted.get(key).add(value);
			}
		}
		HashSet<String> toBeDone = findFirst();
		String inOrder = "";
		while(inOrder.length() < 26) {
			Iterator<String> it = toBeDone.iterator();
			String doneValue = it.next();
			removeValue(doneValue);
			toBeDone.remove(doneValue);
			inOrder += doneValue;
			ArrayList<String> newToBeDone = findNewValue();
			for(String a : newToBeDone) {
				toBeDone.add(a);
			}
		}
		out.println(inOrder);
		in.close();
	}
	
	public static HashSet<String> findFirst() {
		HashSet<String> first = new HashSet<String>();
		for(int i = 'A'; i <= 'Z'; i++) {
			if(!toBeCompleted.containsKey(Character.toString((char)i))) {
				first.add(Character.toString((char)i));
			}
		}
		return first;
	}
	
	public static void removeValue(String doneValue) {
		Iterator<Entry<String, ArrayList<String>>> it = toBeCompleted.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, ArrayList<String>> pair = it.next();
			pair.getValue().remove(doneValue);
		}
	}
	
	public static ArrayList<String> findNewValue() {
		ArrayList<String> dynList = new ArrayList<String>();
		ArrayList<String> toRemove = new ArrayList<String>();
		Iterator<Entry<String, ArrayList<String>>> it = toBeCompleted.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, ArrayList<String>> pair = it.next();
			if(pair.getValue().isEmpty()) {
				dynList.add(pair.getKey());
			}
		}
		for(String a : toRemove) {
			toBeCompleted.remove(a);
		}
		return dynList;
	}
}