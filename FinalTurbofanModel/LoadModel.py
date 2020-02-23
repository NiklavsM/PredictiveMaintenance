import tensorflow as tf
from tensorflow import keras
from keras.models import model_from_json
from keras.models import Model
import pickle
from sklearn import preprocessing
from sklearn.decomposition import PCA
import numpy as np
import json

# To just use the getPrediction method write 'import all' in your environment
# To see the predictList as well call 'from LoadModel import *'


json_file = open('savedStates/model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
# load weights into new model
loaded_model.load_weights("savedStates/model.h5")
print("Loaded model from disk")

loaded_model.compile(optimizer='adam',
              loss='mse', 
              metrics=['mae'])
scaler = pickle.load(open("savedStates/scaler.p", "rb"))

pca = pickle.load(open("savedStates/pca.p", "rb"))

print("Loaded Scaler and PCA")

predictList = []

# Will return a 1-d, empty array if not enough lines yet, will return 1-d, 1-element array if it has enough lines(30 lines required for prediction)
def getPrediction(line) :
    line = line["instances"]
    print(line)
    predictList.append(line[0])
    
    if len(predictList) < 30:
        return []    
    else:
        if len(predictList) > 30:
            predictList.pop(0)
        
        predictNumpy = np.array(predictList)
        standardise = scaler.transform(predictNumpy)
        reduced = pca.transform(standardise)
        
        
        finalArray = np.array([reduced])
        result = loaded_model.predict(finalArray)
        return result[0]