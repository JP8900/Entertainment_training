package comp3350.entertainment_tracker.tests.persistence;

import java.util.ArrayList;

import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.persistence.DataAccess;

public class DataAccessStub implements DataAccess {
	private String dbName;
	private String dbType = "stub";

    private ArrayList<EntertainmentItem> itemList;
	private ArrayList<EntertainmentItem> watchedList;
	private ArrayList<EntertainmentItem> wishList;

	public DataAccessStub(String dbName) { this.dbName = dbName; }

	public void open(String dbName) {
		itemList = new ArrayList<>();
		watchedList = new ArrayList<>();
		wishList = new ArrayList<>();

        final int movieType = EntertainmentItem.TYPE_MOVIE;
		itemList.add(new EntertainmentItem(movieType, "Antman","antman","",0,0));
		itemList.add(new EntertainmentItem(movieType, "Animal Farm","animalfarm","",0,0));
		itemList.add(new EntertainmentItem(movieType, "Arrival","arrival","",0,0));
		itemList.add(new EntertainmentItem(movieType, "Deadpool", "deadpool", "A fast-talking mercenary with a morbid sense of humor is subjected to a rogue experiment that leaves him with accelerated healing powers and a quest for revenge", 0, 0));
        itemList.add(new EntertainmentItem(movieType, "Frozen", "frozen", "When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister, Anna, teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Captain Phillips", "captainphillips", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Finding Dory", "findingdory", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Get Smart", "getsmart", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Interstellar", "interstellar", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Life of Pi", "lifeofpi", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Moana", "moana", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Moneyball", "moneyball", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "Moonlight", "moonlight", "", 0, 0));
        itemList.add(new EntertainmentItem(movieType, "Pulp Fiction", "pulpfiction", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "The Great Gatsby", "thegreatgatsby", "", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "The Avengers", "theavengers", "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity", 0, 0));
		itemList.add(new EntertainmentItem(movieType, "The Matrix", "thematrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers", 0, 0));

        final int tvShowType = EntertainmentItem.TYPE_TVSHOW;

		itemList.add(new EntertainmentItem(tvShowType, "Arrested Development", "arresteddev", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Breaking Bad", "breakingbad", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Black Mirror", "blackmirror", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Community", "community", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Dexter", "dexter", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Futurama", "futurama", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Prison Break", "prisonbreak", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Silicon Valley", "siliconvalley", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "Scream", "scream", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "The Big Bang Theory", "bigbangtheory", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "The Office", "theoffice", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "The 100", "the100", "", 0, 0));
		itemList.add(new EntertainmentItem(tvShowType, "The Walking Dead", "thewalkingdead", "", 0, 0));

        final int bookType = EntertainmentItem.TYPE_BOOK;

		itemList.add(new EntertainmentItem(bookType, "1984", "the1984", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Animal Farm", "animalfarm", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Assassins Apprentice","assassinsapprentice", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "A Series of Unfortunate Events", "unfortunateevents", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "A Wrinkle In Time", "awrinkleintime", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Captain Underpants", "captainunderpants", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Catcher in the Rye", "catcherintherye", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Eragon", "eragon", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Fahrenheit 451", "fahrenheit451", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Huckleberry Finn", "huckleberry", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Hitchhiker's Guide to the Galaxy", "hitchhiker", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "The Hobbit", "thehobbit", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "The Hunger Games", "hungergames", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "To Kill A Mockingbird", "tokillamockingbird", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "Twilight", "twilight", "", 0, 0));
		itemList.add(new EntertainmentItem(bookType, "The Road", "theroad", "", 0, 0));

		System.out.println("Opened " +dbType +" database " +dbName);
	}

    private ArrayList<EntertainmentItem> getItemsOfType(int itemType) {
        ArrayList<EntertainmentItem> result = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemType() == itemType)
                result.add(itemList.get(i));
        }

        return result;
    }

    public ArrayList<EntertainmentItem> getFullItemList() { return itemList; }



	public ArrayList<EntertainmentItem> getBookList() { return getItemsOfType(EntertainmentItem.TYPE_BOOK); }
	public ArrayList<EntertainmentItem> getMovieList() { return getItemsOfType(EntertainmentItem.TYPE_MOVIE); }
	public ArrayList<EntertainmentItem> getTVShowList() { return getItemsOfType(EntertainmentItem.TYPE_TVSHOW); }

	public ArrayList<EntertainmentItem> getWishList() { return wishList;}
	public ArrayList<EntertainmentItem> getWatchedHistory() { return watchedList; }

	public String insertWatchedHistory(EntertainmentItem item) {

		if(item.getItemName() != null && item.getItemType() >= 0 && item.getItemType() <= 2)
			watchedList.add(item);

		return null;
	}
	public String insertWishList(EntertainmentItem item) {
		if(item.getItemName() != null && item.getItemType() >= 0 && item.getItemType() <= 2)
			wishList.add(item);

		return null;
	}

	public String removeWatchedHistory(EntertainmentItem item){
		watchedList.remove(item);

		return null;
	}

	@Override
	public String removeWishList(EntertainmentItem item) {
		wishList.remove(item);

		return null;
	}

	@Override
	public String setUserRating(EntertainmentItem item, float rating) {
		if(rating <=5f) {
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getItemName().equals(item.getItemName())) {
					itemList.get(i).setUserRating(rating);
				}
			}
		}
		return null;
	}
	public float getUserRating(EntertainmentItem item) {
		float tmp = 0;

		for(int i = 0; i<itemList.size();i++) {
			if(itemList.get(i).getItemName().equals(item.getItemName())) {
				tmp = itemList.get(i).getUserRating();
			}
		}
		return tmp;
	}

	public ArrayList<EntertainmentItem> getRandomItem(String name){
		ArrayList<EntertainmentItem> entertainmentItemArrayList = new ArrayList<>();
		EntertainmentItem entertainmentItem;

		for(int i=0; i<itemList.size();i++){
			if(itemList.get(i).getItemName().equals(name)){
				entertainmentItem = itemList.get(i);
				entertainmentItemArrayList.add(entertainmentItem);
			}
		}


		return entertainmentItemArrayList;
	}
	public float getAvgRating(EntertainmentItem item) {
		float tmp = 0;
		for(int i = 0; i<watchedList.size();i++) {
			if(watchedList.get(i).equals(item)) {
				tmp = watchedList.get(i).getAvgRating();
			}
		}
		return tmp;
	}

	public void close() { System.out.println("Closed " +dbType +" database " +dbName); }
}
