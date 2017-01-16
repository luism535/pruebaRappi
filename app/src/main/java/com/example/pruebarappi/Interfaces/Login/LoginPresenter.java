package com.example.pruebarappi.Interfaces.Login;

import android.content.Context;

public interface LoginPresenter {
    void validateCredentials(String email, String password, Context context);
}
