package com.hw.sol.datasource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hw.sol.entity.CustomerTransaction;

@Service
public class CustomerTransactionService {

	//*************************************************************************
	//***  Adding JPA pseudo repository as a potential source of Customer Transaction data
	//***  adding method to mock with JUnit for solution testing
	//***  Another option for source data would be a web service call or even
	//***  native sql to do the grouping and summing
	//*************************************************************************
	public List<CustomerTransaction> findAll() {
		
        List<CustomerTransaction> allTransactions = new ArrayList<>();
        //*** fill out list
        allTransactions.add(populateTrans(Long.valueOf(1), -4, 75.77));
        allTransactions.add(populateTrans(Long.valueOf(2), -4, 99.99));
        allTransactions.add(populateTrans(Long.valueOf(1), -4, 200.59));
        allTransactions.add(populateTrans(Long.valueOf(2), -4, 400.00));

        allTransactions.add(populateTrans(Long.valueOf(1), -3, 45.12));
        allTransactions.add(populateTrans(Long.valueOf(2), -3, 9.99));
        allTransactions.add(populateTrans(Long.valueOf(1), -3, 103.45));
        allTransactions.add(populateTrans(Long.valueOf(2), -3, 50.99));
        allTransactions.add(populateTrans(Long.valueOf(1), -3, 200.59));
        allTransactions.add(populateTrans(Long.valueOf(2), -3, 100.11));
        
        allTransactions.add(populateTrans(Long.valueOf(1), -2, 45.12));
        allTransactions.add(populateTrans(Long.valueOf(2), -2, 9.99));
        allTransactions.add(populateTrans(Long.valueOf(1), -2, 103.45));
        allTransactions.add(populateTrans(Long.valueOf(2), -2, 50.99));
        allTransactions.add(populateTrans(Long.valueOf(1), -2, 200.59));
        allTransactions.add(populateTrans(Long.valueOf(2), -2, 100.11));

        allTransactions.add(populateTrans(Long.valueOf(3), -2, 178.23));
        
		return allTransactions;
		
	}

	private CustomerTransaction populateTrans(Long customerId, Integer monthDiff, Double transAmount) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, monthDiff);
        CustomerTransaction c1 = new CustomerTransaction();
        c1.setCustomerId(customerId);
        c1.setTransactionDate(new Timestamp(calendar1.getTimeInMillis()));
        c1.setTransactionAmount(transAmount);

        return c1;
		
		
	}

}
