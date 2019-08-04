package br.com.andrebaroni.burger.store.api.domain.exception;

import br.com.andrebaroni.burger.store.api.infra.internationalization.Translator;

public class EntityNotFoundException extends GenericException {

    public EntityNotFoundException(Class<?> entityClass) {
        super("exception.EntityNotFound", new Object[]{Translator.toLocale("entity." + entityClass.getSimpleName())});
    }
}
