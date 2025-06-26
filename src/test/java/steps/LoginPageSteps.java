package steps;

import context.ContextStore;
import io.cucumber.java.en.Given;
import pages.LoginPage;

import java.util.Arrays;
import java.util.List;

public class LoginPageSteps {

    LoginPage loginPage = new LoginPage();

    @Given("Save accepted usernames to context")
    public void getLoginCredentials() {
        List<String> loginCredentials = Arrays.stream(loginPage.loginCredentialsText.getText().split("\n")).toList();
        ContextStore.put("loginCredentials", loginCredentials);
    }

}
