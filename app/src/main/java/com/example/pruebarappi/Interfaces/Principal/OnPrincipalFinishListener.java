package com.example.pruebarappi.Interfaces.Principal;

import android.content.Context;

import com.example.pruebarappi.Adapters.PostAdapter;
import com.example.pruebarappi.Models.PostToList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface OnPrincipalFinishListener {
    void onSuccess(String Message);
    void onProgressUpdate(String Message);
    void onNetworkError(String Message);
    void onBeginProcess(Context context, String Message);
    void onLoadNoticePost(Context context, ArrayList<HashMap<String, String>> Data);
    void navigateToPost(String postId);
    void navigateToLogout();
    void odLoadPostList(Context context, PostAdapter adapter);
}
