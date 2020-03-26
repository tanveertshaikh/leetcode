package lrucache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUCacheTest {

    private LRUCache cache;

    public LRUCacheTest() {
        this.cache = new LRUCache(2);
    }

    @Test
    public void testCacheStartsEmpty() {
        assertEquals(cache.get(1), -1);
    }

    @Test
    public void testPutBelowCapacity() {
        cache.put(1, 1);
        assertEquals(cache.get(1), 1);
        assertEquals(cache.get(2), -1);
        cache.put(2, 4);
        assertEquals(cache.get(1), 1);
        assertEquals(cache.get(2), 4);
    }

    @Test
    public void testCapacityReachedOldestRemoved() {
        cache.put(1, 1);
        cache.put(2, 4);
        cache.put(3, 9);
        assertEquals(cache.get(1), -1);
        assertEquals(cache.get(2), 4);
        assertEquals(cache.get(3), 9);
    }

    @Test
    public void testGetRenewsEntry() {
        cache.put(1, 1);
        cache.put(2, 4);
        assertEquals(cache.get(1), 1);
        cache.put(3, 9);
        assertEquals(cache.get(1), 1);
        assertEquals(cache.get(2), -1);
        assertEquals(cache.get(3), 9);
    }
}
