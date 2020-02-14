package com.tensorflow.model.server.Service;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.Repository.NasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NasaServiceImpl implements NasaService{

    @Autowired
    NasaRepository nasaRepository;
    public Nasa findById(Integer id) {
        return nasaRepository.findById(id);
    }
}
