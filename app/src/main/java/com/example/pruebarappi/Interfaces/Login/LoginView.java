package com.example.pruebarappi.Interfaces.Login;

import android.content.Context;
import android.view.View;

/**
 * Created by Luisj on 13/1/2017.
 */

public interface LoginView {
    void setEmptyEmail();
    void setEmptyPassword();
    void setValidEmail();
    void onSuccessLogin();
    void incorrectCredentials();
    void showProgressLogin(Context context);
}
