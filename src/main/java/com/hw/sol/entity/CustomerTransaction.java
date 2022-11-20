package com.hw.sol.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerTransaction {
	
	private Long customerId;
	
	private Timestamp transactionDate;
	
	private Double transactionAmount;

}
