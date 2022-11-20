package com.hw.sol.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hw.sol.datasource.CustomerTransactionService;
import com.hw.sol.entity.CustomerTransaction;
import com.hw.sol.model.CustomerRewards;
import com.hw.sol.model.RewardTotal;

@Service
public class RewardsServiceImpl implements RewardsService {
	
	@Value("${lower.reward.bound}")
	private String lowerRewardBound;

	@Value("${upper.reward.bound}")
	private String upperRewardBound;

	@Value("${lower.reward.multiplier}")
	private String lowerRewardMultiplier;

	@Value("${upper.reward.multiplier}")
	private String upperRewardMultiplier;

	private final CustomerTransactionService customerTransactionRepository;
	
	public RewardsServiceImpl(final CustomerTransactionService customerTransactionRepository) {
		this.customerTransactionRepository = customerTransactionRepository;
	}
	
	@Override
	public List<CustomerRewards> generateCustomerRewards() {

		Map<Long, Map<String, Integer>> customerTotals = new HashMap<>();

		List<CustomerTransaction> allTransactions = customerTransactionRepository.findAll();

		Double lowerBound = Double.parseDouble(lowerRewardBound);
		Double upperBound = Double.parseDouble(upperRewardBound);
		Double lowerMultiplier = Double.parseDouble(lowerRewardMultiplier);
		Double upperMultiplier = Double.parseDouble(upperRewardMultiplier);
		String grandTotal = "GrandTotal";
		
       for (CustomerTransaction customerTransaction: allTransactions) {
           Calendar calendar = Calendar.getInstance();
           calendar.setTimeInMillis(customerTransaction.getTransactionDate().getTime());
	       String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);

	       int points = customerTransaction.getTransactionAmount().intValue();
	       if (customerTransaction.getTransactionAmount() > upperBound) {
	            points = ((points - upperBound.intValue()) * upperMultiplier.intValue()) + (lowerBound.intValue() * lowerMultiplier.intValue());
	        } else {
	            points = Math.max(points - lowerBound.intValue() * lowerMultiplier.intValue(), 0);
	        }

	        if (customerTotals.containsKey(customerTransaction.getCustomerId())) {
	            customerTotals.get(customerTransaction.getCustomerId()).merge(month, points, (oldValue, newValue) -> oldValue + newValue);
	        } else {
	            Map<String, Integer> custTot = new HashMap<>();
	            custTot.put(month, points);
	            customerTotals.put(customerTransaction.getCustomerId(), custTot);
	        }
	        customerTotals.get(customerTransaction.getCustomerId()).merge(grandTotal, points, (oldValue, newValue) -> oldValue + newValue);
	    }
	    List<CustomerRewards> outputRewards = customerTotals.entrySet().stream()
	        .map(e -> new CustomerRewards(e.getKey(),
	             e.getValue()
	             .entrySet()
	             .stream()
	             .map((Map.Entry<String, Integer> f)
	                   -> new RewardTotal(f.getKey(), f.getValue()))
	             .collect(Collectors.toList())
	       )).collect(Collectors.toList());
		
	    return outputRewards;
	}
}
