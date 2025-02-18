package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.*;
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

    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonManagedReference
    @JsonIgnoreProperties({"instructor"})
    private List<Formation> formations;
    //private List<FormationDTO> formas;

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


    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }


/*
    public List<FormationDTO> getFormas() {
        return formas;
    }

    public void setFormas(List<FormationDTO> formas) {
        this.formas = formas;
    }
    
 */
}

