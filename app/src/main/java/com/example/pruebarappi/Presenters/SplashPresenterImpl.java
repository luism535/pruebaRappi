package com.example.pruebarappi.Presenters;

import android.content.Context;

import com.example.pruebarappi.Interactors.SplashInteractorImpl;
import com.example.pruebarappi.Interfaces.Splash.OnSplashFinishListener;
import com.example.pruebarappi.Interfaces.Splash.SplashInteractor;
import com.example.pruebarappi.Interfaces.Splash.SplashPresenter;
import com.example.pruebarappi.Interfaces.Splash.SplashView;

/**
 * Created by Luisj on 13/1/2017.
 */

public class SplashPresenterImpl implements SplashPresenter, OnSplashFinishListener {

    private SplashView view;
    private SplashInteractor interactor;

    public SplashPresenterImpl(SplashView view) {
        this.view = view;
        interactor = new SplashInteractorImpl();
    }

    @Override
    public void checkPrerequisites(Context context) {
        interactor.validatePrerequesites(context, this);
    }

    @Override
    public void networkError() {
        if (view != null) {
            view.setMessage("Aplicación requiere conexión a internet.");
        }
    }

    @Override
    public void otherError() {
        if (view != null) {
            view.setMessage("Ha ocurrido un error desconocido.");
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.navigateToMain();
        }
    }

    @Override
    public void onLogin() {
        if (view != null) {
            view.navigateToLogin();
        }
    }
}
