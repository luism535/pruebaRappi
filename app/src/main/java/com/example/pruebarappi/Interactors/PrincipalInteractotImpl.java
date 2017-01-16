package com.example.pruebarappi.Interactors;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.test.espresso.core.deps.guava.base.Splitter;
import android.util.Log;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;
import com.example.pruebarappi.AD.AccessToData;
import com.example.pruebarappi.Adapters.PostAdapter;
import com.example.pruebarappi.Complements.ComplementMethodos;
import com.example.pruebarappi.Comunication.HttpHandler;
import com.example.pruebarappi.Interfaces.Principal.OnPrincipalFinishListener;
import com.example.pruebarappi.Interfaces.Principal.PrincipalInteractor;
import com.example.pruebarappi.Managers.SessionManager;
import com.example.pruebarappi.Models.PostToList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Luisj on 14/1/2017.
 */

public class PrincipalInteractotImpl implements PrincipalInteractor{

    private final static String TAG = PrincipalInteractotImpl.class.getSimpleName();
    private final static String URL = "https://www.reddit.com/reddits.json";
    AccessToData accessToData;
    OnPrincipalFinishListener listenerClass;
    Context contextClass;
    ArrayList<HashMap<String, String>> postList;
    ComplementMethodos methodos = new ComplementMethodos();

    List<PostToList> postsList = new ArrayList<>();
    PostAdapter adapter;

