package br.com.andrebaroni.burger.store.api.domain.exception;

import br.com.andrebaroni.burger.store.api.infra.internationalization.Translator;

public class GenericException extends RuntimeException {
    public GenericException(String messageCode) {
        super(Translator.toLocale(messageCode));
    }

    public GenericException(String messageCode, Object[] objects) {
        super(Translator.toLocale(messageCode, objects));
    }
}
