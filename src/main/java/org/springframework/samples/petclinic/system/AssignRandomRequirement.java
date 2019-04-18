package org.springframework.samples.petclinic.system;
import java.util.concurrent.ThreadLocalRandom;

public class AssignRandomRequirement {

    public AssignRandomRequirement(){ }
    public static boolean getRandom(Boolean value){
        Boolean required = ThreadLocalRandom.current().nextBoolean();
        return value == required;
    }
}
