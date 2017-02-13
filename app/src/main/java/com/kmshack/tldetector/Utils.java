package com.kmshack.tldetector;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.kmshack.tldetector.env.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kmshack on 2017. 2. 13..
 */

public class Utils {
    private static final Logger LOGGER = new Logger();

    public static void copyAssetAll(Context context, String srcPath) {
        AssetManager assetMgr = context.getAssets();
        String assets[] = null;
        try {
            assets = assetMgr.list(srcPath);
            if (assets.length == 0) {
                copyFile(context, srcPath);
            } else {

                String destPath = context.getFilesDir().getAbsolutePath() + File.separator + srcPath;

                File dir = new File(destPath);
                if (!dir.exists())
                    dir.mkdir();

                for (String element : assets) {
                    Utils.copyAssetAll(context, srcPath + File.separator + element);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copyFile(Context context, String srcFile) {
        AssetManager assetMgr = context.getAssets();

        InputStream is = null;
        OutputStream os = null;
        try {

            File file = Utils.getModelFile(context, srcFile);
            if (file.exists())
                return;

            String destFile = file.getAbsolutePath();
            LOGGER.i("cp model >> %s", destFile);

            is = assetMgr.open(srcFile);
            os = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                os.write(buffer, 0, read);
            }
            is.close();
            os.flush();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File getModelFile(Context context, String fileName) {

        File cacheDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(context.getApplicationContext().getExternalCacheDir(), "/" + fileName);
        } else {
            cacheDir = new File(context.getCacheDir(), "/" + fileName);
        }
        return cacheDir;
    }


    public static File makeModelDir(Context context) {

        File cacheDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(context.getApplicationContext().getExternalCacheDir() + "/");

        } else {
            cacheDir = new File(context.getCacheDir(), "/");
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }

        return cacheDir;

    }


}
