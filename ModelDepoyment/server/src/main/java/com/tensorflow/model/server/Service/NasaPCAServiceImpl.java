package com.tensorflow.model.server.Service;

import com.tensorflow.model.server.DAO.NasaPCA;
import com.tensorflow.model.server.Repository.NasaPCARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NasaPCAServiceImpl implements NasaPCAService{

    @Autowired
    NasaPCARepository nasaPCARepository;

    public NasaPCA findById(Integer id) {
        return nasaPCARepository.findById(id);
    }
}
