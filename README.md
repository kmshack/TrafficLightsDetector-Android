TrafficLights Detector For Android
===================

I tried to recognize the traffic signal on the Android device. 
Machine learning is done through thousands of images, and the data is used to determine the signal.

Data was modeled using [Caffe](http://caffe.berkeleyvision.org/), [CaffeMobile](https://github.com/sh1r0/caffe-android-lib) library was used to drive Caffe to Android.

We also refer to the results of [davidbrai](https://github.com/kmshack/TrafficLightDetector-Android) to minimize traffic signal data collection and size. I also referenced Google's [Tensorflow](https://www.tensorflow.org/mobile/) Android code.

----------


Detail
-------------

Identify traffic signals based on davidbrai's findings and published data. 94% recognition rate, extract 2~3 frames per second from the image, transform it into comparable size, and judge the signal through the learned Caffe modeled data.


> **Note:**

> - 94% recognition rate.
> - Compared with data learned through 2~3 frames per second.
> - Change byte data to the best recognizable size (255 pixels)

----------


How to run
-------------

Download the source code and open it in the Android studio. 
Copy the modeled prototxt and caffemodel files from the trafficlightsmodel directory to your device for your environment.

    private String modelDir = sdcard + "/model";
    private String modelProto = modelDir + "/deploy.prototxt";
    private String modelBinary = modelDir + "/train_squeezenet_scratch_trainval_manual_p2__iter_8000.caffemodel";

----------


Run
-------------

![enter image description here](https://raw.githubusercontent.com/kmshack/TrafficLightDetector-Android/master/screen.jpg)


----------


License
----------
Apache License, Version 2.0
