@LoginTest
Feature:  Dropdown Test
  Scenario: Dropdown test in guru99 Register form
  
    Given User navigates to guru99 register page
     When User should be on register page
      And User selects the country in dropdown
      And user ckick on submit
     Then User should be on register_success page
  
  
