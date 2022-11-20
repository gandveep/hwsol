package com.hw.sol.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.hw.sol.datasource.CustomerTransactionService;
import com.hw.sol.model.CustomerRewards;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {
	
	private RewardsService rewardsService;
	private CustomerTransactionService customerTransactionService;
	
	@Before
	public void setup() {

		//*** Don't need to mock since we're using static data
		customerTransactionService = new CustomerTransactionService();
		rewardsService = new RewardsServiceImpl(customerTransactionService);

		ReflectionTestUtils.setField(rewardsService,"lowerRewardBound","50.00");
		ReflectionTestUtils.setField(rewardsService,"upperRewardBound","100.00");
		ReflectionTestUtils.setField(rewardsService,"lowerRewardMultiplier","1");
		ReflectionTestUtils.setField(rewardsService,"upperRewardMultiplier","2");
		
	}
	
	@Test
	public void generateCustomerRewardsTest() {
		
		List<CustomerRewards> outRewards = rewardsService.generateCustomerRewards();
		
		assertThat(outRewards)
		.isNotNull()
		.hasSize(3)
		.filteredOn(customer -> customer.getCustomerId() == 3).isNotNull();

		for (CustomerRewards c1: outRewards) {
			Map<String, Integer> values = c1.getPeriodRewards().stream()
					.collect(Collectors.toMap(x -> x.getTimePeriod(), x -> x.getRewardPoints()));
			if (c1.getCustomerId() == 1) {
				assertThat(values.get("7")).isEqualTo(275);
				assertThat(values.get("GrandTotal")).isEqualTo(887);
			}
			if (c1.getCustomerId() == 2) {
				assertThat(values.get("8")).isEqualTo(50);
				assertThat(values.get("GrandTotal")).isEqualTo(799);
			}
			if (c1.getCustomerId() == 3) {
				assertThat(values.get("9")).isEqualTo(206);
				assertThat(values.get("GrandTotal")).isEqualTo(206);
			}
		}
	}
}
