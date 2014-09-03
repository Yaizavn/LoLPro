package com.lol.lolpro.app.grids;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.utillidades.Utils;
import com.squareup.picasso.Picasso;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterObjetoGlobal extends BaseAdapter {

    private final Context context;
    private int finalDP;
    private String[][] data;

    /**
     * Constructor
     *
     * @param context   recibe el activity al que está asociado el fragment
     * @param allData      datos de los campeones o los objetos
     * @param desiredDP Dp que tendrán las imágenes
     */
    public GridAdapterObjetoGlobal(Context context, String[][] allData, int desiredDP) {
        this.context = context;
        data = allData;
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

        View view = convertView;
        //Convert dp into px
        int px = (int) Utils.dipToPixels(context, finalDP);

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.cell_image_name_object, parent, false);
        }

        view.setTag(getId(position));

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.cargar)
                .error(R.drawable.error)
                .into((ImageView) view.findViewById(R.id.item_image));
        ((TextView) view.findViewById(R.id.item_text)).setText(data[position][1]);
        return view;
    }

    /**
     * Devuelve la longitud del array datos, es decir tantos campeones u objetos como haya en el array
     *
     * @return Número de campeones u objetos en datos
     */
    @Override
    public int getCount() {
        return data.length;
    }

    /**
     * Devuelve la ruta de la imagen del array datos
     *
     * @param position posición del campeón u objeto para el que se devolverá la ruta
     * @return Ruta de la imagen del campeón u objeto
     */
    @Override
    public String getItem(int position) {
        return data[position][2];
    }

    /**
     * Devuelve el identificador del campeón u objeto
     *
     * @param position posición del campeón u objeto para el que se devolverá el identificador
     * @return Identificados único del campeón u objeto
     */
    public String getId(int position) {
        return data[position][0];
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
    public void refresh(){
        /*TODO
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        data = dbMan.getDatabaseHelper().obtenerGratuitos();
        dbMan.closeDatabase(false);*/
        notifyDataSetChanged();
    }
}
