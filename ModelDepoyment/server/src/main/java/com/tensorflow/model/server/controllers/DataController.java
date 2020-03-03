package com.tensorflow.model.server.controllers;

import com.tensorflow.model.server.DAO.Nasa;
import com.tensorflow.model.server.DAO.NasaPCA;
import com.tensorflow.model.server.Service.NasaPCAService;
import com.tensorflow.model.server.Service.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/data")
class DataController {

    @Autowired
    private NasaService nasaService;

    @Autowired
    private NasaPCAService nasaPCAService;

    private int nextId = 0;

    private int nextPCA = 0;

    @CrossOrigin
    @GetMapping(value = "/nasa/",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Nasa> findLatestRow(){

        List<Nasa> rows = new LinkedList<>();
        for (int i = 0; i < 30; i++){
            rows.add(nasaService.findById(nextId));
            nextId += 1;
        }
        return rows;//        Nasa out = nasaService.findById(nextId);
//        nextId++;
//        return out;
    }

    // ToDo rework to 30 rows at once
    // ToDO Tests
    @GetMapping(value="/nasapca/",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NasaPCA> findLatestThirtyRows(){
        List<NasaPCA> rows = new LinkedList<>();
        for (int i = 0; i < 30; i++){
            rows.add(nasaPCAService.findById(nextPCA));
            nextPCA += 1;
        }
        return rows;
    }

}
