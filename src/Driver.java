
public class Driver {

	public static void main(String[] args) {
		FollowersAndFollowing helper = new FollowersAndFollowing();
		helper.loadFiles("hanFollowers.txt", "hanFollowing.txt");
		helper.compare();
	
		System.out.println("WILL SHOW DEACTIVATED/DELETED ACCOUNTS SO BE WARNED.\n");
		//System.out.println(helper.getWhoYouEgoed());
		//System.out.println(helper.getWhoEgoedYou());
	}

}
