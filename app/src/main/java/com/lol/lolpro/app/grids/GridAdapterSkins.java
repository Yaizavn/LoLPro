package com.lol.lolpro.app.grids;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.SkinDecorator;
import com.lol.lolpro.app.utillidades.Constants;
import com.squareup.picasso.Picasso;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterSkins extends BaseAdapter {

    private final Context context;
    private Champion campeon;

    /**
     * Constructor
     *
     * @param context   recibe el activity al que está asociado el fragment
     * @param campeon   Datos del campeon
     */
    public GridAdapterSkins(Context context, Champion campeon) {
        this.context = context;
        this.campeon = campeon;
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
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.cell_skins, parent, false);
        }
        convertView.setTag(getId(position));
        TextView nombre = (TextView) convertView.findViewById(R.id.skins_text);
        // Defino la nueva fuente cargandola desde el fichero .ttf
        Typeface miPropiaTypeFace = Typeface.createFromAsset(context.getAssets(), Constants.FONT_TURTLES);

        // Aplico el nuevo tipo de letra
        nombre.setTypeface(miPropiaTypeFace);
                // Get the image URL for the current position.
        String url = getItem(position);
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.cargar)
                .error(R.drawable.error)
                .into((ImageView) convertView.findViewById(R.id.skins_image));
        ((TextView) convertView.findViewById(R.id.skins_text)).setText(campeon.getSkins().get(position).getName());
        return convertView;
    }

    /**
     * Devuelve la longitud del array datos, es decir tantos campeones u objetos como haya en el array
     *
     * @return Número de campeones u objetos en datos
     */
    @Override
    public int getCount() {
        return campeon.getSkins().size();
    }

    /**
     * Devuelve la ruta de la imagen del array datos
     *
     * @param position posición del campeón u objeto para el que se devolverá la ruta
     * @return Ruta de la imagen del campeón u objeto
     */
    @Override
    public String getItem(int position) {
        return ((SkinDecorator)campeon.getSkins().get(position)).getRuta();
    }

    /**
     * Devuelve el identificador del campeón u objeto
     *
     * @param position posición del campeón u objeto para el que se devolverá el identificador
     * @return Identificados único del campeón u objeto
     */
    public String getId(int position) {
        return Integer.toString(position);
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
}
