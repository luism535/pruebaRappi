package com.example.pruebarappi.Models;

import android.provider.BaseColumns;

/**
 * @Autor: Luis Jimenez
 * @Creado el: 13/01/2017
 * @Descripcion Modelo para guardar preferencias del usuario
 * @Version 1.0
 */
public class FavoritePostsUser {
    public class FavoritePostsUserEntity implements BaseColumns{
        public static final String TABLE_NAME = "TBL_Favorite_Post_User";
        public static final String COLUMN_NAME_POST_ID = "postId";
        public static final String COLUMN_NAME_USER_ID = "userId";
    }
}
