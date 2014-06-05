package com.lol.lolpro.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapter extends BaseAdapter {

    private final Context context;
    private int finalDP;

    private String[][] datos;

    /**
     * Constructor
     *
     * @param context   recibe el activity al que está asociado el fragment
     * @param data      datos de los campeones o los objetos
     * @param desiredDP Dp que tendrán las imágenes
     */
    public GridAdapter(Context context, String[][] data, int desiredDP) {
        this.context = context;
        datos = data;
        finalDP = desiredDP;
    }

    /**
     * Crea un view con la imagen del campeón u objeto que se encuentra en una posición determinada para añadirlo al grid
     *
     * @param position    Posición de la imagen a crear
     * @param convertView ImageView en el que se insertará la imagen
     * @param parent      null
     * @return Imageview con la imagen creada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //SquaredImageView view = (SquaredImageView) convertView;
        ImageView view = (ImageView) convertView;

        if (view == null) {
            view = new ImageView(context);
            //view.setScaleType(CENTER_CROP);
        }

        view.setTag(getId(position));

        //Convert dp into px
        int px = (int) Utils.dipToPixels(context, finalDP);

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.abc_ab_bottom_solid_light_holo) //TODO imagen palceholder
                .error(R.drawable.abc_ab_bottom_solid_dark_holo) //TODO imagen error
                .resize(px, px)
                .centerCrop()// Keep proportion
                .into(view);

        return view;
    }

    /**
     * Devuelve la longitud del array datos, es decir tantos campeones u objetos como haya en el array
     *
     * @return Número de campeones u objetos en datos
     */
    @Override
    public int getCount() {
        return datos.length;
    }

    /**
     * Devuelve la ruta de la imagen del array datos
     *
     * @param position posición del campeón u objeto para el que se devolverá la ruta
     * @return Ruta de la imagen del campeón u objeto
     */
    @Override
    public String getItem(int position) {
        return datos[position][2];
    }

    /**
     * Devuelve el identificador del campeón u objeto
     *
     * @param position posición del campeón u objeto para el que se devolverá el identificador
     * @return Identificados único del campeón u objeto
     */
    public String getId(int position) {
        return datos[position][0];
    }

    /**
     * Calcula posición del grid en la que se insertará el campeón u objeto
     *
     * @param position posición del campeón u objeto para el que se devolverá el identificador
     * @return posición del grid en la que se insertará el campeón u objeto
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Se encarga de notificar que ha habido cambios y debe recargarse el grid con los nuevos datos.
     */
    public void refresh() {
        datos = new BBDDHelper(context).obtenerGratuitos();
        notifyDataSetChanged();
    }
}
