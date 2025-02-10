package projet.spring.edraak.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;


@Entity
@AllArgsConstructor
public class Student extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //type of student if a student or an employee
    private String typeStudent;
    // heritage de la classe Person
    private String level;
    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
    //@ManyToOne

    private String school;
    // if student is an employee
    private String job;
    private String company;

    public Student(String name, String lastName, String email, String phoneNumber, LocalDate birthDate, String address, String nationality, String numId, String typeStudent, String level, Section section, String school, String job, String company) {
        super(name, lastName, email, phoneNumber, birthDate, address, nationality, numId);
        this.typeStudent = typeStudent;
        this.level = level;
        this.section = section;
        this.school = school;
        this.job = job;
        this.company = company;
    }
    public Student(){
        super();
    }

    public String getTypeStudent() {
        return typeStudent;
    }

    public void setTypeStudent(String typeStudent) {
        this.typeStudent = typeStudent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    // Getters and Setters


}
