package com.example.pruebarappi.Interfaces.PostView;

import android.graphics.Bitmap;

/**
 * Created by Luisj on 15/1/2017.
 */

public interface PostView {
    void setImgPost(Bitmap bitmap);
    void setImgBanner(Bitmap bitmap);
    void setPostName(String title);
    void setPostDate(String date);
    void setPostSubs(String Subs);
    void setPostDescription(String description);
    void setPostUrl(String postUrl);
}
