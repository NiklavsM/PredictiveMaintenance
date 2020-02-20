package com.tensorflow.model.server.Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
class PredictionController {

    @GetMapping("/predict")
    public String predict() {
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        JSONObject sensoryData = new JSONObject();
        double[][] array = {{0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561, 0.250000, 0.170090, 0.257022, 0.250000, 0.666667, 0.662110}};
        try {
            sensoryData.put("instances", new JSONArray(array));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<String>(sensoryData.toString(), headers);

        // create a map for post parameters
        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8501/v1/models/predictive_maintenance:predict";
        String response = rt.postForObject(url, request, String.class);
        System.out.println("response " + response);
        return response;
    }

}