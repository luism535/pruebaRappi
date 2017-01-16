package com.example.pruebarappi.Interfaces.Splash;

/**
 * Created by Luisj on 13/1/2017.
 */

public interface SplashView {
    void showProgress();
    void hideProgress();
    void setMessage(String message);
    void navigateToMain();
    void navigateToLogin();
}
