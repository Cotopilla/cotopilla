package helpers.withlogin;

import api.authorization.AuthorizationApi;
import api.authorization.AuthorizationResponseDto;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        AuthorizationResponseDto response = AuthorizationApi.demoqaAuth();

        String userIdValue = response.getUserId();
        String tokenValue = response.getToken();
        String expiresValue = response.getExpires();

        open("/images/Toolsqa.jpg");
        getWebDriver().manage().addCookie(new Cookie("userID", userIdValue));
        getWebDriver().manage().addCookie(new Cookie("expires", expiresValue));
        getWebDriver().manage().addCookie(new Cookie("token", tokenValue));
    }
}
