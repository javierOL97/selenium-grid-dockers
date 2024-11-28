Feature: Signup and Login functionalities.

  @Smoke
  Scenario: Move to login page
    Given User moves to signup-login page
	
	@Smoke
  Scenario: Move to login page
    Given User moves to signup-login page
    
	@SmokeFail
  Scenario: Move to login page
    Given User moves to signup-login page
    Then User clicks on Delete account button