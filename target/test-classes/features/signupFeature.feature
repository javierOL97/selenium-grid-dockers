Feature: Signup and Login functionalities.

  @Regression
  Scenario Outline: Register successfully a new user 
    Given User moves to signup-login page
    And enters his <name> and <email> in signup fields
    When User clicks on submit button and verify that "Enter Account Information" title is visible
    And Fill details as <gender>, <name>, <email>, <password> and <dOb>
    And Also fills details as <name>, <lname>, <company>, <address>, <country>, <state>, <city>, <zipcode>, <mobilenumber>
    And User press create account button
    And Verifies the create account success message is visible
    And User clicks continue button and Verifies that is logged with his <name>
    Then User clicks on Delete account button
    And Verifies the Delete account message is visible
    
    Examples: 
      | name        | email               | gender   | password | lname    | company | address     | country         | state        | city          | zipcode | mobilenumber | dOb          |
      | "Francisco" | "frank@test.com"    | "Male"   | "pwd123" | "Ortiz"  | "AT"    | "Copey 608" | "United States" | "California" | "Los Angeles" | "03801" | "2122334419" | "01/01/2000" |
      | "Jennifer"  | "jennifer@test.com" | "Female" | "pwd321" | "Vargas" | "Maver" | "Copey 608" | "United States" | "California" | "Los Angeles" | "03801" | "2122334420" | "01/01/2000" |
