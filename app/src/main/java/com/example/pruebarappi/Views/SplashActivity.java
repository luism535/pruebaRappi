package com.example.pruebarappi.Views;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pruebarappi.Interfaces.Principal.PrincipalPresenter;
import com.example.pruebarappi.Interfaces.Splash.SplashPresenter;
import com.example.pruebarappi.Interfaces.Splash.SplashView;
import com.example.pruebarappi.Presenters.SplashPresenterImpl;
import com.example.pruebarappi.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@WindowFeature(Window.FEATURE_NO_TITLE)
@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity implements SplashView {

    private SplashPresenter presenter;

    @ViewById(R.id.txtMessage)
    TextView txtMessages;

    @ViewById(R.id.pbPrincipal)
    ProgressBar pbLoader;

    @ViewById(R.id.imgPrincipal)
    ImageView imgPrincipal;

    @AfterViews
    void init(){
        Picasso.with(getApplicationContext()).load(R.drawable.iconlist).into(imgPrincipal);
        presenter = new SplashPresenterImpl(this);
        presenter.checkPrerequisites(getApplicationContext());
    }

    @Override
    public void showProgress() {
       pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    @Override
    public void setMessage(String message) {
        txtMessages.setText(message);
    }

    @Override
    public void navigateToMain() {
        startActivity(new Intent(SplashActivity.this, PrincipalActivity_.class));
        finish();
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity_.class));
        finish();
    }
}
