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

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        JSONObject sensoryData = new JSONObject();
        double[][][]array = {{{0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561},
                {0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561}}};
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
        findLatestThirtyRows();

        System.out.println("response " + response);
        return response;
    }

    //TODO join this with DataController
    public List<NasaPCA> findLatestThirtyRows() {
        List<NasaPCA> rows = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            rows.add(nasaPCAService.findById(nextPCA));
            nextPCA += 1;
        }
        return rows;
    }

    private double[][][] parse30Rows() {
        double[][][] array = new double[1][30][11];
        List<NasaPCA> rows = findLatestThirtyRows();
        int index = 0;
        for (NasaPCA row : rows) {
            array[0][index][0] = row.getS1();
            array[0][index][1] = row.getS2();
            array[0][index][2] = row.getS3();
            array[0][index][3] = row.getS4();
            array[0][index][4] = row.getS5();
            array[0][index][5] = row.getS6();
            array[0][index][6] = row.getS7();
            array[0][index][7] = row.getS8();
            array[0][index][8] = row.getS9();
            array[0][index][9] = row.getS10();
            array[0][index][10] = row.getS11();
        }
        return array;
    }

}