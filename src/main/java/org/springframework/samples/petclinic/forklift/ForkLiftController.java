package org.springframework.samples.petclinic.forklift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForkLiftController {

    private Forklifter forklifter;

    @Autowired
    public ForkLiftController(Forklifter forklifter) {
        this.forklifter = forklifter;
    }

    @GetMapping("/forklift")
    public void forklift() {
        forklifter.forklift();
    }

}
