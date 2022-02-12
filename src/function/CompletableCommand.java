package function;

@FunctionalInterface // Add later in blog
public interface CompletableCommand<T> {
    void finish();
}
