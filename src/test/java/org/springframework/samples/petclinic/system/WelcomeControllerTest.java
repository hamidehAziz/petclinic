package org.springframework.samples.petclinic.system;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WelcomeControllerTest.class)
public class WelcomeControllerTest {

    @Test
    public void testRollBackWelcomePage(){
        WelcomeController welcomeController = new WelcomeController();
        Toggles.newWelcomePage = true;
        assertEquals("bilingualWelcome", welcomeController.welcome());

        Toggles.newWelcomePage = false;
        assertEquals("welcome", welcomeController.welcome());

        Toggles.newWelcomePage = true;
        assertEquals("bilingualWelcome", welcomeController.welcome());
    }

    @Test
    public void testRandomWelcomePage(){
        int iterations = 1000;
        WelcomeController welcomeController = new WelcomeController();


        for(int i = 0; i < iterations; i++){
            Toggles.newWelcomePage = AssignRandomRequirement.getRandom(Boolean.TRUE);

            welcomeController.welcome();
            //new feature is on, see welcome page
            if(Toggles.newWelcomePage) {
                welcomeController.incrementBilingualPageCounter();
            }
            else {
                welcomeController.incrementWelcomePageCounter();
            }
        }

        System.out.println("Bilingual welcome page was loaded: " + welcomeController.getBiligualPageCount() + " times");
        System.out.println("Regular welcome page was loaded: " + welcomeController.getWelcomePageCount() + " times");
    }
}
