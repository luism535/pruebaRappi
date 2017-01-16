package com.example.pruebarappi.Complements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luisj on 15/1/2017.
 */

public class ComplementMethodos {

    private static final String TAG = ComplementMethodos.class.getSimpleName();

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Convertir fecha en formato Unix Time a formato dd/mm/aaaa, ejemplo: 16/01/2016
     * @Version 1.0
     */
    public String convertToLocalTime(int utcTime){
        try{
            Date date = new Date(utcTime*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = sdf.format(date);
            return formattedDate;

        }catch (Exception ex){
            Log.e(TAG, "Error to convert dateTime: " + ex.getMessage());
            return null;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Converit bitman en base64 String
     * @Version 1.0
     */
    public String BitmapToStirng(Bitmap bitmap){

        return Base64.encodeToString(getBytesFromBitmap(bitmap), Base64.NO_WRAP);
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion convertir base64 de una imagen en un bitmap
     * @Version 1.0
     */
    public Bitmap StringToBitmap(String image){
        try {
            byte[] encodeByte = Base64.decode(image, Base64.NO_WRAP);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Obtener un arreglo de bit de un bitmap
     * @Version 1.0
     */
    private static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Verificar si un string cumple con el formato valido
     * @Version 1.0
     */
    public static boolean isEmailValid(String email){
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }

}
