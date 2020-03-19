package com.tensorflow.model.server.controllers;

import com.tensorflow.model.server.DAO.NasaPCA;
import com.tensorflow.model.server.Service.NasaPCAService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
class PredictionController {

    @Autowired
    private NasaPCAService nasaPCAService;
    private int nextPCA = 0;

    @GetMapping("/predict")
    public String predict() {


        return "{ \"predictions\": [[82.9299545]]}";
    }



}