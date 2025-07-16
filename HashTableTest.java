public class HashTableTest {

    public static void main(String[] args) {
        testEmptyTable();
        testAddSingleItem();
        testAddMultipleItems();
        testContains();
        testRehash();
    }

    private static void testEmptyTable() {
        HashTable<String> table = new HashTable<>();
        boolean pass = !table.contains("test");
        printResult("testEmptyTable", pass);
    }

    private static void testAddSingleItem() {
        HashTable<String> table = new HashTable<>();
        table.add("apple");
        boolean pass = table.contains("apple");
        printResult("testAddSingleItem", pass);
    }

    private static void testAddMultipleItems() {
        HashTable<String> table = new HashTable<>();
        table.add("apple");
        table.add("banana");
        table.add("carrot");
        boolean pass = table.contains("apple") && table.contains("banana") && table.contains("carrot");
        printResult("testAddMultipleItems", pass);
    }

    private static void testContains() {
        HashTable<String> table = new HashTable<>();
        table.add("dog");
        table.add("cat");
        boolean pass = table.contains("dog") && !table.contains("elephant");
        printResult("testContains", pass);
    }

    private static void testRehash() {
        // Insert enough items to trigger rehashing (usage / capacity > 0.75)
        HashTable<Integer> table = new HashTable<>(2);
        table.add(1);
        table.add(2);
        table.add(3); // Should trigger rehash
        boolean pass = table.contains(1) && table.contains(2) && table.contains(3);
        printResult("testRehash", pass);
    }

    private static void printResult(String testName, boolean passed) {
        if (passed) {
            System.out.println(testName + ": PASSED");
        } else {
            System.out.println(testName + ": FAILED");
        }
    }
}
