package comp3350.entertainment_tracker.tests.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.entertainment_tracker.business.ItemSearch;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class ItemSearchTest extends TestCase {
	private ArrayList<EntertainmentItem> searchList;
	private String searchQuery;
	private ArrayList<EntertainmentItem> matchingList;

	@Before
	public void setUp() {
		searchList = new ArrayList<>();
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "The Matrix", "thematrix", "", 0));
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "1984", "1984", "", 0));
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Breaking Bad", "breakingbad", "", 0));
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Frozen", "frozen", "", 0));
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "The Avengers", "theavengers", "", 0));

		searchQuery = "";
	}

	@Test
	public void testSearchEmptyList() {
		ArrayList<EntertainmentItem> emptyList = new ArrayList<>();
		searchQuery = "testing";

		matchingList = ItemSearch.searchListForSubstring(emptyList, searchQuery);
		assertNotNull(matchingList);
		assertTrue(matchingList.isEmpty());
	}

	@Test
	public void testSearchNullList() {
		searchQuery = "testing";

		matchingList = ItemSearch.searchListForSubstring(null, searchQuery);
		assertNotNull(matchingList);
		assertTrue(matchingList.isEmpty());
	}

	@Test
	public void testSearchEmptyString() {
		searchQuery = "";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertNotNull(matchingList);
		assertTrue(matchingList.isEmpty());
	}

	@Test
	public void testSearchNullString() {
		matchingList = ItemSearch.searchListForSubstring(searchList, null);
		assertNotNull(matchingList);
		assertTrue(matchingList.isEmpty());
	}

	@Test
	public void testExactMatch() {
		ArrayList<EntertainmentItem> simpleList = new ArrayList<>();
		simpleList.add(new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "The Matrix", "thematrix", "", 0));
		searchQuery = "The Matrix";

		matchingList = ItemSearch.searchListForSubstring(simpleList, searchQuery);
		assertEquals(1, matchingList.size());
		assertEquals("The Matrix", matchingList.get(0).getItemName());

		// test against a list with more elements
		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(1, matchingList.size());
		assertEquals("The Matrix", matchingList.get(0).getItemName());
	}

	@Test
	public void testSubstringSearch() {
		searchQuery = "i";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(2, matchingList.size());
		assertEquals("The Matrix", matchingList.get(0).getItemName());
		assertEquals("Breaking Bad", matchingList.get(1).getItemName());

		searchQuery = "1";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(1, matchingList.size());
		assertEquals("1984", matchingList.get(0).getItemName());

		searchQuery = "The";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(2, matchingList.size());
		assertEquals("The Matrix", matchingList.get(0).getItemName());
		assertEquals("The Avengers", matchingList.get(1).getItemName());
	}

	@Test
	public void testQueryDifferentCase() {
		searchQuery = "the avengers";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(1, matchingList.size());
		assertEquals("The Avengers", matchingList.get(0).getItemName());
	}

	@Test
	public void testSearchNotFound() {
		searchQuery = "Harry Potter";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(0, matchingList.size());

		searchQuery = "The Matrix was a pretty good anime";

		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(0, matchingList.size());
	}

	@Test
	public void testSearchFoundDuplicate() {
		searchList.add(new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "The Matrix", "thematrix", "", 0));

		searchQuery = "The Matrix";
		matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		assertEquals(2, matchingList.size());
		assertEquals("The Matrix", matchingList.get(0).getItemName());
		assertEquals("The Matrix", matchingList.get(1).getItemName());
	}
}
