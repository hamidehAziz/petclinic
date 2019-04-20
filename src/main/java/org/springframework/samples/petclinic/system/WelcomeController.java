package org.springframework.samples.petclinic.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

    private int biligualPageCount;
    private int welcomePageCount;
    private Logger logger = LogManager.getLogger(WelcomeController.class);


    @GetMapping("/")
    public String welcome() {
        if (Toggles.newWelcomePage){
            return "bilingualWelcome";
        }
        return "welcome";
    }

    public void incrementWelcomePageCounter(){
        logger.info("regular welcome page count accessed: " + welcomePageCount);
        welcomePageCount++;
    }

    public void incrementBilingualPageCounter(){
        logger.info("bilingual welcome page count accessed: " + biligualPageCount);
        biligualPageCount++;
    }

    public int getBiligualPageCount(){
        return biligualPageCount;
    }

    public int getWelcomePageCount() {
        return welcomePageCount;
    }
}
