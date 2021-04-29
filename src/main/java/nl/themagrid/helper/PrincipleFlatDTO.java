package nl.themagrid.helper;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class PrincipleFlatDTO {
    public String id;
    public String value;

    public PrincipleFlatDTO() {
    }

    public PrincipleFlatDTO(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
