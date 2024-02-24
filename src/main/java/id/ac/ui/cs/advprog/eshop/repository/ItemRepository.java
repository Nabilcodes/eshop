package id.ac.ui.cs.advprog.eshop.repository;
import java.util.Iterator;
public interface ItemRepository<T> {
    T create(T entity);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T entity);
    void deleteById(String id);
}