# Charter Communications Exercise

### Solution assumptions:

Based on my interpretation of the requirements statement, here are my coded assumptions:

* I used integer point totals and not fractional/partial points
* Over 50 dollars and over 100 dollars spent implies reward points start at 51 and 101 dollars spent.
* I provided monthly totals and a Grand Total for each customer
* Data source is static data, but could be jpa, web service(s), native SQL, etc.   
* Added test classes using JUnit4
* Rest endpoint is simply to trigger the data retrieval.  Similar to a report endpoint.
* lower and upper bound reward thresholds and multipliers are config properties.  Could be externalized.

