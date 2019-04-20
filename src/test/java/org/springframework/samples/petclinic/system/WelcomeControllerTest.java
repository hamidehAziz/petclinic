package org.springframework.samples.petclinic.system;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.samples.petclinic.system.toggles.Toggles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WelcomeControllerTest.class)
public class WelcomeControllerTest {

    private WelcomeController welcomeController;

    @Before
    public void setup(){
        welcomeController = new WelcomeController();
    }

    @Test
    public void testRollBackWelcomePage() {
        Toggles.newWelcomePage = true;
        assertEquals("bilingualWelcome", welcomeController.welcome());

        Toggles.newWelcomePage = false;
        assertEquals("welcome", welcomeController.welcome());

        Toggles.newWelcomePage = true;
        assertEquals("bilingualWelcome", welcomeController.welcome());
    }

//    @Test
//    public void testRandomWelcomePage() {
//        int iterations = 1000;
//
//        for (int i = 0; i < iterations; i++) {
//            Toggles.newWelcomePage = AssignRandomRequirement.getRandom(Boolean.TRUE);
//
//            welcomeController.welcome();
//            //new feature is on, see welcome page
//            if (Toggles.newWelcomePage) {
//                welcomeController.incrementBilingualPageCounter();
//            } else {
//                welcomeController.incrementWelcomePageCounter();
//            }
//        }
//    }
}
