package br.com.andrebaroni.burger.store.api.domain.exception;

public class SaleAlreadyConcludedException extends GenericException {

    public SaleAlreadyConcludedException() {
        super("exception.saleAlreadyConcluded");
    }
}
