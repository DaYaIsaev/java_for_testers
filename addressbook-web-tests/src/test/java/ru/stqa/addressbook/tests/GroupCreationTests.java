package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static Stream<GroupData> randomGroups() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));

        return Stream.generate(randomGroup).limit(3);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("randomGroups")
    public void canCreateGroup(GroupData group) {

        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();

        var maxId = newGroups.get(newGroups.size() - 1);
        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).collect(Collectors.toList());
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId.id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }

}
