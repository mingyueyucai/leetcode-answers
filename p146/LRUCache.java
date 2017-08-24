import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LRUCache {
    private OpenHash openHash;
    private Node head;
    private Node tail;
    private int currentLength = 0;
    private int capacity;

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1101);
        try {
            Scanner scanner = new Scanner(new File("/Users/jiangpeiran/workspace/scripts/lru.txt"));
            while (scanner.hasNext()) {
                String command = scanner.next();
                if (command.equals("get")) {
                    cache.get(scanner.nextInt());
                } else {
                    cache.put(scanner.nextInt(), scanner.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("Error");
            throw e;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.openHash = new OpenHash(capacity);
    }

    public int get(int key) {
        Node node = openHash.get(key);
        if (node == null) {
            return -1;
        } else {
            refreshOrAdd(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = openHash.put(key, value);
        refreshOrAdd(node);
    }

    private void refreshOrAdd(Node node) {
        if (node.linkNext != null) {
            if (node.linkPrevious == null) {
                // this is head
                head = node.linkNext;
                head.linkPrevious = null;
                tail.linkNext = node;
                node.linkPrevious = tail;
                node.linkNext = null;
                tail = node;
                return;
            } else {
                node.linkPrevious.linkNext = node.linkNext;
                node.linkNext.linkPrevious = node.linkPrevious;
                node.linkPrevious = tail;
                tail.linkNext = node;
                node.linkNext = null;
                tail = node;
                return;
            }
        }
        if (node.linkPrevious != null) {
            // this is tail. do nothing.
        } else if (head == node) {
            // this is the only node in link. do nothing.
        } else {
            // this is a new node to link
            if (currentLength == capacity) {
                weedOutLRU();
            }
            addToTail(node);
        }
    }

    private void addToTail(Node node) {
        if (head == null) {
            head = node;
            tail = node;
            currentLength++;
            return;
        }
        node.linkPrevious = tail;
        tail.linkNext = node;
        tail = node;
        currentLength++;
    }

    private void weedOutLRU() {
        Node node = head;
        head = node.linkNext;
        if (head != null) {
            head.linkPrevious = null;
        }
        if (tail == node) {
            tail = null;
        }
        openHash.delete(node.key);
        currentLength--;
    }
    
    private static class OpenHash {
        private float fact = 0.75f;
        private int size;
        private Node[] data;

        public OpenHash(int capacity) {
            size = (int)((capacity + 1) / fact) + 1;
            data = new Node[size];
        }

        private int hashFunc(int x) {
            return x % size;
        }

        public Node get(int k) {
            int index = hashFunc(k);
            Node p = data[index];
            while (p != null && p.key != k) {
                p = p.next;
            }
            if (p == null) {
                return null;
            } else {
                return p;
            }
        }

        public Node put(int k, int v) {
            Node newNode = new Node();
            newNode.key = k;
            newNode.value = v;
            int index = hashFunc(k);
            if (data[index] == null) {
                data[index] = newNode;
                return newNode;
            }
            Node p = data[index];
            while (p.key != k && p.next != null) {
                p = p.next;
            }
            if (p.key == k) {
                p.value = v;
                return p;
            }
            p.next = newNode;
            return newNode;
        }

        public void delete(int k) {
            int index = hashFunc(k);
            Node p = data[index];
            if (p.key == k) {
                data[index] = p.next;
                return;
            }
            while (p.next.key != k) {
                p = p.next;
            }
            p.next = p.next.next;
        }
    }

    private static class Node {
        public int key;
        public int value;
        public Node next;
        public Node linkPrevious;
        public Node linkNext;
    }
}