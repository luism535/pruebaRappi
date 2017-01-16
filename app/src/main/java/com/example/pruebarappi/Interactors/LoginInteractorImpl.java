package com.example.pruebarappi.Interactors;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.pruebarappi.AD.AccessToData;
import com.example.pruebarappi.Complements.ComplementMethodos;
import com.example.pruebarappi.Interfaces.Login.LoginInteractor;
import com.example.pruebarappi.Interfaces.Login.OnLoginFinishListener;
import com.example.pruebarappi.Managers.SessionManager;
import com.example.pruebarappi.Security.SecurityMethodos;

public class LoginInteractorImpl implements LoginInteractor {

    private static String TAG = LoginInteractorImpl.class.getSimpleName();
    private SecurityMethodos security = new SecurityMethodos();
    private SessionManager sessionManager;
    private AccessToData db;


    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Simulacion de proceso de login...
     * @Version 1.0
     */
    @Override
    public void validateCredentials(final String email, final String password, final OnLoginFinishListener listener, final Context context) {

        listener.validateCredentials(context);
        ComplementMethodos methodos = new ComplementMethodos();

        //Verificar si Email se encuentra Vacio
        if(!email.isEmpty()){

            //Verificar si Comtrasena se encuentra Vacio
            if(!password.isEmpty()){

                //Verificar si Email se cumple con el formato valido
                if(methodos.isEmailValid(email)){

                    //Simulacion de verificacion de credenciales contra servidor
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            db = new AccessToData(context);
                            Log.d(TAG, "Users on Database: " + db.getUsersNumber());

                            //Verificacion si combinacion de credenciales se encunetra en base de datos.
                            int userId = db.searchUserId(email, security.getSha512(password));

                            if (userId > 0) {
                                Log.d(TAG, "userID is: " + userId);
                                sessionManager = new SessionManager(context);
                                sessionManager.setLogin(true, "Administrador");
                                listener.onSuccess();
                            } else {
                                listener.onIncorrectCredentials();
                                Log.d(TAG, "Usuario no existe...");
                            }
                        }
                    }, 5000);

                }else{
                    listener.ValidEmail();
                }
            }else{
                listener.EmptyPassword();
            }
        }else{
            listener.EmptyEmail();
        }
    }
}
