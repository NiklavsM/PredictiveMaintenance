package com.tensorflow.model.server.Repository;

import com.tensorflow.model.server.DAO.NasaPCA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NasaPCARepository extends JpaRepository<NasaPCA, String> {
    NasaPCA findById(@Param("id") Integer id);
}
