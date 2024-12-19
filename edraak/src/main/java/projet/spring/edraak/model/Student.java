package projet.spring.edraak.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person{
    //type of student if a student or an employee
    private String typeStudent;
    // heritage de la classe Person
    private String level;
    private String section;
    private String school;
    // if student is an employee
    private String job;
    private String company;

    public Student(String name, String lastName, String email, String phoneNumber, LocalDate birthDate, String address, String nationality, String numId, String typeStudent, String level, String section, String school, String job, String company) {
        super(name, lastName, email, phoneNumber, birthDate, address, nationality, numId);
        this.typeStudent = typeStudent;
        this.level = level;
        this.section = section;
        this.school = school;
        this.job = job;
        this.company = company;
    }
    // Getters and Setters

}
