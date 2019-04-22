package org.springframework.samples.petclinic.system;
import java.util.concurrent.ThreadLocalRandom;

public class AssignRandomRequirement {

    public AssignRandomRequirement(){ }

    public boolean getInsurance(int proportionWithVetsWithInsurance){

        int rnd = ThreadLocalRandom.current().nextInt(1, 101);

        if(proportionWithVetsWithInsurance >= rnd) {
            return true;
        }

        return false;
    }
}
