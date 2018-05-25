package comp3350.entertainment_tracker.tests.business;

import junit.framework.TestCase;

import org.junit.*;

import java.util.ArrayList;

import comp3350.entertainment_tracker.business.Sorter;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class SorterTest extends TestCase {
    private ArrayList<EntertainmentItem> orderedList;
    private Sorter sort;
    private int result;
    private final int ZERO = 0;

    public SorterTest(String arg0) { super(arg0); }

    @Before
    public void setUp() {
        orderedList = new ArrayList<>();
        sort = new Sorter();
    }

    @Test
    public void testSortEmpty() {
        ArrayList<EntertainmentItem> list = new ArrayList();
        orderedList = sort.sortItems(list, 1);

        EntertainmentItem tvList = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "", "", "", 0, 0);

        sort.sortOrder = 2;
        result = sort.EntertainmentTitle.compare(tvList, tvList);

        assertFalse(result != ZERO);
        assertTrue((orderedList.isEmpty()));
    }

    @Test
    public void testSortSame() {
        ArrayList<EntertainmentItem> list = new ArrayList<>();
        EntertainmentItem tvList = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "name", "image", "description", 0, 0);

        list.add(tvList);
        list.add(tvList);

        ArrayList<EntertainmentItem> list2 = new ArrayList<>(list);

        orderedList = sort.sortItems(list, 1);
        result = sort.EntertainmentTitle.compare(tvList, tvList);

        assertEquals(orderedList, list);
        assertTrue(orderedList.equals(list2));

        assertTrue(result == ZERO);
        assertEquals(ZERO, result);
    }

    @Test
    public void testAlreadySorted() {
        ArrayList<EntertainmentItem> list = new ArrayList<>();
        EntertainmentItem bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "aname", "", "", 0, 0);

        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "bname", "", "", 0, 0);
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "cname", "", "", 0, 0);
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "dname", "", "", 0, 0);
        list.add(bookList);


        ArrayList<EntertainmentItem> list2 = new ArrayList<>(list);
        orderedList = sort.sortItems(list, 1);

        assertTrue((orderedList.equals(list2)));

        list.clear();
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "cname", "", "", 0, 0);
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "bname", "", "", 0, 0);
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "aname", "", "", 0, 0);
        list.add(bookList);

        list2 = new ArrayList<>(list);
        orderedList = sort.sortItems(list, 1);

        assertFalse(orderedList.equals(list2));
        orderedList = sort.sortItems(list, 2);
        assertTrue(orderedList.equals(list2));
    }

    @Test
    public void testSortNull() {
        ArrayList<EntertainmentItem> list = null;

        try {
            sort.sortItems(list, 1);
            fail("Should've thrown an exception.");
        }
        catch(Exception e){}

        ArrayList<EntertainmentItem> list2 = new ArrayList<>();
        EntertainmentItem bookList = new EntertainmentItem(ZERO, null, null, null, 0, 0);

        list2.add(bookList);
        EntertainmentItem bookList2 = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "name", "image", "description", 0, 0);
        list2.add(bookList2);

        try {
            orderedList = sort.sortItems(list2, 1);
            result = sort.EntertainmentTitle.compare(bookList, bookList);
            fail("Should've thrown an exception.");
        }
        catch(Exception e){}
    }

    @Test
    public void testCaseInsensitiveSort() {
        ArrayList<EntertainmentItem> list = new ArrayList<>();
        EntertainmentItem movieList = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "sOmE nAmE", "ImaGe NAME", "description", 0, 0);

        list.add(movieList);
        movieList = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Some Name", "image name", "description", 0, 0);
        list.add(movieList);
        movieList = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "SoME NAME", "image name", "description", 0, 0);
        list.add(movieList);
        EntertainmentItem movieList2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "some name", "different image name", "description", 0, 0);
        list.add(movieList);

        ArrayList<EntertainmentItem> list2 = new ArrayList<>(list);
        orderedList = sort.sortItems(list, 1);
        result = sort.EntertainmentTitle.compare(movieList, movieList2);

        assertTrue(orderedList.equals(list2));
        orderedList = sort.sortItems(list, 2);
        assertTrue(orderedList.equals(list2));

        assertFalse(result != ZERO);
        assertEquals(ZERO, result);
    }

    @Test
    public void testSymbolsEquals() {
        ArrayList<EntertainmentItem> list = new ArrayList<>();
        EntertainmentItem movieList = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "!@3$5^^Movie Name", "image name", "description", 0, 0);

        list.add(movieList);
        EntertainmentItem movieList2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "12#4%66Movie Name", "different image name", "description", 0, 0);
        list.add(movieList2);
        result = sort.EntertainmentTitle.compare(movieList, movieList2);

        ArrayList<EntertainmentItem> list2 = new ArrayList<>(list);
        orderedList = sort.sortItems(list, 2);

        assertFalse(orderedList.equals(list2));
        assertFalse(result == ZERO);

        movieList = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "M@vi3 N#m3", "image name", "description", 0, 0);
        list.add(movieList);
        movieList2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "M@vi3 N#m3", "different image name", "description", 0, 0);
        list.add(movieList);

        list.clear();
        list2 = new ArrayList<>(list);
        orderedList = sort.sortItems(list, 2);
        sort.sortOrder = 2;
        result = sort.EntertainmentTitle.compare(movieList, movieList2);

        assertTrue(orderedList.equals(list2));
        assertTrue(result == ZERO);
    }

    @Test
    public void testPartialSort() {
        ArrayList<EntertainmentItem> list = new ArrayList<>();
        ArrayList<EntertainmentItem> list2 = new ArrayList<>();
        EntertainmentItem bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "aname", "image", "description", 0, 0);

        list.add(bookList);
        list2.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "bname", "image", "description", 0, 0);
        list.add(bookList);
        list2.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "wname", "image", "description", 0, 0);
        list.add(bookList);
        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "ename", "image", "description", 0, 0);
        list.add(bookList);
        list2.add(bookList);

        bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "wname", "image", "description", 0, 0);
        list2.add(bookList);

        orderedList = sort.sortItems(list, 1);
        assertTrue(orderedList.equals(list2));

        orderedList = sort.sortItems(list, 2);
        assertFalse(orderedList.equals(list2));
    }

    @Test
    public void testSortComparator() {

        EntertainmentItem bookList = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "aname", "image", "description", 0, 0);
        EntertainmentItem bookList2 = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "bname", "image", "description", 0, 0);

        //ascending
        result = sort.EntertainmentTitle.compare(bookList, bookList2);
        assertTrue(result < ZERO);

        result = sort.EntertainmentTitle.compare(bookList2, bookList);
        assertFalse(result == ZERO);
        assertTrue(result > ZERO);

        //descending
        sort.sortOrder = 2;
        result = sort.EntertainmentTitle.compare(bookList, bookList);
        assertEquals(ZERO, result);

        result = sort.EntertainmentTitle.compare(bookList, bookList2);
        assertFalse(result < ZERO);

        result = sort.EntertainmentTitle.compare(bookList2, bookList);
        assertFalse(result == ZERO);
        assertFalse(result > ZERO);

    }
}
