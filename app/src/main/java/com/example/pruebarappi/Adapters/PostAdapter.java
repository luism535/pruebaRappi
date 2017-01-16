package com.example.pruebarappi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebarappi.Models.PostToList;
import com.example.pruebarappi.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends BaseAdapter {

    Context context;
    List<PostToList> postItems;
    ViewHolder holder;

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Clase constructora
     * @Version 1.0
     */
    public PostAdapter(Context context, List<PostToList> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int position) {
        return postItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postItems.indexOf(getItem(position));
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Holder Constructor
     * @Version 1.0
     */
    static class ViewHolder{
        TextView txtPostName;
        TextView txtPostDate;
        ImageView imgPostImage;
    }

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 15/01/2017
     * @Descripcion Graficar elementos en el list view
     * @Version 1.0
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = null;

        //Verificar si el convert view es nulo
        if(convertView == null){

            //Generar new Holder
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_post_item, parent, false);
            holder = new ViewHolder();

            holder.txtPostName = (TextView) convertView.findViewById(R.id.txtPostTitle);
            holder.txtPostDate = (TextView) convertView.findViewById(R.id.txtDateTimeList);
            holder.imgPostImage = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);

        }else {
            //Obtener el tag asociado al viewHolder
            holder = (ViewHolder) convertView.getTag();
        }

        //Vaciar info obtenida en sus respectivos contenedores
        PostToList row_pos = postItems.get(position);
        holder.txtPostName.setText(row_pos.getPostName());
        holder.txtPostDate.setText(row_pos.getPostDateTime());
        Picasso.with(context).load(R.drawable.iconlist).into(holder.imgPostImage);

        return convertView;
    }
}
