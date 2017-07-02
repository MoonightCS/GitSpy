package ru.popov.bodya.gitspy.activity.entity;

import android.os.Parcel;

import org.junit.Test;

import ru.popov.bodya.gitspy.EntitiesGenerator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserParcelable() {
        User expected = EntitiesGenerator.createRandomUser();

        Parcel parcel = Parcel.obtain();
        expected.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        User actual = User.CREATOR.createFromParcel(parcel);
        assertThat(actual, is(expected));
    }

}