package com.example.pruebarappi.AD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "PruebaRappi.db";
    private static final int DB_SCHEMA_VERSION = 1;

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @param context
     * @Descripcion Constructor DataBase...
     * @Version 1.0
     */
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEMA_VERSION);
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Creacion de base de datos...
     * @Version 1.0
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DATABASE", AccessToData.SQL_DEFAULT_USER);
        db.execSQL(AccessToData.SQL_CREATE_USERS);
        db.execSQL(AccessToData.SQL_CREATE_AUDITORIAS_LOGIN);
        db.execSQL(AccessToData.SQL_CREATE_POST);
        db.execSQL(AccessToData.SQL_CREATE_POST_X_USER);

        db.execSQL(AccessToData.SQL_DEFAULT_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}