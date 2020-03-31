# Deployment instructions

## System requirements

* Docker installed
* Maven installed
* Git installed
* Npm installed

## Deploying the trained model

* TensorFlow serving
    * Clone https://github.com/tensorflow/serving
    * Create a folder and put the model to be deployed under /serving/tensorflow_serving/servables/tensorflow/testdata/
    * Run command : docker run -t --rm -p 8501:8501     -v "/$(pwd)/serving/tensorflow_serving/servables/tensorflow/testdata/*Model_directory_name*:/models/*Model_name*" -e MODEL_NAME=*Model_name*     tensorflow/serving &
* Spring boot
    * Navigate to the directory ModelDeployment/server
    * Run "mvn install"
    * To start the server run "mvn spring-boot:run"
* React client
    * Navigate to the directory client/
    * Run "npm install"
    * To start the client run "npm start"
