package com.example.pruebarappi.Interfaces.Principal;

import android.content.Context;
import android.widget.ListAdapter;

import com.example.pruebarappi.Adapters.PostAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Luisj on 14/1/2017.
 */

public interface PrincipalView {
    void showProgressDialog(Context context, String Message);
    void hideProgressDialog(String Message);
    void setMessageProgressDialog(String Message);
    void generateAdapter(Context context, ArrayList<HashMap<String, String>> Data);
    void setListAdapter(PostAdapter adapter);
    void navigateToPost(String postId);
    void navigateToLogout();
}
