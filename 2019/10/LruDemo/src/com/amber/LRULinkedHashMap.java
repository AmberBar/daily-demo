package com.amber;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author amber
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    //最大容量
    private final int maxCapacity ;
    // 默认增长因子
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public LRULinkedHashMap(int maxCapacity) {
        //accessOrder为true表示会把最新访问的数据放到最后一个节点，默认false
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if(size()>maxCapacity)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        LRULinkedHashMap<String, String> lruLinkedHashMap = new LRULinkedHashMap(5);
        lruLinkedHashMap.put("1", "1");
        lruLinkedHashMap.put("2", "2");
        lruLinkedHashMap.put("3", "3");
        lruLinkedHashMap.put("4", "4");
        lruLinkedHashMap.put("5", "5");
        Iterator<Map.Entry<String, String>> iterator = lruLinkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        lruLinkedHashMap.get("1");
        System.out.println("超出最大容量");
        lruLinkedHashMap.put("6", "6");
        iterator = lruLinkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
