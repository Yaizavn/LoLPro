package com.lol.lolpro.app.grids;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.ChampionDecorator;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterFreeChamps extends BaseAdapter {

    private final Context context;
    private List<ChampionDecorator> data;

    /**
     * Constructor
     *
     * @param context   recibe el activity al que está asociado el fragment
     * @param allData      datos de los campeones o los objetos
     */
    public GridAdapterFreeChamps(Context context, List<ChampionDecorator> allData) {
        this.context = context;
        data = allData;
    }

    public List<ChampionDecorator> getData(){
        return data;
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
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.cell_image, parent, false);
        }
        convertView.setTag(getId(position));
        // Get the image URL for the current position.
        Champion campeon = getItem(position);
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(campeon.getImage().getFull()) //
                .fit()
                .placeholder(R.drawable.cargar)
                .error(R.drawable.error)
                .into((ImageView) convertView.findViewById(R.id.item_image));
        return convertView;
    }

    /**
     * Devuelve la longitud del array datos, es decir tantos campeones u objetos como haya en el array
     *
     * @return Número de campeones u objetos en datos
     */
    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }
        else return 0;
    }

    /**
     * Devuelve la ruta de la imagen del array datos
     *
     * @param position posición del campeón u objeto para el que se devolverá la ruta
     * @return Ruta de la imagen del campeón u objeto
     */
    @Override
    public Champion getItem(int position) {
        return data.get(position);
    }

    /**
     * Devuelve el identificador del campeón u objeto
     *
     * @param position posición del campeón u objeto para el que se devolverá el identificador
     * @return Identificados único del campeón u objeto
     */
    public String getId(int position) {
        return data.get(position).getId().toString();
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
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        data = dbMan.getDatabaseHelper().obtenerCampeonesGratuitos();
        dbMan.closeDatabase(false);
        notifyDataSetChanged();
    }
}
