package com.example.pruebarappi.Interactors;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.pruebarappi.AD.AccessToData;
import com.example.pruebarappi.Complements.ComplementMethodos;
import com.example.pruebarappi.Interfaces.PostView.OnPostViewFinishListener;
import com.example.pruebarappi.Interfaces.PostView.PostInteractor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class PostInteractorImpl implements PostInteractor{

    private final static String TAG = PostInteractorImpl.class.getSimpleName();
    ComplementMethodos methodos = new ComplementMethodos();


    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Buscar informacion relacionada a un post
     * @Version 1.0
     */
    @Override
    public void searchPostInfo(Context context, String postId, OnPostViewFinishListener listener) {

        AccessToData accessToData = new AccessToData(context);
        String imgPost = null, postName = null, postDesc = null, postUrl = null, postHeaderStr = null, postBannerStr = null;
        int postDate = 0, postSubs = 0;

        //Buscar post en base de datos...
        Cursor cursor = accessToData.getIndividualPost(postId);

        //Verificar si el cursor posee data
        if(cursor != null){
            if(cursor.moveToFirst()) {
                postName = cursor.getString(0);
                postDate = cursor.getInt(1);
                postDesc = cursor.getString(2);
                postSubs = cursor.getInt(3);
                imgPost = cursor.getString(4);
                postUrl = cursor.getString(5);
                postHeaderStr = cursor.getString(6);
                postBannerStr = cursor.getString(7);
            }
        }
        cursor.close();

        //Vaciar data en sus respectivo elementos.
        String normalDateTime = methodos.convertToLocalTime(postDate);
        Bitmap bitmapH = methodos.StringToBitmap(postHeaderStr);
        Bitmap bitmapB = methodos.StringToBitmap(postBannerStr);
        listener.setDataToPost(bitmapH, postName, normalDateTime, postSubs, postDesc, postUrl, bitmapB);
    }

}
