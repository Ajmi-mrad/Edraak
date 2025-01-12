package projet.spring.edraak.request.student;

import lombok.*;
import projet.spring.edraak.model.Section;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateRequest {
    private String id;
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
    private Section section;
    private String school;
    // if student is an employee
    private String job;
    private String company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
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
}
