/***
Coder: Roslan S, UiTM Pahang, roslancs@uitm.edu.my
Year: 2012
***/

public interface List<E> {
	public void add(E element);
	public void add(int index, E element);
	public E get(int index);
	public int size();
	public E set(int index, E element);	
	public void clear();
	public boolean contains(E element);	
	public int indexOf(E element);
	public boolean remove(E element);
	public E remove(int index);
}
