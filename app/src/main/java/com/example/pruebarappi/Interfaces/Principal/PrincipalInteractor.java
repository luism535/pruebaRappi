package com.example.pruebarappi.Interfaces.Principal;

import android.content.Context;
import android.widget.ListAdapter;

import com.example.pruebarappi.Adapters.PostAdapter;
import com.example.pruebarappi.Interfaces.Login.OnLoginFinishListener;


public interface PrincipalInteractor {
    void downloadData(Context context, OnPrincipalFinishListener listener);
    void getPostId(PostAdapter adapter, int position);
    void closeSession(Context context);
}
