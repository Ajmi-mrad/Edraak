package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.util.List;


@Entity
@AllArgsConstructor
public class Instructor extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    private Speciality speciality;

    @Lob
    @Column(columnDefinition = "LONGBLOB", name = "cv")
    private byte[] cv;

    @OneToMany(mappedBy = "instructor")
    @JsonManagedReference
    private List<Formation> formations;

    public Instructor(String name, String lastName, String email, String phoneNumber, LocalDate birthDate, String address, String nationality, String numId, Speciality speciality, byte[] cv) {
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

    public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }
}

