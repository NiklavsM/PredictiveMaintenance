package com.tensorflow.model.server.Service;

import com.tensorflow.model.server.DAO.NasaPCA;

public interface NasaPCAService {
    NasaPCA findById(Integer id);
}
