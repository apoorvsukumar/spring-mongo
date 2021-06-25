package com.apoorv.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.apoorv.api.model.ClaimForm;
import com.apoorv.api.model.Registration;
import com.apoorv.api.repository.ClaimRepository;
import com.apoorv.api.service.ClaimService;

@SpringBootTest
class SpringMongoApplicationTestsClaimService {

	@Autowired
	private ClaimService claimService;

	@MockBean
	private ClaimRepository claimRepository;

	@Test
	public void saveClaimTest() {
		ClaimForm claim = new ClaimForm(1, "Amit", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021", "Test Provider", 100,
				"Self");

		when(claimRepository.save(claim)).thenReturn(claim);
		assertEquals(claim, claimService.saveClaim(claim));
	}

	@Test
	public void getAllClaimTest() {
		when(claimRepository.findAll()).thenReturn(Stream.of(
				new ClaimForm(1, "Amit", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021", "Test Provider", 100, "Self"),
				new ClaimForm(2, "Sumit", "Kumar", "6/20/2021", "6/20/2021", "6/20/2021", "Test Provider B", 500, "Self"))
				.collect(Collectors.toList()));
		assertEquals(2, claimService.getAllClaim().size());
	}
	
	@Test
	public void deleteClaimByIdTest() {
		claimService.deleteClaimById("1");
		verify(claimRepository, times(1)).deleteById(1);
		
	}
	
	@Test
	public void fetchClaimByIdTest() {
		String id = "1";
		ClaimForm claim = new ClaimForm(1, "Amit", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021", "Test Provider", 100,
				"Self");
		Optional<ClaimForm> optionalClaim = Optional.of(claim);
		when(claimRepository.findById(Integer.parseInt(id))).thenReturn(optionalClaim);
		assertEquals(optionalClaim, claimService.fetchClaimById("1"));
	}
	
	@Test
	public void updateClaimFormByIdTest() {
		String id = "1";
		ClaimForm claim = new ClaimForm(1, "Amit", "Kumar", "6/22/2021", "6/22/2021", "6/22/2021", "Test Provider", 100,
				"Self");
		Optional<ClaimForm> optionalClaim = Optional.of(claim);
		when(claimRepository.findById(Integer.parseInt(id))).thenReturn(optionalClaim);
		optionalClaim.get().setFirstName("Name changed");
		when(claimRepository.save(optionalClaim.get())).thenReturn(optionalClaim.get());
	}
	
	

}
