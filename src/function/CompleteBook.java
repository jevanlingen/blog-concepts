package function;

public class CompleteBook extends AbstractCompletableCommand<Book> {
    final Book book;

    public CompleteBook(final Repository<Book> repository, final Book book) {
        super(repository, book);
        this.book = book;
    }
    
    @Override
    public void doFinish() {
        book.read();
    }
}
