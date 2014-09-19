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
import com.lol.lolpro.app.bbdd.DBManager;
import com.squareup.picasso.Picasso;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterSpells extends BaseAdapter {

    private final Context context;
    private String[][] data;
    private int idCampeon;

    /**
     * Constructor
     *
     * @param context recibe el activity al que está asociado el fragment
     * @param allData datos de los campeones o los objetos
     */
    public GridAdapterSpells(Context context, String[][] allData, int id) {
        this.context = context;
        data = allData;
        idCampeon = id;
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
            convertView = inflater.inflate(R.layout.cell_spells, parent, false);
        }
        if (data != null) {
            ((TextView) convertView.findViewById(R.id.nombre)).setText(data[position][0]);
            ((TextView) convertView.findViewById(R.id.descripcion)).setText(data[position][1] + "\n\n" + data[position][2]);
            if (Integer.parseInt(data[position][7]) == 0) {
                convertView.findViewById(R.id.alcance).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.coste).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.enfriamiento).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.textAlcance).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.textCoste).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.textEnfriamiento).setVisibility(View.VISIBLE);
                if (data[position][3].equals("self")) {
                    ((TextView) convertView.findViewById(R.id.alcance)).setText(context.getResources().getString(R.string.alcance_propio));
                } else {
                    ((TextView) convertView.findViewById(R.id.alcance)).setText(data[position][3]);
                }
                ((TextView) convertView.findViewById(R.id.coste)).setText(data[position][4]);
                ((TextView) convertView.findViewById(R.id.enfriamiento)).setText(data[position][5] + " " + context.getResources().getString(R.string.segundos));
            } else {
                convertView.findViewById(R.id.alcance).setVisibility(View.GONE);
                convertView.findViewById(R.id.coste).setVisibility(View.GONE);
                convertView.findViewById(R.id.enfriamiento).setVisibility(View.GONE);
                convertView.findViewById(R.id.textAlcance).setVisibility(View.GONE);
                convertView.findViewById(R.id.textCoste).setVisibility(View.GONE);
                convertView.findViewById(R.id.textEnfriamiento).setVisibility(View.GONE);
            }
            // Get the image URL for the current position.
            String url = getItem(position);
            // Trigger the download of the URL asynchronously into the image view.
            Picasso.with(context) //
                    .load(url) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .into((ImageView) convertView.findViewById(R.id.Imagen));
        }
        convertView.setTag(getId(position));
        return convertView;
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
        return data[position][6];
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
    public void refresh() {
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        data = dbMan.getDatabaseHelper().obtenerHabilidadesCampeon(idCampeon);
        dbMan.closeDatabase(false);
        notifyDataSetChanged();
    }
}
