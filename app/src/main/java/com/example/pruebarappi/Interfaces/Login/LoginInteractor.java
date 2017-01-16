package com.example.pruebarappi.Interfaces.Login;

import android.content.Context;

public interface LoginInteractor {
    void validateCredentials(String email, String password, OnLoginFinishListener listener, Context context);
}
