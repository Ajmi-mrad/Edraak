package projet.spring.edraak.exceptions;

public class FormationNotFoundException extends RuntimeException{
    public FormationNotFoundException(String message) {
        super(message);
    }
}
