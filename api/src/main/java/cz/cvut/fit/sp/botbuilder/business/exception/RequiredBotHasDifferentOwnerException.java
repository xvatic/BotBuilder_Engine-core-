package cz.cvut.fit.sp.botbuilder.business.exception;

public class RequiredBotHasDifferentOwnerException extends Exception{
    public RequiredBotHasDifferentOwnerException() {
        super("ACCESS DENIED");
    }
}
