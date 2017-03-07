package hot.swap.proxy.base;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by leeshine on 3/7/17.
 */

public class ListDelegate implements List<Object> {
    private List<Object> _delegate;

    public ListDelegate() {
        _delegate = new ArrayList<Object>();
    }

    public void setDelegate(List<Object> delegate) {
        _delegate = delegate;
    }

    public List<Object> getDelegate() {
        return _delegate;
    }

    public int size() {
        return _delegate.size();
    }

    public boolean isEmpty() {
        return _delegate.isEmpty();
    }

    public boolean contains(Object o) {
        return _delegate.contains(o);
    }

    public Iterator<Object> iterator() {
        return _delegate.iterator();
    }

    public Object[] toArray() {
        return _delegate.toArray();
    }

    public <T> T[] toArray(T[] ts) {
        return _delegate.toArray(ts);
    }

    public boolean add(Object e) {
        return _delegate.add(e);
    }

    public boolean remove(Object o) {
        return _delegate.remove(o);
    }

    public boolean containsAll(Collection<?> clctn) {
        return _delegate.containsAll(clctn);
    }

    public boolean addAll(Collection<? extends Object> clctn) {
        return _delegate.addAll(clctn);
    }

    public boolean addAll(int i, Collection<? extends Object> clctn) {
        return _delegate.addAll(i, clctn);
    }

    public boolean removeAll(Collection<?> clctn) {
        return _delegate.removeAll(clctn);
    }

    public boolean retainAll(Collection<?> clctn) {
        return _delegate.retainAll(clctn);
    }

    public void clear() {
        _delegate.clear();
    }

    public Object get(int i) {
        return _delegate.get(i);
    }

    public Object set(int i, Object e) {
        return _delegate.set(i, e);
    }

    public void add(int i, Object e) {
        _delegate.add(i, e);
    }

    public Object remove(int i) {
        return _delegate.remove(i);
    }

    public int indexOf(Object o) {
        return _delegate.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return _delegate.lastIndexOf(o);
    }

    public ListIterator<Object> listIterator() {
        return _delegate.listIterator();
    }

    public ListIterator<Object> listIterator(int i) {
        return _delegate.listIterator(i);
    }

    public List<Object> subList(int i, int i1) {
        return _delegate.subList(i, i1);
    }
}