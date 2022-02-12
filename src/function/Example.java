package function;

import java.util.function.Consumer;

public class Example {
    private Repository<Book> bookRepo;
    private Repository<Invoice> invoiceRepo;

    // Notice the difference between the actions!

    // Endpoint #1
    private void actionOnBook(long id) {
        final var book = bookRepo.findById(id);
        book.read();
        bookRepo.save(book);
    }

    private void actionOnBookNew(long id) {
        final var book = bookRepo.findById(id);
        new CompleteBook(bookRepo, book).finish();
    }

    private void actionOnBookNew2(long id) {
        final var book = bookRepo.findById(id);
        execute(new CompletableCommand<Book>() {
            @Override
            public void finish() {
                book.read();
            }
        }, bookRepo, book);
    }

    private void actionOnBookNew3(long id) {
        final var book = bookRepo.findById(id);
        execute(() -> book.read(), bookRepo, book);
    }

    private void actionOnBookNew4(long id) {
        final var book = bookRepo.findById(id);
        execute(book::read, bookRepo, book);
    }

    private void actionOnBookRefactored(long id) {
        final var book = bookRepo.findById(id);
        execute(Book::read, bookRepo, book);
    }

    // Endpoint #2
    private void actionOnInvoice(long id) {
        final var invoice = invoiceRepo.findById(id);
        invoice.complete();
        invoiceRepo.save(invoice);
    }

    private void actionOnInvoiceNew(long id) {
        final var invoice = invoiceRepo.findById(id);
        new CompleteInvoice(invoiceRepo, invoice).finish();
    }

    private void actionOnInvoiceRefactored(long id) {
        final var invoice = invoiceRepo.findById(id);
        execute(Invoice::complete, invoiceRepo, invoice);
    }

    // === Execute helpers === //

    private <T> void execute(CompletableCommand<T> command, Repository<T> repository, T t) {
        command.finish();
        repository.save(t);
    }

    private <T> void execute(Consumer<T> command, Repository<T> repository, T t) {
        command.accept(t);
        repository.save(t);
    }
}
