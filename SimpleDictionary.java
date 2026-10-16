/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public interface SimpleDictionary<K, V> {
    public V put(K key, V value);
    public boolean remove(K key);
    public boolean containsKey(K key);
    public void clear();
    public SimpleList<K> keys();
    public SimpleList<V> values();
    public int size();
    public boolean isEmpty();
}
