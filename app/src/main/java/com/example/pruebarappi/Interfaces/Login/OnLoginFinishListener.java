package com.example.pruebarappi.Interfaces.Login;

import android.content.Context;

public interface OnLoginFinishListener {
    void ValidEmail();
    void EmptyEmail();
    void EmptyPassword();
    void onSuccess();
    void onIncorrectCredentials();
    void validateCredentials(Context context);
}
