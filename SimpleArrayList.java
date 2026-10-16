/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] array;
    private int size = 0;
    private static final int DEFAULT_SIZE = 4;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        array = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(E element) {
        validateSize(size + 1);
        array[size] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        // Aca el index puede ser igual a size (agregar al final)
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Indice invalido: " + index);
        validateSize(size + 1);
        shiftRight(index);
        array[index] = element;
        size++;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        E previous = array[index];
        array[index] = element;
        return previous;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        E result = array[index];
        shiftLeft(index);
        size--;
        return result;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i]))
                return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Indice invalido: " + index);
    }

    private void validateSize(int newSize) {
        if (newSize > array.length)
            resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        // Creamos un nuevo array del doble de largo que el actual
        E[] nuevoArray = (E[]) new Object[array.length * 2];

        // Copiamos todo lo que esta en array al nuevo
        for (int i = 0; i < size; i++)
            nuevoArray[i] = array[i];

        array = nuevoArray;
    }

    private void shiftRight(int index) {
        // Corremos cada elemento a la derecha, empezando desde el final
        for (int i = size; i > index; i--)
            array[i] = array[i - 1];
    }

    private void shiftLeft(int index) {
        // Corremos cada elemento a la izquierda
        for (int i = index; i < size - 1; i++)
            array[i] = array[i + 1];

        // Borramos el ultimo para que no quede duplicado
        array[size - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
