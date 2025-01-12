package projet.spring.edraak.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;


@Entity
@AllArgsConstructor
public class Instructor extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    @Lob
    private String cv;

    public Instructor(String name, String lastName, String email, String phoneNumber, LocalDate birthDate, String address, String nationality, String numId, Speciality speciality, String cv) {
        super(name, lastName, email, phoneNumber, birthDate, address, nationality, numId);
        this.speciality = speciality;
        this.cv = cv;
    }

    public Instructor() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}

