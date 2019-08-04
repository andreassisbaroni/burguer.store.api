package br.com.andrebaroni.burger.store.api.domain.exception;

public class SaleAlreadyCanceledException extends GenericException {

    public SaleAlreadyCanceledException() {
        super("exception.saleAlreadyCanceled");
    }
}
