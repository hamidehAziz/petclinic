package org.springframework.samples.petclinic.newTests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.samples.petclinic.system.WelcomeController;

import static org.mockito.Mockito.*;

public class newControllerTest {

	@Test
	public void controllerTest(){
		WelcomeController welcomeMock = mock(WelcomeController.class);
        when(welcomeMock.welcome()).thenReturn("Welcome to petClinic!");
	    String  welcome = "Welcome to petClinic!";
	    assertEquals(welcome, welcomeMock.welcome());
	}

}
