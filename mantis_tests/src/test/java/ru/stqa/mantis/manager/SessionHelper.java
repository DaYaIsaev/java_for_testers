package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

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


    public void signUp(String username, String email, String password) {
        click(By.cssSelector("a.back-to-login-link.pull-left[href=\"signup_page.php\"]"));
        type(By.name("username"),username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }


    public void updateUser(String realName, String password) {
        type(By.name("realname"), realName);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type=\"submit\"]"));
    }
}
