package ru.stqa.mantis.manager;


import okhttp3.*;
import ru.stqa.mantis.model.UserDate;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase {

    OkHttpClient client;


    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(UserDate user) {
        RequestBody formBody = new FormBody.Builder()
                .add("username",user.username())
                .add("password", user.password())
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/login.php", manager.property("web.baseUrl")))
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.property("web.baseUrl"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String body = response.body().string();
            return body.contains("<span class=\"user-info\">");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
