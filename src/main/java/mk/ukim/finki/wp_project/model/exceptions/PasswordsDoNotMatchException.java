package mk.ukim.finki.wp_project.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("passwords do not match exception");
    }
}
