package ru.popov.bodya.gitspy.entity;

import org.junit.Test;

import ru.popov.bodya.gitspy.model.users.entity.User;
import ru.popov.bodya.gitspy.model.users.entity.UsersGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testEquals() {
        String username = "MoonightCS";
        String avatarUrl = "https://avatars3.githubusercontent.com/u/9767952?v=3";
        double score = 37.574158;
        User firstUser = UsersGenerator.createSpecifiedUser(username, avatarUrl, score);
        User secondUser = UsersGenerator.createSpecifiedUser(username, avatarUrl, score);

        assertThat(firstUser.equals(secondUser), is(true));
        assertThat(secondUser.equals(firstUser), is(true));
        assertThat(firstUser.hashCode(), is(secondUser.hashCode()));
    }

    @Test
    public void testNotEquals() {
        String username = "MoonightCS";
        String secondUsername = "MoonlightCS";
        String avatarUrl = "https://avatars3.githubusercontent.com/u/9767952?v=3";
        double score = 37.574158;
        User firstUser = UsersGenerator.createSpecifiedUser(username, avatarUrl, score);
        User secondUser = UsersGenerator.createSpecifiedUser(secondUsername, avatarUrl, score);

        assertThat(firstUser.hashCode(), is(not(secondUser.hashCode())));
        assertThat(firstUser.equals(secondUser), is(false));
        assertThat(secondUser.equals(firstUser), is(false));
    }

}