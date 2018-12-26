@LoginTest
Feature:  login Test
@RegressionTest @SomkeTest
  Scenario: Microsoft login test
  
    Given User navigates to microsoft login page
     When User should be login to microsoft account
     Then User should be on MShome 
  
