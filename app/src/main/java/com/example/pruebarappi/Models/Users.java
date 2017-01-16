package com.example.pruebarappi.Models;

import android.provider.BaseColumns;

public class Users {

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Modelo para el proceso de los usuarios
     * @Version 1.0
     */
    public final class usersEntity implements BaseColumns{
        public static final String TABLE_NAME = "TBL_USERS";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_IS_LOGGED_IN = "isLoggedIn";
    }
}
