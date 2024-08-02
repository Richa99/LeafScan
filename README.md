# LeafScan : Leaf Disease Detection Android Application

## Overview

This Android application detects diseases in banana and papaya leaves using image classification models. It detects various diseases, enhancing agricultural productivity through early and precise disease detection.

## Features

- Detects diseases in banana and papaya leaves.
- Identifies the following diseases:
  - **Banana Leaves:**
    - Cordana_BananaLeaf_Disease
    - Pestalotiopsis_BananaLeaf_Disease
    - Sigatoka_BananaLeaf_Disease
    - Healthy_BananaLeaf
  - **Papaya Leaves:**
    - Anthracnose_PapayaLeaf_Disease
    - BacterialSpot_PapayaLeaf_Disease
    - Curl_PapayaLeaf_Disease
    - RingSpot_PapayaLeaf_Disease
    - Healthy_PapayaLeaf
- Utilizes Teachable Machine for model training and exports the model as TensorFlow Lite for integration.
- Features object detection to ensure the input image is a leaf image, prompting the user to input the correct image if a non-leaf image is presented.

## Technologies Used

- **Android Studio** : Used Java programming on android studio to buid this application.
- **Teachable Machine**: Used for training the image classification model.
- **TensorFlow Lite**: Model exported and integrated into the Android application for efficient on-device inference.
  
