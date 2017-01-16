package com.example.pruebarappi.AD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pruebarappi.Models.AuditoriaLogin;
import com.example.pruebarappi.Models.FavoritePostsUser;
import com.example.pruebarappi.Models.Posts;
import com.example.pruebarappi.Models.Users;

public class AccessToData {

    private static final String TAG = AccessToData.class.getSimpleName();

    private DataBaseHelper helper;
    private SQLiteDatabase db;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY";
    private static final String COMMA_SEP = " , ";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Clase constructora
     * @Version 1.0
     */
    public AccessToData(Context context) {
        //Initialize DataBase...
        helper = new DataBaseHelper(context);

        //Open database on Writable mode...
        db = helper.getWritableDatabase();
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para registrar usuarios
     * @Version 1.0
     */
    public static final String SQL_CREATE_USERS = "CREATE TABLE " + Users.usersEntity.TABLE_NAME
            + " ( " +  Users.usersEntity._ID + PRIMARY_KEY + COMMA_SEP
            +  Users.usersEntity.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP
            +  Users.usersEntity.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP
            +  Users.usersEntity.COLUMN_NAME_IS_LOGGED_IN + INT_TYPE + COMMA_SEP
            +  Users.usersEntity.COLUMN_NAME_EMAIL + TEXT_TYPE + " );";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para crear auditorias
     * @Version 1.0
     */
    public static final String SQL_CREATE_AUDITORIAS_LOGIN = "CREATE TABLE " + AuditoriaLogin.auditoriaLoginEntity.TABLE_NAME
            + " ( " +  AuditoriaLogin.auditoriaLoginEntity._ID + PRIMARY_KEY + COMMA_SEP
            +  AuditoriaLogin.auditoriaLoginEntity.COLUMN_NAME_USER_ID + INT_TYPE + COMMA_SEP
            +  AuditoriaLogin.auditoriaLoginEntity.COLUMN_NAME_DATE_LOGIN + TEXT_TYPE + " );";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para registrar los post-articulos-temas
     * @Version 1.0
     */
    public static final String SQL_CREATE_POST = "CREATE TABLE " + Posts.postEntity.TABLE_NAME
                    + " ( " +  Posts.postEntity._ID + PRIMARY_KEY + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_POST_ID + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_DISPLAY_NAME + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_CREATED_AT + INT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_PUBLIC_DESCRIPTION + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_SUBSCRIBERS + INT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_BANNER_IMG_URL + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_HEADER_IMG_URL + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_BANNER_IMG_STR + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_HEADER_IMG_STR + TEXT_TYPE + COMMA_SEP
                    +  Posts.postEntity.COLUMN_NAME_POST_URL + TEXT_TYPE + " );";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para registrar los articulos favoritos de un usuario
     * @Version 1.0
     */
    public static final String SQL_CREATE_POST_X_USER = "CREATE TABLE "
            + FavoritePostsUser.FavoritePostsUserEntity.TABLE_NAME
            + " ( " +  FavoritePostsUser.FavoritePostsUserEntity._ID + PRIMARY_KEY + COMMA_SEP
            +  FavoritePostsUser.FavoritePostsUserEntity.COLUMN_NAME_USER_ID + INT_TYPE + COMMA_SEP
            +  FavoritePostsUser.FavoritePostsUserEntity.COLUMN_NAME_POST_ID + TEXT_TYPE + " );";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para registrar usuario por defecto - Administrador
     * @Version 1.0
     */
    public static final String SQL_DEFAULT_USER = "INSERT INTO " + Users.usersEntity.TABLE_NAME
            + "( " + Users.usersEntity.COLUMN_NAME_USERNAME + COMMA_SEP
            + Users.usersEntity.COLUMN_NAME_EMAIL + COMMA_SEP
            + Users.usersEntity.COLUMN_NAME_PASSWORD
            + ") VALUES ('admin', 'admin@rappi.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec');";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para crear un nuevo usuario
     * @Version 1.0
     */
    public boolean insertToUser(String mUsername, String mPassword, String mEmail, int mLoggedIn){
        try{
            ContentValues values = new ContentValues();
            values.put(Users.usersEntity.COLUMN_NAME_USERNAME, mUsername);
            values.put(Users.usersEntity.COLUMN_NAME_PASSWORD, mPassword);
            values.put(Users.usersEntity.COLUMN_NAME_EMAIL, mEmail);
            values.put(Users.usersEntity.COLUMN_NAME_IS_LOGGED_IN, mLoggedIn);

            long newRegister = db.insert(Users.usersEntity.TABLE_NAME, null, values);
            Log.i(TAG, "New user register has been created with id: " + newRegister);

            return true;

        }catch (Exception ex){
            Log.e(TAG, "Error while new storage register: " + ex.getMessage());
            return false;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para obtener el total de usuario registrados
     * @Version 1.0
     */
    public int getUsersNumber(){
        String sql = "SELECT * FROM " + Users.usersEntity.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        int result = cursor.getCount();
        cursor.close();
        return result;
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para obtener información de un usuario especifico.
     * @Version 1.0
     */
    public int searchUserId(String email, String password){

        int userId = 0;
        String SQL = "SELECT _ID FROM "
                + Users.usersEntity.TABLE_NAME + " WHERE "
                + Users.usersEntity.COLUMN_NAME_EMAIL + " = '" + email
                + "' and " + Users.usersEntity.COLUMN_NAME_PASSWORD + " = '" + password + "';";

        Log.d(TAG, "Sql to search user: " + SQL);

       Cursor cursor = db.rawQuery(SQL, null);

        if (cursor.moveToFirst()){
            userId = cursor.getInt(0);
        }
        cursor.close();

        return userId;
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para insertar un nuevo post.
     * @Version 1.0
     */
    public boolean insertPost(String postId, String displayName, int createdAt, String publicDescription, int subscribers, String bannerImg, String headerImg, String postUrl,String bannerImgStr, String headerImgStr){

        try{
            ContentValues values = new ContentValues();
            values.put(Posts.postEntity.COLUMN_NAME_POST_ID, postId);
            values.put(Posts.postEntity.COLUMN_NAME_DISPLAY_NAME, displayName);
            values.put(Posts.postEntity.COLUMN_NAME_CREATED_AT, createdAt);
            values.put(Posts.postEntity.COLUMN_NAME_PUBLIC_DESCRIPTION, publicDescription);
            values.put(Posts.postEntity.COLUMN_NAME_SUBSCRIBERS, subscribers);
            values.put(Posts.postEntity.COLUMN_NAME_BANNER_IMG_URL, bannerImg);
            values.put(Posts.postEntity.COLUMN_NAME_HEADER_IMG_URL, headerImg);
            values.put(Posts.postEntity.COLUMN_NAME_BANNER_IMG_STR, bannerImgStr);
            values.put(Posts.postEntity.COLUMN_NAME_HEADER_IMG_STR, headerImgStr);
            values.put(Posts.postEntity.COLUMN_NAME_POST_URL, postUrl);

            if(db.insert(Posts.postEntity.TABLE_NAME, null, values) > 0){
                Log.d(TAG, "Post ha sido insertado satisfactoriamente...");
                return true;
            }else{
                Log.e(TAG, "Ha ocurrido un error al insertar el post, Error desconocido");
                return false;
            }

        }catch (Exception ex){
            Log.e(TAG, "Ha ocurrido un error al insertar el post, Error: " + ex.getMessage());
            return false;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para verificar si existe un post registrado con un id especifico
     * @Version 1.0
     */
    public boolean isPostExist(String postId){

        try{
            if(DatabaseUtils.queryNumEntries(db, Posts.postEntity.TABLE_NAME,
                    Posts.postEntity.COLUMN_NAME_POST_ID + "=?",
                    new String[]{postId}) > 0){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            Log.e(TAG, "Ha ocurrido un error: " + ex.getMessage());
            return false;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para obtener el total de post registrados
     * @Version 1.0
     */
    public int postNumbers(){
        try{
            long result = DatabaseUtils.queryNumEntries(db, Posts.postEntity.TABLE_NAME, null);
            Log.d(TAG, "Se han encontrado: " + result);
            return (int) result;
        }catch (Exception ex){
            Log.e(TAG, "Ha ocurrido un error al contabilizar los post: " + ex.getMessage());
            return 0;
        }
    }


    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para obtener todos los post registrados
     * @Version 1.0
     */
    public Cursor getAllPost(){
        try{
            String[] dataColumns = new String[]{Posts.postEntity.COLUMN_NAME_POST_ID, Posts.postEntity.COLUMN_NAME_DISPLAY_NAME, Posts.postEntity.COLUMN_NAME_CREATED_AT};
            String order = Posts.postEntity.COLUMN_NAME_DISPLAY_NAME + " ASC";

            return db.query(Posts.postEntity.TABLE_NAME, dataColumns,null,null,null,null,order);
        }catch (Exception ex){
            Log.e(TAG, "Ha ocurrido un error: " + ex.getMessage());
            return null;
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Script para obtener un post especifico.
     * @Version 1.0
     */
    public Cursor getIndividualPost(String postId){

        try{
            String selection = Posts.postEntity.COLUMN_NAME_POST_ID + " = ?";
            String[] selectionArgs = { postId };


            String[] dataColumns = new String[]{
                    Posts.postEntity.COLUMN_NAME_DISPLAY_NAME,
                    Posts.postEntity.COLUMN_NAME_CREATED_AT,
                    Posts.postEntity.COLUMN_NAME_PUBLIC_DESCRIPTION,
                    Posts.postEntity.COLUMN_NAME_SUBSCRIBERS,
                    Posts.postEntity.COLUMN_NAME_HEADER_IMG_URL,
                    Posts.postEntity.COLUMN_NAME_POST_URL,
                    Posts.postEntity.COLUMN_NAME_HEADER_IMG_STR,
                    Posts.postEntity.COLUMN_NAME_BANNER_IMG_STR,
            };
            return db.query(Posts.postEntity.TABLE_NAME, dataColumns,selection,selectionArgs,null,null,null);

        }catch (Exception ex){
            Log.e(TAG, "Ha ocurrido un error al buscar el Post con id: " + postId);
            return null;
        }
    }
}
