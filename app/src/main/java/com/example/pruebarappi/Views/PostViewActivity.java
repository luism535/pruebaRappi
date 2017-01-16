package com.example.pruebarappi.Views;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebarappi.Interfaces.PostView.PostPresenter;
import com.example.pruebarappi.Interfaces.PostView.PostView;
import com.example.pruebarappi.Presenters.PostPresenterImpl;
import com.example.pruebarappi.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_post_view)
public class PostViewActivity extends AppCompatActivity implements PostView{

    private PostPresenter presenter;

    @ViewById(R.id.imgPost)
    ImageView postImage;

    @ViewById(R.id.imgBanner)
    ImageView postBanner;

    @ViewById(R.id.txtPostName)
    TextView postNameTxt;

    @ViewById(R.id.txtDate)
    TextView postDateTxt;

    @ViewById(R.id.txtSubs)
    TextView postSubsTxt;

    @ViewById(R.id.txtUrl)
    TextView postUrlTxt;

    @ViewById(R.id.txtDescription)
    TextView postDescriptionTxt;




    @Extra("id")
    String PostId;

    @AfterViews
    protected void init(){
        presenter = new PostPresenterImpl(this);
        presenter.loadPostData(getApplicationContext(), PostId);
    }

    @Override
    public void setImgPost(Bitmap bitmap) {
        postImage.setImageBitmap(bitmap);
    }

    @Override
    public void setImgBanner(Bitmap bitmap) {
        postBanner.setImageBitmap(bitmap);
    }

    @Override
    public void setPostName(String title) {
        postNameTxt.setText(title);
    }

    @Override
    public void setPostDate(String date) {
        postDateTxt.setText(date);
    }

    @Override
    public void setPostSubs(String Subs) {
        postSubsTxt.setText(Subs);
    }

    @Override
    public void setPostDescription(String description) {
        postDescriptionTxt.setText(description);
    }

    @Override
    public void setPostUrl(String postUrl) {
        postUrlTxt.setText(postUrl);
    }
}
