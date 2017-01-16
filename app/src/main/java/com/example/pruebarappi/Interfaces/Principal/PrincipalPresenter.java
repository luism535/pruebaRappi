package com.example.pruebarappi.Interfaces.Principal;

import android.content.Context;
import android.widget.ListAdapter;

import com.example.pruebarappi.Adapters.PostAdapter;

/**
 * Created by Luisj on 14/1/2017.
 */

public interface PrincipalPresenter {
    void downloadData(Context context);
    void getPostId(PostAdapter adapter, int position);
    void closeSession(Context context);
}
