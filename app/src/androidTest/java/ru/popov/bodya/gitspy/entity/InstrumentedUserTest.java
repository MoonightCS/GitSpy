package ru.popov.bodya.gitspy.entity;

import android.os.Parcel;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InstrumentedUserTest {

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

}