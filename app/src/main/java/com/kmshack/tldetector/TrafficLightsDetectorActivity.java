package com.kmshack.tldetector;

import android.util.Size;
import android.widget.ImageView;

import com.kmshack.tldetector.env.Logger;
import com.sh1r0.caffe_android_lib.CaffeMobile;

import static com.kmshack.tldetector.R.id.result;

/**
 * Created by kmshack on 2017/01/31.
 */
public class TrafficLightsDetectorActivity extends DetectorActivity {

    private static final Logger LOGGER = new Logger();

    private static Integer[] LIGHTS = new Integer[]{R.mipmap.none, R.mipmap.stop, R.mipmap.start};

    static {
        System.loadLibrary("caffe");
        System.loadLibrary("caffe_jni");
        System.loadLibrary("imageutils_jni");
    }

    private String modelProto = "deploy.prototxt";
    private String modelBinary = "train_squeezenet_scratch_trainval_manual_p2__iter_8000.caffemodel";

    private CaffeMobile caffeMobile;
    private ImageView resultView;

    @Override
    public void onPreviewSizeChosen(Size size, int rotation) {
        super.onPreviewSizeChosen(size, rotation);

        resultView = (ImageView) findViewById(result);
    }

    @Override
    public void onDetector(final String bitmap) {

        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        final int result = caffeMobile.predictImage(bitmap)[0];
                        LOGGER.e("predictImage >> %d", result);

                        requestRender();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resultView.setImageResource(LIGHTS[result]);
                                computing = false;
                            }
                        });

                    }
                });

    }

    @Override
    protected void setFragment() {
        super.setFragment();

        Utils.makeModelDir(getApplicationContext());
        Utils.copyFile(getApplicationContext(), modelProto);
        Utils.copyFile(getApplicationContext(), modelBinary);

        caffeMobile = new CaffeMobile();
        caffeMobile.setNumThreads(4);
        caffeMobile.loadModel(Utils.getModelFile(getApplicationContext(), modelProto).getAbsolutePath(),
                Utils.getModelFile(getApplicationContext(), modelBinary).getAbsolutePath());

        float[] meanValues = {104, 117, 123};
        caffeMobile.setMean(meanValues);
        caffeMobile.setScale(320f);
        caffeMobile.enableLog(false);
    }
}
