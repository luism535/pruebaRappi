package com.example.pruebarappi.Views;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.pruebarappi.Adapters.PostAdapter;
import com.example.pruebarappi.Interfaces.Principal.PrincipalPresenter;
import com.example.pruebarappi.Interfaces.Principal.PrincipalView;
import com.example.pruebarappi.Presenters.PrincipalPresenterImpl;
import com.example.pruebarappi.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;

@EActivity(R.layout.activity_principal)
@OptionsMenu(R.menu.menu_main)
public class PrincipalActivity extends AppCompatActivity implements PrincipalView {

    private PrincipalPresenter presenter;

    @ViewById(R.id.content_List)
    ListView elementList;

    @ViewById(R.id.activity_principal)
    RelativeLayout layout;

    ListAdapter listAdapter;
    PostAdapter adapterL;

    private ProgressDialog dialog;

    @AfterViews
    protected void init(){
        presenter = new PrincipalPresenterImpl(this);
        presenter.downloadData(getApplicationContext());
    }

    @ItemClick(R.id.content_List)
    void itemClicked(int position){
        presenter.getPostId(adapterL, position);
    }

    @OptionsItem(R.id.action_logout)
    void logoutOnClick() {
        presenter.closeSession(getApplicationContext());
    }

    @OptionsItem(R.id.action_about_it)
    void aboutIt() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Acerca de...");

        alertDialogBuilder.
            setMessage("Desarrollador: Luis Jimenez").
            setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void showProgressDialog(Context context, String Message) {
        dialog = new ProgressDialog(PrincipalActivity.this);
        dialog.setMessage(Message);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void hideProgressDialog(String Message) {
        dialog.dismiss();
        Snackbar snackbar = Snackbar.make(layout, Message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setMessageProgressDialog(String Message) {
        dialog.setMessage(Message);
    }

    @Override
    public void generateAdapter(Context context, ArrayList<HashMap<String, String>> Data) {
        listAdapter = new SimpleAdapter(context, Data, R.layout.item_list, new String[]{"postName"},
                new int[]{R.id.displayName});

        elementList.setAdapter(listAdapter);
        dialog.dismiss();
    }

    @Override
    public void setListAdapter(PostAdapter adapter) {
        adapterL = adapter;
        elementList.setAdapter(adapterL);
        dialog.dismiss();
    }

    @Override
    public void navigateToPost(String postId) {
        Intent intent = new Intent(this, PostViewActivity_.class);
        intent.putExtra("id", postId);
        startActivity(intent);
    }

    @Override
    public void navigateToLogout() {
        Toast.makeText(this, "Cerrando sesión activa...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity_.class);
        startActivity(intent);
        finish();
    }
}
