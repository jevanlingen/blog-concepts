package function;

public interface Repository<T> {
    T findById(long id);
    T save(T t);
}
