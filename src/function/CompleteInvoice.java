package function;

public class CompleteInvoice extends AbstractCompletableCommand<Invoice> {
    final Invoice invoice;

    public CompleteInvoice(final Repository<Invoice> repository, final Invoice invoice) {
        super(repository, invoice);
        this.invoice = invoice;
    }

    @Override
    public void doFinish() {
        invoice.complete();
    }
}
