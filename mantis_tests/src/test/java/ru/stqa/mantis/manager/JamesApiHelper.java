package ru.stqa.mantis.manager;


import okhttp3.*;
import ru.stqa.mantis.model.UserDate;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase {

    OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json");

    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void addUser(UserDate user) {

        RequestBody body = RequestBody.create(
                String.format("{\"password\":\"%s\"}", user.password()), JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", manager.property("jamesApiBaseUrl") ,user.email()))
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }






    }
}
