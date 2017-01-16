package com.example.pruebarappi.Interfaces.PostView;

import android.content.Context;

/**
 * Created by Luisj on 15/1/2017.
 */

public interface PostInteractor {
    void searchPostInfo(Context context, String postId, OnPostViewFinishListener listener);
}
