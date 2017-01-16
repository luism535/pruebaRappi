package com.example.pruebarappi.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebarappi.Interfaces.Login.LoginPresenter;
import com.example.pruebarappi.Interfaces.Login.LoginView;
import com.example.pruebarappi.Presenters.LoginPresenterImpl;
import com.example.pruebarappi.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements LoginView{

    private LoginPresenter presenter;
    private ProgressDialog dialog;

    @ViewById(R.id.tilUsername)
    TextInputLayout tilEmail;

    @ViewById(R.id.tilPassword)
    TextInputLayout tilPassword;

    @ViewById(R.id.edUsername)
    EditText edEmail;

    @ViewById(R.id.edPassword)
    EditText edPassword;

    @ViewById(R.id.activity_login)
    RelativeLayout relativeLayout;

    @Click(R.id.floatingButton)
    void clickHelp(){
        Snackbar snackbar = Snackbar.make(relativeLayout, "La contraseña es:  admin", Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorSnack));
        snackbar.show();
    }

    @AfterViews
    protected void init(){
        edEmail.setText("admin@rappi.com");
        presenter = new LoginPresenterImpl(this);
    }

    @Click(R.id.btnLogin)
    void loginClicked(){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);
        presenter.validateCredentials(edEmail.getText().toString(), edPassword.getText().toString(), getApplicationContext());

        //limpiar los errores de los editText
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                tilEmail.setError(null);
                tilPassword.setError(null);
            }
        };
        edEmail.addTextChangedListener(textWatcher);
        edPassword.addTextChangedListener(textWatcher);
    }

    @Override
    public void setEmptyEmail() {
        dialog.dismiss();
        tilEmail.setEnabled(true);
        tilEmail.setError("El campo Email no debe estar vacio.");
    }

    @Override
    public void setEmptyPassword() {
        dialog.dismiss();
        tilPassword.setEnabled(true);
        tilPassword.setError("Elcampo de password no debe estar vacio.");
    }

    @Override
    public void setValidEmail() {
        dialog.dismiss();
        tilEmail.setEnabled(true);
        tilEmail.setError("Ingrese un email con formato valido.");
    }

    @Override
    public void onSuccessLogin() {
        startActivity(new Intent(LoginActivity.this, PrincipalActivity_.class));
        finish();
    }

    @Override
    public void incorrectCredentials() {
        dialog.dismiss();

        Snackbar snackbar = Snackbar.make(relativeLayout, "Email o Contraseña incorrectos...", Snackbar.LENGTH_LONG);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));

        snackbar.show();
    }

    @Override
    public void showProgressLogin(Context context) {
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Validando credenciales...");
        dialog.setCancelable(false);
        dialog.show();
    }
}