package function;

public abstract class AbstractCompletableCommand<T> implements CompletableCommand<T> {
    private final Repository<T> repository;
    private final T t;

    public AbstractCompletableCommand(final Repository<T> repository, final T t) {
        this.repository = repository;
        this.t = t;
    }

    protected abstract void doFinish();

    @Override
    public void finish() {
        doFinish();
        repository.save(t);
    }
}
