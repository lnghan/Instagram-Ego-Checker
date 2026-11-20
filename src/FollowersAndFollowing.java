
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

// Yes, Instagram is this serious
public class FollowersAndFollowing {
	private ArrayList<String> followers = new ArrayList<>();
	private ArrayList<String> following = new ArrayList<>();
	private ArrayList<String> youEgoed = new ArrayList<>();
	private ArrayList<String> egoers = new ArrayList<>();
	

	private boolean readUsers(String filename, ArrayList<String> list) {
		if (filename == null) {
			return false;
		}

		try (Scanner fileScanner = new Scanner(new File(filename))) {
			while (fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine().trim();

				// Many lines in file, only need one with username
				if (currentLine.startsWith("\"title\":") || currentLine.startsWith("\"value\":")) {
					// Store usernames (usually after the colon in quotes)
					String username = currentLine.substring(currentLine.indexOf(':') + 1).trim();
					
					// Remove trailing commas
					if (username.endsWith(",")) {
						username = username.substring(0, username.length() - 1);
					}
					
					list.add(username);
				}
			} return true;
		} catch (FileNotFoundException e) {
			System.err.println("Error while opening file.");
			return false;
		}
	}

	public boolean loadFiles(String followersFile, String followingFile) {
		boolean first = readUsers(followersFile, followers);
		boolean second = readUsers(followingFile, following);
		return first && second;
	}
	
	public void compare() {
		youEgoed.clear();
		egoers.clear();
		
		// People you egoed
		for (int i = 0; i < followers.size(); i++) {
			String currentFollower = followers.get(i);
			if (!following.contains(currentFollower) && !youEgoed.contains(currentFollower)) {
				youEgoed.add(currentFollower);
			}
		}
		
		// People who egoed you
		for (int i = 0; i < following.size(); i++) {
			String currentUser = following.get(i);
			if (!followers.contains(currentUser) && !egoers.contains(currentUser)) {
				egoers.add(currentUser);
			}
		}
	}
	
	// Getters --------------------------------
		
	public ArrayList<String> getFollowers() {
		return followers;
	}
	
	public ArrayList<String> getFollowing() {
		return following;
	}
	
	public String getWhoYouEgoed() {
		System.out.println("Who you egoed:\n");
		String result = "";
		for (int i = 0; i < youEgoed.size(); i++) {
			result += (youEgoed.get(i)) + "\n";
		}
		return result;
	}
	
	public String getWhoEgoedYou() {
		System.out.println("Who egoed you:\n");
		String result = "";
		for (int i = 0; i < egoers.size(); i++) {
			result += (egoers.get(i)) + "\n";
		}
		return result;
	}
	
}