/***
Coder: Roslan S, UiTM Pahang, roslancs@uitm.edu.my
Year: 2012
***/

public abstract class AbstractList<E> implements List<E> {
	
    protected int size = 0;
    protected AbstractList(){} /*default constructor*/
    
    protected AbstractList(E[] element){ /*Normal constructor*/
        for (int i=0; i<element.length; i++)
            add(element[i]); /*calling add()*/
    }
    
    public void add(E element) { add(size, element); }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){ return size; }

    public boolean remove(E element) {
        if(indexOf(element) >= 0){
            remove(indexOf(element));
            return true;
        }
        else {
            return false;
        }
    }	

}
