package exception;

public class MemberAlreadyExistException extends Exception {
    public MemberAlreadyExistException(String messgae){
        super(messgae);
    }
}