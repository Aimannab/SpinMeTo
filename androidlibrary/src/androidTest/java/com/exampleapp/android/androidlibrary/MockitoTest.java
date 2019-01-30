package com.exampleapp.android.androidlibrary;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

//Not working - imcomplete
public class MockitoTest {

    @Mock
    RandomCountriesResultListActivity databaseMock = mock(RandomCountriesResultListActivity.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testQuery()  {
        //RandomCountriesResultListActivity t = new RandomCountriesResultListActivity();
        Cursor c = databaseMock.getAllRandomCountries();
        boolean check = c.getCount()>0;
        assertTrue(check);
        verify(databaseMock).getAllRandomCountries();
    }
}
