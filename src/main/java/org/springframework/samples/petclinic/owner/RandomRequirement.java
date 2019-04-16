package org.springframework.samples.petclinic.owner;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRequirement {

    public RandomRequirement(){ }
    public boolean getAddOwner(Boolean value){
        Boolean required = ThreadLocalRandom.current().nextBoolean();
        if(value == required)
            return true;
        else
            return false;
    }
}
