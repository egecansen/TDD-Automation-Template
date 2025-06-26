Feature: Sauce

  @Web-UI @SCN-Sauce-1
  Scenario: Add 2 items to the shopping cart and checkout
    * Navigate to url: https://www.saucedemo.com/
    * Verify presence of element userNameInput on the LoginPage
    * Click the userNameInput on the LoginPage
    * Fill form input on the LoginPage
      | Input Element | Input         |
      | userNameInput | standard_user |
      | passwordInput | secret_sauce  |
    * Click the loginButton on the LoginPage
    * Wait for element pageTitle on the AllProductsPage to be visible
    * Click listed element Sauce Labs Bike Light from productTitles list on the AllProductsPage
    * Save the selected product price to context
    * Click the addToCartButton on the ProductPage
    * Click the backToProductsButton on the ProductPage
    * Click listed element Test.allTheThings() T-Shirt (Red) from productTitles list on the AllProductsPage
    * Save the selected product price to context
    * Click the addToCartButton on the ProductPage
    * Click component element shoppingCart of MainNavigation component on the ProductPage
    * Wait for element cartQuantityLabel on the ShoppingCartPage to be visible
    * Click the checkOutButton on the ShoppingCartPage
    * Fill form input on the YourInformationPage
      | Input Element   | Input  |
      | firstNameInput  | Egecan |
      | lastNameInput   | Sen    |
      | postalCodeInput | 444    |
    * Click the continueButton on the YourInformationPage
    * Validate the total amount

  @Web-UI @SCN-Sauce-2
  Scenario: Sort items A-Z and verify the order
    * Navigate to url: https://www.saucedemo.com/
    * Verify presence of element userNameInput on the LoginPage
    * Click the userNameInput on the LoginPage
    * Fill form input on the LoginPage
      | Input Element | Input         |
      | userNameInput | standard_user |
      | passwordInput | secret_sauce  |
    * Click the loginButton on the LoginPage
    * Wait for element pageTitle on the AllProductsPage to be visible
    * Click the sortContainer on the AllProductsPage
    * Click listed element Name (Z to A) from sortContainerOptions list on the AllProductsPage
    * Save the product names and verify they are in reversed alphabetical order

  @Web-UI @SCN-Sauce-3
  Scenario: Login with wrong password
    * Navigate to url: https://www.saucedemo.com/
    * Verify presence of element userNameInput on the LoginPage
    * Click the userNameInput on the LoginPage
    * Fill form input on the LoginPage
      | Input Element | Input          |
      | userNameInput | standard_user  |
      | passwordInput | wrong_password |
    * Click the loginButton on the LoginPage
    * Verify presence of element loginErrorMessage on the LoginPage
    * Verify the text of loginErrorMessage on the LoginPage to be: Epic sadface: Username and password do not match any user in this service
