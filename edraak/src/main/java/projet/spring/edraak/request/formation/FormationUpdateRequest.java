package projet.spring.edraak.request.formation;

import projet.spring.edraak.model.TypeFormation;

public class FormationUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private TypeFormation typeFormation;

    public FormationUpdateRequest(Long id ,String name, String description, TypeFormation typeFormation) {
        this.id= id;
        this.name = name;
        this.description = description;
        this.typeFormation = typeFormation;
    }
    public FormationUpdateRequest(){
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

    public void setName(String name) {
        this.name = name;
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
