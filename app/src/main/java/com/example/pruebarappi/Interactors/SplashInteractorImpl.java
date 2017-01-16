package com.example.pruebarappi.Interactors;

import android.content.Context;
import android.os.Handler;

import com.example.pruebarappi.Interfaces.Splash.OnSplashFinishListener;
import com.example.pruebarappi.Interfaces.Splash.SplashInteractor;
import com.example.pruebarappi.Managers.SessionManager;

public class SplashInteractorImpl implements SplashInteractor {

    private SessionManager manager;

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Simulacion de verificacion de requitos del dispositivo
     * Simulacion dura 3 segundos.
     * @Version 1.0
     */
    @Override
    public void validatePrerequesites(final Context context, final OnSplashFinishListener listener) {

        manager = new SessionManager(context);

        if(manager.isLoggedIn()){
            listener.onSuccess();
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onLogin();
                }
            }, 3000);
        }


    }
}
