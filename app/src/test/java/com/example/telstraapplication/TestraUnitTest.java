package com.example.telstraapplication;

import com.example.telstraapplication.model.ContentInfo;
import com.example.telstraapplication.model.DropBoxContent;
import com.example.telstraapplication.network.ApiInterface;
import com.example.telstraapplication.presenter.ContentInfoDetailsContract;
import com.example.telstraapplication.presenter.ContentInfoDetailsModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestraUnitTest {
    /*@Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/

    @Mock
    private ApiInterface charactersDataSource;

    @Mock
    private ContentInfoDetailsContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {
        ContentInfoDetailsModel service = Mockito.spy(ContentInfoDetailsModel.class);
        DropBoxContent dropBoxContent = Mockito.mock(DropBoxContent.class);
        ArrayList<ContentInfo> responseList = new ArrayList<>();
        responseList.add(new ContentInfo("", "", ""));
        responseList.add(new ContentInfo("", "", ""));
        when(dropBoxContent.getRows())
                .thenReturn(responseList);
        assertTrue(service.isSuccessful(dropBoxContent));

    }
    @Test
    public void testIsSuccessfulResponse_Error() {
        ContentInfoDetailsModel service = Mockito.spy(ContentInfoDetailsModel.class);
        DropBoxContent dropBoxContent = Mockito.mock(DropBoxContent.class);
        ArrayList<ContentInfo> responseList = null;
        Mockito.when(dropBoxContent.getRows()).thenReturn(responseList);
        assertFalse(service.isSuccessful(dropBoxContent));
    }

    @Test
    public void testMessage_Error() {
        ContentInfoDetailsModel service = Mockito.spy(ContentInfoDetailsModel.class);
        assertEquals("Error in getting data. Please try again later.", service.getErrorMessage());
    }

}