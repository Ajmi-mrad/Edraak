package projet.spring.edraak.model;

import jakarta.persistence.*;

@Entity
public class Formation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //type of student if a student or an employee
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "typeFormation_id", referencedColumnName = "id")
    private TypeFormation typeFormation;

    public Formation(String nameFormation, String description, TypeFormation typeFormation) {
        this.name = nameFormation;
        this.description = description;
        this.typeFormation = typeFormation;
    }
    public Formation(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameFormation) {
        this.name = nameFormation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeFormation getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(TypeFormation typeFormation) {
        this.typeFormation = typeFormation;
    }
}
