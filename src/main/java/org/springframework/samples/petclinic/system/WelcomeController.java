package org.springframework.samples.petclinic.system;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {

    private int biligualPageCount;
    private int welcomePageCount;

    @GetMapping("/")
    public String welcome() {
        if (Toggles.newWelcomePage){
            return "bilingualWelcome";
        }
        return "welcome";
    }

    public void incrementWelcomePageCounter(){
        welcomePageCount++;
    }

    public void incrementBilingualPageCounter(){
        biligualPageCount++;
    }

    public int getBiligualPageCount(){
        return biligualPageCount;
    }

    public int getWelcomePageCount() {
        return welcomePageCount;
    }
}
