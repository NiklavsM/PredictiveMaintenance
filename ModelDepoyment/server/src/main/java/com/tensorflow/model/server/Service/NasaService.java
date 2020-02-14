package com.tensorflow.model.server.Service;

import com.tensorflow.model.server.DAO.Nasa;

public interface NasaService {

    Nasa findById(Integer id);
}