    int checkNewPost = 0;

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 14/01/2017
     * @Descripcion Buscar Post en base de datos
     * @Version 1.0
     */
    @Override
    public void downloadData(Context context, OnPrincipalFinishListener listener) {
        accessToData = new AccessToData(context);
        listenerClass = listener;
        contextClass = context;

        //Verificar si el numero de post es mayor a 0, en caso contrario descargar deintener
        if(accessToData.postNumbers() > 0){
            listenerClass.onBeginProcess(contextClass, "Cargando Noticias...");
            buscarDataParaCargar();
            adapter = new PostAdapter(context, postsList);
            listener.odLoadPostList(context, adapter);
        }else{
            new GetData().execute();
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 14/01/2017
     * @Descripcion Obtener el id de un post al hacer click en el list view
     * @Version 1.0
     */
    @Override
    public void getPostId(PostAdapter PostAdapter, int position) {
        //Obetenr el id mendiate busqueda en el adpatador
        PostToList item = (PostToList) PostAdapter.getItem(position);
        Log.d(TAG, "Prueba");
        listenerClass.navigateToPost(item.getPostId());
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 16/01/2017
     * @Descripcion Cerrar la sesión activa
     * @Version 1.0
     */
    @Override
    public void closeSession(Context context) {
        //Cambiar estatus de la sesion en el manager.
        SessionManager manager = new SessionManager(context);
        manager.setLogin(false, null);
        listenerClass.navigateToLogout();
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Llamar a base de datos para obtner los registros.
     * @Version 1.0
     */
    public void buscarDataParaCargar(){
        Cursor cursor = accessToData.getAllPost();
        PostToList item;

        if(cursor != null){
            //buscar los post en base de datos.
            int numberOfPost = cursor.getCount();
            Log.d(TAG, "Number de post on Cursor: " + numberOfPost);
            postList = new ArrayList<>();

            if(cursor.moveToFirst()) {

                for (int i = 0; i < numberOfPost; i++) {
                    String postId = cursor.getString(0);
                    String postName = cursor.getString(1);
                    int postDateTime = cursor.getInt(2);
                    String localDateTime = methodos.convertToLocalTime(postDateTime);

                    //Creae nuevo item de tipo post
                    item = new PostToList(postId, postName, localDateTime);
                    //agregar nevo post a la lista general
                    postsList.add(item);

                    cursor.moveToNext();
                }
            }
        }
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Descargas los post de intenet en backgroung...
     * Primero se guardan las noticias en base de datos y luego son llamadas
     * Esto se realiza para poder mantener un mayor orden al llenar el listView
     * @Version 1.0
     */
    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            postList = new ArrayList<>();
            listenerClass.onBeginProcess(contextClass, "Descargando Información...");
            Log.d(TAG, "La lectura ha comnezado...");
            Log.d(TAG, " ");
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler handler = new HttpHandler();
            String jsonData = handler.makeServiceCall(URL);

            //Obetener data de la pagina solicitada: https://www.reddit.com/reddits.json
            if(jsonData != null){
                Log.d(TAG, "Response is not null, lenght of response: " + jsonData.length());

                try{
                    //crear json con la data obtenida
                    JSONObject jsonObject = new JSONObject(jsonData);
                    //crear json con info contenida en el objeto data
                    JSONObject jsonObjectData = new JSONObject(jsonObject.getString("data"));
                    //crear json array con los post obtenidos
                    JSONArray jsonArrayPost = jsonObjectData.getJSONArray("children");

                    //Procesar array con noticias
                    for(int i = 1; i < jsonArrayPost.length(); i++){

                        JSONObject post = jsonArrayPost.getJSONObject(i);
                        JSONObject postData = post.getJSONObject("data");

                        String postId = postData.getString("id");
                        String postName = postData.getString("display_name");
                        int postCreateAt = postData.getInt("created_utc");
                        String publicDescription = postData.getString("public_description");
                        int subs = postData.getInt("subscribers");
                        String banner = postData.getString("banner_img");
                        String header = postData.getString("header_img");
                        String postUrl = postData.getString("url");

                        //Convertit Primera letra en Mayuscula
                        postName = postName.substring(0,1).toUpperCase() + postName.substring(1);
                        String postHeaderStr = null;
                        String postBannerStr = null;

                        Bitmap bitmapH = null, bitmapB = null;

                        //Descargar imagen usuando la libreria Glide.
                        //Posterior a la descargar convertir bitmap obtenido en String.
                        if(header != null || !header.isEmpty()){
                            try {
                                bitmapH = Glide
                                        .with(contextClass)
                                        .load(header)
                                        .asBitmap()
                                        .into(-1, -1)
                                        .get();
                                postHeaderStr = methodos.BitmapToStirng(bitmapH);
                            } catch (InterruptedException | ExecutionException e) {
                                postHeaderStr = null;
                                e.printStackTrace();
                            }
                        }

                        Log.d(TAG, "Post number: " + i + " with id: " + postId);
                        Log.d(TAG, "Post name: " + postName);
                        Log.d(TAG, "Post created At: " + postCreateAt);
                        Log.d(TAG, " ");

                        //Verificar si ya existe un post con el id actual
                        if(!accessToData.isPostExist(postId)){
                            if(accessToData.insertPost(postId, postName, postCreateAt, publicDescription, subs, banner, header, postUrl, postBannerStr, postHeaderStr)){
                                Log.d(TAG, "Post Ha sido insertado existosamente");
                                checkNewPost++;
                            }
                        }else{
                            Log.d(TAG, "Post con id: " + postId + ", ya se encuentra registrado");
                        }
                    }

                    //cargar posts de base de datos...
                    buscarDataParaCargar();

                }catch (JSONException ex){
                    Log.e(TAG, "Ha ocurrido un error al procesar el Json: " + ex.getMessage());
                }

            }else{
                Log.e(TAG, "Response is null, Check this url: " + URL);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, " ");
            Log.d(TAG, "La lectura ha finalizado...");

            //Crear adaptador con data obtenida
            adapter = new PostAdapter(contextClass, postsList);
            listenerClass.odLoadPostList(contextClass, adapter);

            String message = null;
            if(checkNewPost > 0){
                message = "Se han descargado " + checkNewPost + " noticias. Disfrutalas!!!";
            }else{
                message = "La aplicación se encuentra actualizada";
            }
            listenerClass.onSuccess(message);
        }
    }
}
