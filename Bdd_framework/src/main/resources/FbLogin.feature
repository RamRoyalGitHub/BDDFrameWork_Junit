@LoginTest
Feature:  login Test
  Scenario: FB login test
  
    Given User navigates to Fb login page
     When User should be login to FB
     Then User should be on home 
  
