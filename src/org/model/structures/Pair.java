package org.model.structures;

public class Pair<K,V> {
    public final K k;
    public final V v;
    
    public Pair(K key, V value) {
        this.k = key;
        this.v = value;
    }
}
