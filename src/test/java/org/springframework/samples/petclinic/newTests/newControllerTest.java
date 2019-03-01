package org.springframework.samples.petclinic.system;

import static org.junit.Assert.*;
import org.junit.Test;
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