package com.tensorflow.model.server.controllers;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.Repository.NasaRepository;
import com.tensorflow.model.server.Service.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
class DataController {

    @Autowired
    private NasaService nasaService;
    private int nextId = 1;

    @GetMapping(value = "/nasa/",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public Nasa findLatestRow(){
        Nasa out = nasaService.findById(nextId);
        nextId++;
        return out;
    }


}
