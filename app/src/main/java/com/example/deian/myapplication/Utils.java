package com.example.deian.myapplication;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String GetStringFromFileAssets(AssetManager am, String file) {
        String rt = "";

        try {
            InputStream stream = am.open(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            rt = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return rt;
    }

}
