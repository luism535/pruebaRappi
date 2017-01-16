package com.example.pruebarappi.Managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    //Share Preference
    SharedPreferences preferences;

    Editor editor;
    Context _context;

    // Share preference mode
    int PRIVATE_MODE = 0;

    // SHARE PREFERENCES FILE NAME
    private static final String PREF_NAME = "PruebaRappi";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String USERNAME = "username";

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Constructor de la clase
     * @Version 1.0
     */
    public SessionManager(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Configurar nueva sesion.
     * @Version 1.0
     */
    public void setLogin(boolean isLoggedIn, String username){
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(USERNAME, username);
        //commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Verificar si hay una sesion activa.
     * @Version 1.0
     */
    public  boolean isLoggedIn(){
        return preferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Obtener el nombre del usuario activo
     * Simulacion dura 3 segundos.
     * @Version 1.0
     */
    public String getUsername(){
        return preferences.getString(USERNAME, null);
    }
}
