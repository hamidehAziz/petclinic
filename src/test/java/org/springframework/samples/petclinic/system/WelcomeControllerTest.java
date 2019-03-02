package org.springframework.samples.petclinic.system;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class WelcomeControllerTest{

    @Test
    public void controllerTest(){
        WelcomeController welcomeMock = mock(WelcomeController.class);
        when(welcomeMock.welcome()).thenReturn("Welcome to petClinic!");
        String  welcome = "Welcome to petClinic!";
        assertEquals(welcome, welcomeMock.welcome());
    }

}
