package com.lol.lolpro.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Created by sergio on 28/05/14.
 */
public class GridAdapter extends BaseAdapter{

    private final Context context;
    private int finalDP;

    private String[][]datos;

    //Para campeones por lo menos
    public GridAdapter(Context context, String[][] data, int desiredDP) {
        this.context = context;
        datos = data;
        finalDP = desiredDP;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //SquaredImageView view = (SquaredImageView) convertView;
        ImageView view = (ImageView) convertView;

        if (view == null) {
            view = new ImageView(context);
            //view.setScaleType(CENTER_CROP);
        }

        view.setTag(getId (position));

        //Convert dp into px
        int px = (int)Utils.dipToPixels(context, finalDP);

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
        return datos[position][0];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refresh(){
        datos=new BBDDHelper(context).obtenerGratuitos();
        notifyDataSetChanged();
    }
}
