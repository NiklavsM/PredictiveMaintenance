package com.tensorflow.model.server.controllers;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.Repository.NasaRepository;
import com.tensorflow.model.server.Utility.CsvReader;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/data")
class DataController {
    NasaRepository nasaRepository;
    @GetMapping(value = "/nasa/")
    public Double getLatestRow(){
        return 0.0;
    }

    @PutMapping(value = "/nasa/", consumes = "text/csv")
    public void uploadNasa(@RequestBody InputStream stream){
        nasaRepository.saveAll(CsvReader.readCsv(Nasa.class, stream));
    }
}
