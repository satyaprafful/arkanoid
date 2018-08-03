import java.io.BufferedReader;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Highscores {
	public static TreeMap<Integer, ArrayList<String>> nameToScoreList = new TreeMap<>();
	private static final String filename = "src/Highscores.txt"; // File Information
	private static Integer highScore = 0;

	// Parses text file.
	// Finds the HighScore, called by the main(). Sets it accordingly
	// Adds all scores parsed from txtfile to TreeMap<Score, ArrayList<String>>
	public static void readFile() {
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader buffReader = new BufferedReader(reader);
			String line;
			while ((line = buffReader.readLine()) != null) {
				int posOfColon = line.indexOf(':');
				String name = line.substring(0, posOfColon);
				String score = line.substring(posOfColon + 2, line.length());
				Integer scoreVal = Integer.parseInt(score);
				highScore = Math.max(scoreVal, highScore);
				addNewScore(scoreVal, name);
			}
			buffReader.close();
		}

		catch (FileNotFoundException ex) {
			System.out.print("ERROR : Highscores File Not Found!");
		} catch (IOException e) {
			System.out.println("ERROR: IO Error ");
		}
	}

	// Writes the TreeMap to a textFile. We only manipulate Collections, and read
	// and write only once!
	// #Prettyclever!
	public static void writeFile() {
		try {
			FileWriter writer = new FileWriter(filename);
			Set<Integer> scores = nameToScoreList.keySet();
			for (Integer i : scores) {
				ArrayList<String> namelist = nameToScoreList.get(i);
				for (String s : namelist) {
					writer.write(s + ": " + i + "\n");
				}
			}
			writer.close();
		}

		catch (IOException ex) {
			System.out.println("ERROR: IO Error");
		}
	}

	public static int getHighScore() {
		return highScore;
	}

	// Adds scores. If score exists, gets the current namelist of those with the
	// same score, adds new name.
	public static void addNewScore(Integer scoreVal, String name) {
		ArrayList<String> names;
		if (!nameToScoreList.containsKey(scoreVal)) {
			names = new ArrayList<>();
			names.add(name);
			nameToScoreList.put(scoreVal, names);
		} else {
			names = nameToScoreList.get(scoreVal);
			names.add(name);
			nameToScoreList.put(scoreVal, names);
		}
	}

	// This function picks the top 5 scores and returns the entries as an ArrayList.
	// Ready to display.
	public static ArrayList<String> topFiveEntries() {
		NavigableMap<Integer, ArrayList<String>> scores = nameToScoreList.descendingMap();
		ArrayList<String> retArr = new ArrayList<String>();
		for (Entry<Integer, ArrayList<String>> entry : scores.entrySet()) {
			ArrayList<String> namelist = entry.getValue();
			for (String s : namelist) {
				String hsentry = s + ": " + entry.getKey() + "\n";
				if (retArr.size() < 5) {
					retArr.add(hsentry);
				} else {
					return retArr;
				}
			}
		}
		return retArr;
	}

	public static void setHighScore(int score) {
		highScore = score;
	}	
}