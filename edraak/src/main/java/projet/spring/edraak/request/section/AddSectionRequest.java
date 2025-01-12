package projet.spring.edraak.request.section;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddSectionRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

