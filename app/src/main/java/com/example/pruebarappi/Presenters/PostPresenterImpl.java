package com.example.pruebarappi.Presenters;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.pruebarappi.Interactors.PostInteractorImpl;
import com.example.pruebarappi.Interfaces.PostView.OnPostViewFinishListener;
import com.example.pruebarappi.Interfaces.PostView.PostInteractor;
import com.example.pruebarappi.Interfaces.PostView.PostPresenter;
import com.example.pruebarappi.Interfaces.PostView.PostView;

public class PostPresenterImpl implements PostPresenter, OnPostViewFinishListener{

    private PostView view;
    private PostInteractor interactor;

    public PostPresenterImpl(PostView view) {
        this.view = view;
        interactor = new PostInteractorImpl();
    }

    @Override
    public void loadPostData(Context context, String postId) {
        interactor.searchPostInfo(context, postId, this);
    }

    @Override
    public void setDataToPost(Bitmap imgPost, String postName, String postDate, int postSubs, String postDesc, String postUrl, Bitmap bannerPost) {
        if(view != null){
            view.setImgPost(imgPost);
            view.setPostDate(postDate);
            view.setPostDescription(postDesc);
            view.setPostSubs("Subscriptores: " + postSubs);
            view.setPostName(postName);
            view.setPostUrl(postUrl);
            view.setImgBanner(bannerPost);
            view.setPostUrl("http://reddit.com"+postUrl);
        }
    }
}
