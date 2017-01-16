package com.example.pruebarappi.Presenters;

import android.content.Context;

import com.example.pruebarappi.Interactors.LoginInteractorImpl;
import com.example.pruebarappi.Interfaces.Login.LoginInteractor;
import com.example.pruebarappi.Interfaces.Login.LoginPresenter;
import com.example.pruebarappi.Interfaces.Login.LoginView;
import com.example.pruebarappi.Interfaces.Login.OnLoginFinishListener;

import java.util.Collection;


public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishListener {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion validar si las credenciales ingresadas son validas
     * @Version 1.0
     */
    @Override
    public void validateCredentials(String email, String password, Context context) {
        interactor.validateCredentials(email, password, this, context);
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion validar email
     * @Version 1.0
     */
    @Override
    public void ValidEmail() {
        if(view != null){
            view.setValidEmail();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion verificar que el email no se encuentre vacio
     * @Version 1.0
     */
    @Override
    public void EmptyEmail() {
        if(view != null){
            view.setEmptyEmail();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion verificar que el passowrd no se encuentre vacio
     * @Version 1.0
     */
    @Override
    public void EmptyPassword() {
        if(view != null){
            view.setEmptyPassword();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion lanzar actividad principal
     * @Version 1.0
     */
    @Override
    public void onSuccess() {
        if(view != null){
            view.onSuccessLogin();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion notificar al usuario que la combinacion de email / clave es invalida
     * @Version 1.0
     */
    @Override
    public void onIncorrectCredentials() {
        if (view != null){
            view.incorrectCredentials();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 14/01/2017
     * @Descripcion validar que las credenciales ingresadas son validas.
     * @Version 1.0
     */
    @Override
    public void validateCredentials(Context context) {
        if (view != null){
            view.showProgressLogin(context);
        }
    }
}
