package comp3350.entertainment_tracker.tests.objects;

import junit.framework.TestCase;
import org.junit.Test;

import comp3350.entertainment_tracker.objects.EntertainmentItem;


public class EntertainmentItemTest extends TestCase {

    public EntertainmentItemTest(String arg0) { super(arg0); }

    @Test
    public void testNullEquals() {
        EntertainmentItem item1 = null;
        EntertainmentItem item2 = new EntertainmentItem(0, "Some Name", "image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "movie name", null, "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "movie name", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(0, null, null, "description", 0, 0);
        item2 = new EntertainmentItem(0, null, "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));
    }

    @Test
    public void testCaseInsensitiveEquals() {
        EntertainmentItem item1 = new EntertainmentItem(0, "sOmE nAmE", "ImaGe NAME", "description", 0, 0);
        EntertainmentItem item2 = new EntertainmentItem(0, "Some Name", "image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(0, "SOME NAME", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(0, "some name", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(0, "SoME NAME", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(0, "some name", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));
    }

    @Test
    public void testEmptyEquals() {
        EntertainmentItem item1 = new EntertainmentItem(0, "", "image name", "description", 0, 0);
        EntertainmentItem item2 = new EntertainmentItem(0, "", "image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(0, " ", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(0, " ", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(0, "", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(0, " ", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(0, "  ", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(0, " ", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));
    }

    @Test
    public void testTypoEquals() {
        EntertainmentItem item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, " A movie name", "image name", "description", 0, 0);
        EntertainmentItem item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "A movie name", "image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Some movvie", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Some movie", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Anothermovie", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Another movie", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Yet Another Movie ", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Yet Another Movie", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));
    }

    @Test
    public void testSymbolsEquals() {
        EntertainmentItem item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "@ movie name", "image name", "description", 0, 0);
        EntertainmentItem item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "@ movie name", "image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "M@vi3 N#m3", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "M@vi3 N#m3", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "!@3$5^^Movie Name", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "12#4%66Movie Name", "different image name", "description", 0, 0);
        assertFalse(item2.equals(item1));

        item1 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Yet_Another M@vie", "image name", "description", 0, 0);
        item2 = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Yet_Another M@vie", "different image name", "description", 0, 0);
        assertTrue(item2.equals(item1));
    }
}
