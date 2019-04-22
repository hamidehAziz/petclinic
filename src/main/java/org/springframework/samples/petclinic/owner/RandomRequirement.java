package org.springframework.samples.petclinic.owner;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRequirement {

    public RandomRequirement() {
    }

    public boolean getAddOwner(int proportionWithAdd) {
        int rnd = ThreadLocalRandom.current().nextInt(1, 101);

        if (proportionWithAdd >= rnd) {
            return true;
        }

        return false;
    }
}
