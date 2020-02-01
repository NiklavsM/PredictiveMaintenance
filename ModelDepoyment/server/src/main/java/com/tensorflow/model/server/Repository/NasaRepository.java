package com.tensorflow.model.server.Repository;

import com.tensorflow.model.server.DAO.Nasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasaRepository extends JpaRepository<Nasa, String> {
}

