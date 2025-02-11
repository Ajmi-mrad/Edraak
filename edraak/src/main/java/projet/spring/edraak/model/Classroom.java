package projet.spring.edraak.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Classroom(String name) {
        this.name = name;
    }
    public Classroom(){
    }
    @OneToMany(mappedBy = "classroom")
    private List<RegistrationFormation> registrationFormations;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

