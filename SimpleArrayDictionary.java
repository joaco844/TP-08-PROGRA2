/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class SimpleArrayDictionary<K, V> implements SimpleDictionary<K, V> {

    // Dos arrays paralelos: la clave en keys[i] tiene su valor en values[i]
    private K[] keys;
    private V[] values;
    private int size = 0;
    private static final int DEFAULT_SIZE = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayDictionary() {
        keys = (K[]) new Object[DEFAULT_SIZE];
        values = (V[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public V put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");

        // Si la clave ya existe, pisamos el valor y devolvemos el anterior
        int index = indexOf(key);
        if (index != -1) {
            V previous = values[index];
            values[index] = value;
            return previous;
        }

        // Si no existe, la agregamos al final
        validateSize(size + 1);
        keys[size] = key;
        values[size] = value;
        size++;
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        int index = indexOf(key);
        if (index == -1)
            return false;
        shiftLeft(index);
        size--;
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        return indexOf(key) != -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        keys = (K[]) new Object[DEFAULT_SIZE];
        values = (V[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public SimpleList<K> keys() {
        // Devolvemos una lista nueva, asi si la modifican no rompen el diccionario
        SimpleList<K> result = new SimpleArrayList<>();
        for (int i = 0; i < size; i++)
            result.add(keys[i]);
        return result;
    }

    @Override
    public SimpleList<V> values() {
        SimpleList<V> result = new SimpleArrayList<>();
        for (int i = 0; i < size; i++)
            result.add(values[i]);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Extra (no esta en la interfaz): devuelve el valor de una clave, o null si no esta
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula");
        int index = indexOf(key);
        if (index == -1)
            return null;
        return values[index];
    }

    private int indexOf(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key))
                return i;
        }
        return -1;
    }

    private void validateSize(int newSize) {
        if (newSize > keys.length)
            resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        // Creamos los nuevos arrays del doble de largo que los actuales
        K[] nuevasKeys = (K[]) new Object[keys.length * 2];
        V[] nuevosValues = (V[]) new Object[values.length * 2];

        // Copiamos todo a los nuevos
        for (int i = 0; i < size; i++) {
            nuevasKeys[i] = keys[i];
            nuevosValues[i] = values[i];
        }

        keys = nuevasKeys;
        values = nuevosValues;
    }

    private void shiftLeft(int index) {
        // Corremos cada par clave/valor a la izquierda
        for (int i = index; i < size - 1; i++) {
            keys[i] = keys[i + 1];
            values[i] = values[i + 1];
        }

        // Borramos el ultimo para que no quede duplicado
        keys[size - 1] = null;
        values[size - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            sb.append(keys[i]).append("=").append(values[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
