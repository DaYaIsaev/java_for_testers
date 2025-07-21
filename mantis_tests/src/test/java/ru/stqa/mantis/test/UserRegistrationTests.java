package ru.stqa.mantis.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserDate;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var user = new UserDate()
                .withUsername("user5")
                .withPassword("password");
        app.jamesApi().addUser(user);
        app.rest().createUser(user);
        app.session().signUp(user);
        var messages = app.mail().receive(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages);
        app.driver().get(url);
        app.session().updateUser(user);
        app.http().login(user);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
