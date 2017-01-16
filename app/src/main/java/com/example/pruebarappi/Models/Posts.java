package com.example.pruebarappi.Models;

import android.provider.BaseColumns;


public class Posts {

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Modelo para el procesamiento de los post
     * @Version 1.0
     */
    public final class postEntity implements BaseColumns{
        public static final String TABLE_NAME = "TBL_POSTS";

        public static final String COLUMN_NAME_POST_ID = "postId";
        public static final String COLUMN_NAME_DISPLAY_NAME = "displayName";
        public static final String COLUMN_NAME_CREATED_AT = "createdAt";
        public static final String COLUMN_NAME_PUBLIC_DESCRIPTION = "publicDescription";

        public static final String COLUMN_NAME_SUBSCRIBERS = "subscribers";

        public static final String COLUMN_NAME_BANNER_IMG_URL = "bannerImg";
        public static final String COLUMN_NAME_HEADER_IMG_URL = "headerImg";
        public static final String COLUMN_NAME_POST_URL = "postUrl";

        public static final String COLUMN_NAME_BANNER_IMG_STR = "bannerImgStr";
        public static final String COLUMN_NAME_HEADER_IMG_STR = "headerImgStr";

    }
}
