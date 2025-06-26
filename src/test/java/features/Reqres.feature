Feature: Reqres API

  @API-Test @SCN-Reqres-1
  Scenario: Get users list and validate the page number
    * Get user list from page 2
    * Verify the page number is 2 on the response

  @API-Test @SCN-Reqres-2
  Scenario: Login with a user
    * Login with the following user
      | email    | michael.lawson@reqres.in |
      | password | pistol                   |
    * Verify the login token is not null

  @API-Test @SCN-Reqres-3
  Scenario: Login with a random user
    * Get user list from page 2
    * Login with the following user
      | email    | CONTEXT-randomUserEmail |
      | password | pistol                  |
    * Verify the login token is not null

  @API-Test @SCN-Reqres-4
  Scenario: Update random user
    * Get user list from page 2
    * Update user with id CONTEXT-randomUserId with following
      | name | Can       |
      | job  | Fisherman |
    * Verify the update user response body

  @API-Test @SCN-Reqres-5
  Scenario: Delete random user
    * Get user list from page 2
    * Delete user with id CONTEXT-randomUserId

  @API-Test @SCN-Reqres-6
  Scenario: Create delayed user
    * Create and verify user with 3 seconds delay with following:
      | name | Mike   |
      | job  | Tailor |

  @API-Test @SCN-Reqres-7
  Scenario: Login with a non-existed user
    * Login with the following user
      | email    | test@test.in |
      | password | pistol       |
    * Verify the error message is: user not found

  @API-Test @SCN-Reqres-8
  Scenario: Login without submitting a password
    * Login with the following user
      | email | michael.lawson@reqres.in |
    * Verify the error message is: Missing password
