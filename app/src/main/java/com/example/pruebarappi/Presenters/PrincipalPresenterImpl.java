package com.example.pruebarappi.Presenters;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;

import com.example.pruebarappi.Adapters.PostAdapter;
import com.example.pruebarappi.Interactors.PrincipalInteractotImpl;
import com.example.pruebarappi.Interfaces.Principal.OnPrincipalFinishListener;
import com.example.pruebarappi.Interfaces.Principal.PrincipalInteractor;
import com.example.pruebarappi.Interfaces.Principal.PrincipalPresenter;
import com.example.pruebarappi.Interfaces.Principal.PrincipalView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luisj on 14/1/2017.
 */

public class PrincipalPresenterImpl implements PrincipalPresenter, OnPrincipalFinishListener {

    private PrincipalView view;
    private PrincipalInteractor interactor;

    public PrincipalPresenterImpl(PrincipalView view) {
        this.view = view;
        interactor = new PrincipalInteractotImpl();
    }

    @Override
    public void downloadData(Context context) {
        interactor.downloadData(context, this);
    }

    @Override
    public void getPostId(PostAdapter adapter, int position) {
        interactor.getPostId(adapter, position);
    }

    @Override
    public void closeSession(Context context) {
        interactor.closeSession(context);
    }

    @Override
    public void onSuccess(String Message) {
        if(view != null){
            view.hideProgressDialog(Message);
        }
    }

    @Override
    public void onProgressUpdate(String Message) {
        if(view != null) {
            view.setMessageProgressDialog(Message);
        }
    }

    @Override
    public void onNetworkError(String Message) {
        if(view != null) {
            view.setMessageProgressDialog(Message);
        }
    }

    @Override
    public void onBeginProcess(Context context, String Message) {
        if(view != null){
            view.showProgressDialog(context, Message);
        }
    }

    @Override
    public void onLoadNoticePost(Context context, ArrayList<HashMap<String, String>> Data) {
        if(view != null){
            view.generateAdapter(context, Data);
        }
    }


    @Override
    public void navigateToPost(String postId) {
        if(view != null){
            view.navigateToPost(postId);
        }
    }

    @Override
    public void navigateToLogout() {
        if(view != null){
            view.navigateToLogout();
        }
    }

    @Override
    public void odLoadPostList(Context context, PostAdapter adapter) {
        if(view != null){
            view.setListAdapter(adapter);
        }
    }
}
