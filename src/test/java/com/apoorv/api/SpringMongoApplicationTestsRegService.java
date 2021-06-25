package com.apoorv.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.apoorv.api.model.Registration;
import com.apoorv.api.repository.RegistrationRepository;
import com.apoorv.api.service.RegistrationService;

@SpringBootTest
class SpringMongoApplicationTestsRegService {
	
	@Autowired
	private RegistrationService regService;
	
	@MockBean
	private RegistrationRepository regRepository;

//	@Test
	public void saveRegistrationTest() {
		Registration r = new Registration("1", "Amit", "Address Test", "India", "Goa",
				"test@test.com", "Admin@123", "ASDFG1212W", "9171558594", "06/22/2021", "test@test.com");

		when(regRepository.save(r)).thenReturn(r);
		assertEquals(r, regService.saveRegistration(r));
	}

//	@Test
	public void findByEmail() {
		Registration r = new Registration("1", "Amit", "Address Test", "India", "Goa",
				"test@test.com", "Admin@123", "ASDFG1212W", "9171558594", "06/22/2021", "test@test.com");
		
		String email = "test@test.com";
		
		when(regRepository.findByEmail(email)).thenReturn(r);
		assertEquals(r, regService.findByEmail(email));
	}
	
//	@Test
	public void updateRegistrationByEmail() {
		String oldEmail = "test@test.com";
		Registration regFromUI = new Registration("1", "Amit", "Address Test", "India", "Goa",
				"test@test.com", "Admin@123", "ASDFG1212W", "9171558594", "06/22/2021", "test@test.com");
		when(regRepository.findByEmail(oldEmail)).thenReturn(regFromUI);
		regFromUI.setAddress("2nd address");
		when(regRepository.save(regFromUI)).thenReturn(regFromUI);
		assertEquals(regFromUI, regService.updateRegistrationByEmail(oldEmail, regFromUI));
		
		assertEquals(regFromUI, regService.updateRegistrationByEmail(oldEmail, regFromUI));
	}

}
