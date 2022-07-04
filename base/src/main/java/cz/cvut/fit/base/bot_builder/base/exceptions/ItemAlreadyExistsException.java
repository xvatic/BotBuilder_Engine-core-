package cz.cvut.fit.base.bot_builder.base.exceptions;

public class ItemAlreadyExistsException extends RuntimeException{
    public <T> ItemAlreadyExistsException(T item) {
        super("This username is already taken: " + item.toString());
    }
}
