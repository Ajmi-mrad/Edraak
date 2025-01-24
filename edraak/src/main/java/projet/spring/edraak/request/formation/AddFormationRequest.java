package projet.spring.edraak.request.formation;

import projet.spring.edraak.model.TypeFormation;

public class AddFormationRequest {
    private String name;
    private String description;
    private TypeFormation typeFormation;

    public AddFormationRequest(String name, String description, TypeFormation typeFormation) {
        this.name = name;
        this.description = description;
        this.typeFormation = typeFormation;
    }
    public AddFormationRequest(){
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
