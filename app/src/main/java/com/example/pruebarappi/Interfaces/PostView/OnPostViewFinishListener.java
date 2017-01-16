package com.example.pruebarappi.Interfaces.PostView;

import android.graphics.Bitmap;

/**
 * Created by Luisj on 15/1/2017.
 */

public interface OnPostViewFinishListener {
    void setDataToPost(Bitmap imgPost, String postName, String postDate, int postSubs, String postDesc, String postUrl, Bitmap bannerPost);
}
