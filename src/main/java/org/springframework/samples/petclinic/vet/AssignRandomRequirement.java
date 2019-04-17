package org.springframework.samples.petclinic.vet;
import java.util.concurrent.ThreadLocalRandom;

public class AssignRandomRequirement {

    public AssignRandomRequirement(){ }
    public boolean getInsurance(Boolean value){
        Boolean required = ThreadLocalRandom.current().nextBoolean();
        return value == required;
    }
}
