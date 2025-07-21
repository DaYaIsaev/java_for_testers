package ru.stqa.mantis.common;

import ru.stqa.mantis.model.MailMessage;
import ru.stqa.mantis.model.UserDate;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomNumber(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(9);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> '1' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomFile(String dir){
       var fileNames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }

    public static String extractUrl (List<MailMessage> messages) {
        var text = messages.get(messages.size()-1).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            return url;
        }
        return "No link";

    }
}
