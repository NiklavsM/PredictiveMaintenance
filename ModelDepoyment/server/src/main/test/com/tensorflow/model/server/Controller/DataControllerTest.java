package com.tensorflow.model.server.Controller;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.ServerApplication;
import com.tensorflow.model.server.Service.NasaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tensorflow.model.server.Utility.TestDataBuilder.populateNasaDAO;
import static com.tensorflow.model.server.Utility.TestDataBuilder.populateStoredNasaDAO1;
import static com.tensorflow.model.server.Utility.TestDataBuilder.populateStoredNasaDAO2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServerApplication.class)
public class DataControllerTest {
    @InjectMocks
    DataController dataController;

    @Mock
    NasaService nasaService;

    @Test
    void testFindLatestRow_WhenRowPresent_ThenReturnObject(){
        Nasa expected = populateStoredNasaDAO1();
        when(nasaService.findById(expected.getId())).thenReturn(expected);
        assertEquals(expected, dataController.findLatestRow());
    }

    @Test
    void testFindLatestRow_When2ndRowPresent_ThenReturnObject(){
        Nasa expected = populateStoredNasaDAO2();
        dataController.findLatestRow();
        when(nasaService.findById(expected.getId())).thenReturn(expected);
        assertEquals(expected, dataController.findLatestRow());
    }

    @Test
    void testFindLatestRow_WhenNoRowsPresent_ThenReturnNull(){
        for (int i = 0; i < 50; i++){
            dataController.findLatestRow();
        }
        assertNull(dataController.findLatestRow());
    }
}
