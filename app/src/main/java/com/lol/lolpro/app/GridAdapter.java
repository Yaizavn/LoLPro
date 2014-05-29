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

    static final String BASE = "http://i.imgur.com/";
    static final String EXT = ".jpg";
    static final String[] URLS = {
            BASE + "CqmBjo5" + EXT, BASE + "zkaAooq" + EXT, BASE + "0gqnEaY" + EXT,
            BASE + "9gbQ7YR" + EXT, BASE + "aFhEEby" + EXT, BASE + "0E2tgV7" + EXT,
            BASE + "P5JLfjk" + EXT, BASE + "nz67a4F" + EXT, BASE + "dFH34N5" + EXT,
            BASE + "FI49ftb" + EXT, BASE + "DvpvklR" + EXT, BASE + "DNKnbG8" + EXT,
            BASE + "yAdbrLp" + EXT, BASE + "55w5Km7" + EXT, BASE + "NIwNTMR" + EXT,
            BASE + "DAl0KB8" + EXT, BASE + "xZLIYFV" + EXT, BASE + "HvTyeh3" + EXT,
            BASE + "Ig9oHCM" + EXT, BASE + "7GUv9qa" + EXT, BASE + "i5vXmXp" + EXT,
            BASE + "glyvuXg" + EXT, BASE + "u6JF6JZ" + EXT, BASE + "ExwR7ap" + EXT,
            BASE + "Q54zMKT" + EXT, BASE + "9t6hLbm" + EXT, BASE + "F8n3Ic6" + EXT,
            BASE + "P5ZRSvT" + EXT, BASE + "jbemFzr" + EXT, BASE + "8B7haIK" + EXT,
            BASE + "aSeTYQr" + EXT, BASE + "OKvWoTh" + EXT, BASE + "zD3gT4Z" + EXT,
            BASE + "z77CaIt" + EXT,
    };
    private final Context context;
    private final List<String> urls = new ArrayList<String>();

    private String[][]datos;

    //Para campeones por lo menos
    public GridAdapter(Context context, String[][] resultado) {
        this.context = context;

        datos = resultado;


        // Ensure we get a different ordering of images on each run.
        Collections.addAll(urls, URLS);
        Collections.shuffle(urls);

        // Triple up the list.
        ArrayList<String> copy = new ArrayList<String>(urls);
        urls.addAll(copy);
        urls.addAll(copy);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //SquaredImageView view = (SquaredImageView) convertView;
        ImageView view = (ImageView) convertView;

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

    @Override
    public long getItemId(int position) {
        return position;
    }
}
