##Scripts
### Start serving the TF model
docker run -t --rm -p 8501:8501     -v "/$(pwd)/ModelServing/tensorflow_serving/servables/tensorflow/testdata/saved_model_predictive_maintenance:/models/predictive_maintenance" -e MODEL_NAME=predictive_maintenance     tensorflow/serving &


### To test that the model is served
curl -d '{"instances": [[0.741379, 0.500000, 0.370482, 0.285154, 0.408001, 1.0, 0.679549, 0.196970, 0.105717, 0.255952, 0.573561, 0.250000, 0.170090, 0.257022, 0.250000, 0.666667, 0.662110]]}' -X POST http://localhost:8501/v1/models/predictive_maintenance:predict