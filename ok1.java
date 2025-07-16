/**
 * A simple hasht table is an array of linked lists. In its simplest form, a
 * linked list is represented by its first node. Typically we label this node as
 * "head". Here, however, we'll know it's the first node of the list because it
 * will be placed in an array element. For example, if we have 4 linked lists,
 * we know that the "head" of the third one can be found in position [2] of the
 * underlying array.
 */
public class nfs_HashTable<E extends Comparable<E>> {

    /**
     * Underlying array of nodes. Each non empty element of this array is the first
     * node of a linked list.
     */
    private Node<E>[] underlying;

    /** Counts how many places in the underlying array are occupied */
    private int usage;

    /** Counts how many nodes are stored in this hashtable */
    private int totalNodes;

    /** Tracks underlying array's load factor */
    private double loadFactor;

    /** Default size for the underlying array. */
    private static final int DEFAULT_SIZE = 4;

    /** Default load factor threshold for resizing */
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    /** Resize factor */
    private static final int RESIZE_BY = 2;

    /**
     * Basic constructor with user-specified size. If size is absurd, the
     * constructor will revert to the default size.
     */
    @SuppressWarnings("unchecked")
    public nfs_HashTable(int size) {
        if (size <= 0)
            size = DEFAULT_SIZE;
        this.underlying = (Node<E>[]) new Node[size];
        this.usage = 0;
        this.totalNodes = 0;
        this.loadFactor = 0.0;
    } // basic constructor

    /** Default constructor, passes default size to basic constructor */
    public nfs_HashTable() {
        this(DEFAULT_SIZE);
    } // default constructor

    /** Update the load factor */
    private void updateLoadFactor() {
        this.loadFactor = (double) this.usage / (double) this.underlying.length;
    } // method updateLoadFactor

    private Node<E>[] getUnderlying() {
        return this.underlying;
    }

    private double getLoadFactor() {
        return this.loadFactor;
    }

    private int getUsage() {
        return this.usage;
    }

    private int getTotalNodes() {
        return this.totalNodes;
    }

    private int hashPosition(E content) {
        return Math.abs(content.hashCode()) % this.underlying.length;
    }

    public boolean contains(E target) {
        boolean found = false;
        int expectedAt = this.hashPosition(target);
        Node<E> cursor = this.underlying[expectedAt];
        while (cursor != null && !found) {
            found = target.equals(cursor.getContent());
            cursor = cursor.getNext();
        }
        return found;
    }

    private void rehash() {
        nfs_HashTable<E> temporary = new nfs_HashTable<>(RESIZE_BY * this.underlying.length);
        for (Node<E> head : this.underlying) {
            Node<E> current = head;
            while (current != null) {
                temporary.add(current.getContent());
                current = current.getNext();
            }
        }
        this.underlying = temporary.getUnderlying();
        this.loadFactor = temporary.getLoadFactor();
        this.usage = temporary.getUsage();
        this.totalNodes = temporary.getTotalNodes();
    } // method rehash

    /**
     * Adds a node, with the specified content, to a linked list in the underlying
     * array.
     * 
     * @param content E The content of a new node, to be placed in the array.
     */
    public void add(E content) {
        // Resize and rehash if needed first
        if (this.loadFactor >= LOAD_FACTOR_THRESHOLD) {
            this.rehash();
        }
        // Create the new node to add to the hashtable
        Node<E> newNode = new Node<E>(content);
        // Use the hashcode for the new node's contents to find where to place it in the
        // underlying array. Use abs in case hashcode < 0.
        int position = this.hashPosition(content);
        // Check if selected position is already in use
        if (this.underlying[position] == null) {
            // Selected position not in use. Place the new node here and update the usage of
            // the underlying array.
            this.underlying[position] = newNode;
            this.usage += 1;
        } else {
            // Selected position in use. We will append its contents to the new node first,
            // then place the new node in the selected position. Effectively the new node
            // becomes the first node of the existing linked list in this position.
            newNode.setNext(this.underlying[position]);
            this.underlying[position] = newNode;
        }
        // Update the number of nodes
        this.totalNodes += 1;
        this.updateLoadFactor();
    } // method add

    /** Constants for toString */
    private static final String LINKED_LIST_HEADER = "\n[ %2d ]: ";
    private static final String EMPTY_LIST_MESSAGE = "null";
    private static final String ARRAY_INFORMATION = "Underlying array usage / length: %d/%d";
    private static final String NODES_INFORMATION = "\nTotal number of nodes: %d";
    private static final String NODE_CONTENT = "%s --> ";

    /** String representationf for the object */
    public String toString() {
        // Initialize the StringBuilder object with basic info
        StringBuilder sb = new StringBuilder(
                String.format(ARRAY_INFORMATION,
                        this.underlying.length, this.usage));
        sb.append(String.format(NODES_INFORMATION, this.totalNodes));
        // Iterate the array
        for (int i = 0; i < underlying.length; i++) {
            sb.append(String.format(LINKED_LIST_HEADER, i));
            Node<E> head = this.underlying[i];
            if (head == null) {
                // message that this position is empty
                sb.append(EMPTY_LIST_MESSAGE);
            } else {
                // traverse the linked list, displaying its elements
                Node<E> cursor = head;
                while (cursor != null) {
                    // update sb
                    sb.append(String.format(NODE_CONTENT, cursor));
                    // move to the next node of the ll
                    cursor = cursor.getNext();
                } // done traversing the linked list
            } // done checking the current position of the underlying array
        } // done iterating the underlying array
        return sb.toString();
    } // method toString

} // class HashTable