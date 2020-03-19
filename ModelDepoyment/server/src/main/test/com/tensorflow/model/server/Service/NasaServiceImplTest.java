package com.tensorflow.model.server.Service;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.Repository.NasaRepository;
import com.tensorflow.model.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tensorflow.model.server.Utility.TestDataBuilder.populateNasaDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ServerApplication.class)
public class NasaServiceImplTest {

    @InjectMocks
    NasaServiceImpl nasaServiceImpl;

    @Mock
    NasaRepository nasaRepository;

    @Test
    void testFindById_WhenObjectPresent_ThenReturnObject(){
        Nasa expected = populateNasaDAO();
        when(nasaRepository.findById(expected.getId())).thenReturn(expected);
        assertEquals(expected, nasaServiceImpl.findById(expected.getId()));
    }

    @Test
    void testFindById_WhenObjectNotPresent_ThenReturnNull(){
        when(nasaRepository.findById(0)).thenReturn(null);
        assertNull(nasaServiceImpl.findById(0));
    }
}
