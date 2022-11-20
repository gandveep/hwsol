package com.hw.sol.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hw.sol.model.CustomerRewards;
import com.hw.sol.service.RewardsService;

@RestController
@RequestMapping(path="/api")
public class RewardsController {

	private final RewardsService rewardsService;
	
	public RewardsController(final RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}
	
	@GetMapping(value = "/v1/customers/rewards")
	public List<CustomerRewards> getCustomerRewards() {
		return rewardsService.generateCustomerRewards();
	}
}
