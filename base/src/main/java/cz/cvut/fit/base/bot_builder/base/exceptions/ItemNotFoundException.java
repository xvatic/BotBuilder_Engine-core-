package cz.cvut.fit.base.bot_builder.base.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public <T> ItemNotFoundException(T item) {
        super("Item not found:" + item.toString());
    }
}
