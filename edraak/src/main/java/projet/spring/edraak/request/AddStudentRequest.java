package projet.spring.edraak.request;

import jdk.jfr.Name;
import lombok.*;

import java.time.LocalDate;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class AddStudentRequest {
    //private String id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;
    private String nationality;
    private String numId;
    //type of student if a student or an employee
    private String typeStudent;
    // heritage de la classe Person
    private String level;
    private String section;
    private String school;
    // if student is an employee
    private String job;
    private String company;

}
