package projet.spring.edraak.request.classroom;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddClassroomRequest {
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
