package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;
import ru.stqa.mantis.model.UserDate;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase {

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(UserDate user, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()){
            try {
                var inbox = getInbox(user);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();

                var result = Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent(m.getContent().toString());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (result.size() > 0){
                    return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail");
    }

    private Folder getInbox(UserDate user)  {

        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost", user.email(), user.password());
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void drain(UserDate user) {

        try {
            var inbox = getInbox(user);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }



}
