package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserDate;

public class SessionHelper extends HelperBase{
    public SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }


    public void signUp(UserDate user) {
        click(By.cssSelector("a.back-to-login-link.pull-left[href=\"signup_page.php\"]"));
        type(By.name("username"),user.username());
        type(By.name("email"), user.email());
        click(By.cssSelector("input[type='submit']"));
    }


    public void updateUser(UserDate user) {
        type(By.name("realname"), user.username());
        type(By.name("password"), user.password());
        type(By.name("password_confirm"), user.password());
        click(By.cssSelector("button[type=\"submit\"]"));
    }
}
