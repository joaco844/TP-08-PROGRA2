/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public interface SimpleList<E> {
    public void add(E element);
    public void add(int index, E element);
    public E get(int index);
    public E set(int index, E element);
    public E remove(int index);
    public boolean contains(E element);
    public int indexOf(E element);
    public void clear();
    public int size();
    public boolean isEmpty();
}
