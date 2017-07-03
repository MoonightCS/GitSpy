package ru.popov.bodya.gitspy.entity;

import android.os.Parcel;

import org.junit.Test;

import ru.popov.bodya.gitspy.EntitiesGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserInstrumentedTest {

    @Test
    public void testUserParcelable() {
        User expected = EntitiesGenerator.createRandomUser();

        Parcel parcel = Parcel.obtain();

        try {
            expected.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            User actual = User.CREATOR.createFromParcel(parcel);
            assertThat(actual, is(expected));
        } finally {
            parcel.recycle();
        }
    }


    @Test
    public void testEquals() {
        String username = "MoonightCS";
        String avatarUrl = "https://avatars3.githubusercontent.com/u/9767952?v=3";
        double score = 37.574158;
        User firstUser = new User(username, score, avatarUrl);
        User secondUser = new User(username, score, avatarUrl);

        assertThat(firstUser.equals(secondUser), is(true));
        assertThat(secondUser.equals(firstUser), is(true));
        assertThat(firstUser.hashCode(), is(secondUser.hashCode()));
    }

}