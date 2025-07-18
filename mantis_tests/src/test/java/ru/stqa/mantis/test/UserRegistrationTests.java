package ru.stqa.mantis.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String username = "user5";
        var email = String.format("%s@localhost", username);
        final String password = "password";
        app.jamesCli().addUser(email, password);
        app.session().signUp(username, email, password);
        var messages = app.mail().receive( username, password, Duration.ofSeconds(10));
        var text = messages.get(messages.size()-1).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
            app.driver().get(url);
            app.session().updateUser(username, password);
        }
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
