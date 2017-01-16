package com.example.pruebarappi.Security;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityMethodos {

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion obtener sha512 de alguna data.
     * @Version 1.0
     */
    public String getSha512(String data){
        String result = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA512");
            byte[] textBytes = data.getBytes("UTF-8");
            digest.update(textBytes, 0, textBytes.length);
            byte[] hash = digest.digest();
            result =  byteArrayToHexString(hash).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Convertir bytes[] en string con formato hex
     * @Version 1.0
     */
    private static String byteArrayToHexString(byte[] array) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : array) {
            int intVal = b & 0xff;
            if (intVal < 0x10)
                hexString.append("0");
            hexString.append(Integer.toHexString(intVal));
        }
        return hexString.toString();
    }
}
