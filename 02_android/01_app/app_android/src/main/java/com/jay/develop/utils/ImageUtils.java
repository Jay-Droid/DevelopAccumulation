package com.jay.develop.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.*;

public class ImageUtils {

    public static byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }

    // 二进制转字符串
    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String tmp = "";
        for (int i = 0; i < b.length; i++) {
            tmp = Integer.toHexString(b[i] & 0XFF);
            if (tmp.length() == 1) {
                sb.append("0" + tmp);
            } else {
                sb.append(tmp);
            }

        }
        return sb.toString();
    }

    //将bitmap转化为byte[]类型也就是转化为二进制
    public static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    //将二进制转化为bitmap
    public static void getBitmapFromByte(String json) {
//        //读写assets目录下的文件
//        InputStream is = getResources().getAssets().open("test.txt");
//        Reader in = new InputStreamReader(is);
//        BufferedReader bufferedReader = new BufferedReader(in);
//        String line = null;
//        while (null != (line = bufferedReader.readLine()) ){
//            System.out.println("assets file==========" + line);
//        }
//        bufferedReader.close();
//        in.close();
//        is.close();


    }

}
