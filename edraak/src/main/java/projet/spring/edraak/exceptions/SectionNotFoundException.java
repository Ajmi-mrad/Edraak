package projet.spring.edraak.exceptions;

public class SectionNotFoundException extends RuntimeException{
    public SectionNotFoundException(String message) {
        super(message);
    }
}
