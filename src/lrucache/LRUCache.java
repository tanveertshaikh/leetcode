package lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    final Node head = new Node();
    final Node tail = new Node();
    Map<Integer, Node> node_map;
    int cache_capacity;

    public LRUCache(int capacity) {
        this.node_map = new HashMap<>(capacity);
        this.cache_capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int result = -1;
        Node node = node_map.get(key);
        if(node != null) {
            result = node.val;
            remove(node);
            add(node);
        }
        return result;
    }

    public void put(int key, int value) {
        Node node = node_map.get(key);
        if(node != null) {
            remove(node);
            node.val = value;
            add(node);
        } else {
            if(node_map.size() == cache_capacity) {
                node_map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value;

            node_map.put(key, new_node);
            add(new_node);
        }
    }

    public void add(Node node) {
        Node head_next = head.next;
        head.next = node;
        node.prev = head;

        node.next = head_next;
        head_next.prev = node;
    }

    public void remove(Node node) {
        Node next_node = node.next;
        Node prev_node = node.prev;

        next_node.prev = prev_node;
        prev_node.next = next_node;
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(7);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(5, 5);
        cache.put(6, 6);
        cache.put(7, 7);
        cache.put(8, 8);
        int param = cache.get(8);

        System.out.println(param);
    }
}