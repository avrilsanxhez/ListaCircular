package listacircular;

import java.util.Iterator;

public interface IList<T>  {
    public void append(T elemento) throws ListException;
    public void insert(T elemento, int index);
    public T remove(int index) throws ListException;
    public boolean removeObj(T elemento) throws ListException;
    public int indexOf(T elemento);
    public T get(int index) throws ListException;
    public void set(T elemento, int index) throws ListException;
    public void clear();
    public boolean empty();
    public int size();
    public Iterator<T> Iterator();  
}
