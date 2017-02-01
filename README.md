

TrafficLights Detector For Android
===================

(한국어는 하단 참조)


I tried to recognize the traffic signal on the Android device. 
Machine learning is done through thousands of images, and the data is used to determine the signal.

Data was modeled using [Caffe](http://caffe.berkeleyvision.org/), [CaffeMobile](https://github.com/sh1r0/caffe-android-lib) library was used to drive Caffe to Android.

We also refer to the results of [davidbrai](https://github.com/davidbrai/deep-learning-traffic-lights) to minimize traffic signal data collection and size. I also referenced Google's [Tensorflow](https://www.tensorflow.org/mobile/) Android code.

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

Download the source code and open it in the Android studio. ([APK Download](https://github.com/kmshack/TrafficLightsDetector-Android/blob/master/tldetector.apk))

Copy the modeled prototxt and caffemodel files from the [trafficlightsmodel](https://github.com/kmshack/TrafficLightsDetector-Android/tree/master/trafficlightsmodel) directory to your device for your environment.

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
Apache License 2017 @kmshack

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

--------------------------


한국어
=====

안드로이드에서 기계학습을 통한 교통신호 판단하는 앱 구현하기.
 
----------


[davidbrai](https://github.com/davidbrai/deep-learning-traffic-lights)이 공개한 교통신호를 판단하기위해 만들어진 모델링한 데이터를 가지고 안드로이드에서 작동시켜보았습니다. 

[Caffe](http://caffe.berkeleyvision.org/)라는 모델링된 데이터를 안드로이드에서 구동하기위해 [CaffeMobile](https://github.com/sh1r0/caffe-android-lib) 라이브러리를 사용하였으며, 실시간 영상의 이미지를 초당 2~3프레임씩 뽑아낸 후 학습된 데이터가 판별가능한 가장 작은 사이즈(255픽셀)로 인코딩하여 신호를 판단합니다. 

davidbrai가 수집한 모델링 데이터는 약 94%의 정확도를 가지며, 3MB보다 작은 용량으로 빠르게 결과를 뽑아 낼 수 있습니다. (빠른 이미지 처리는 구글 텐서플로우 안드로이드의 JNI로 구현된 ImageUtils 참조) 학습시에도 약 255픽셀정도의 이미지로 학습된 데이터이기때문에 입력 데이터도 이와 비슷한 조건으로 하는것이 좋습니다.

1프레임당 처리속도는 약 200ms내외 이며 이미지사이즈, 폰 성능에따라 달라질 수 있습니다.


