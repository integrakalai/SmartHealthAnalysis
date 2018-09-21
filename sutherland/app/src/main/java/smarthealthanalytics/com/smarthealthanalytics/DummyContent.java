package smarthealthanalytics.com.smarthealthanalytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyContent {
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

// Note that the class currently adds three items in the form of strings that read “Item 1”, “Item 2” and “Item 3”:

    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();



    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);

    }

    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
