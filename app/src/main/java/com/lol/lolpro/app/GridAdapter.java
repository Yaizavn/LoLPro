package com.lol.lolpro.app;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sergio on 28/05/14.
 */
public class GridAdapter extends BaseAdapter{

    private final Context context;

    private String[][]datos;

    //Para campeones por lo menos
    public GridAdapter(Context context, String[][] resultado) {
        this.context = context;
        datos = resultado;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //SquaredImageView view = (SquaredImageView) convertView;
        ImageView view = (ImageView) convertView;
        //ERROR AQUI
        view.setTag(getId (position));

        if (view == null) {
            view = new ImageView(context);
            //view.setScaleType(CENTER_CROP);
        }

        //Convert dp into px
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, this.context.getResources().getDisplayMetrics());

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.abc_ab_bottom_solid_light_holo) //TODO imagen palceholder
                .error(R.drawable.abc_ab_bottom_solid_dark_holo) //TODO imagen error
                .resize(px, px)
                .centerCrop() // Keep proportion
                .into(view);

        return view;
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public String getItem(int position) {
        // TODO caonstante
        // Devuelve la ruta de la imagen
        return datos[position][2];
    }

    public String getId(int position) {
        // TODO caonstante
        // Devuelve la ruta de la imagen
        return datos[position][0];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
