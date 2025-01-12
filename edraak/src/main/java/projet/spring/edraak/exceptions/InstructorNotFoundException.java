package projet.spring.edraak.exceptions;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(String message) {
        super(message);
    }
}
