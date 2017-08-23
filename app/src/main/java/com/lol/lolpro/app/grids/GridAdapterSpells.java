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
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Passive;
import com.lol.lolpro.app.json.Campeones.Spell;
import com.squareup.picasso.Picasso;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterSpells extends BaseAdapter {

    private final Context context;
    private Champion campeon;

    /**
     * Constructor
     *
     * @param context recibe el activity al que está asociado el fragment
     * @param campeon datos del campeón
     */
    public GridAdapterSpells(Context context, Champion campeon) {
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
        Spell spell;
        Passive passive;
        String url;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.cell_spells, parent, false);
        }
        if (campeon != null) {
            if (position != 0) {
                spell = campeon.getSpells().get(position);
                ((TextView) convertView.findViewById(R.id.spells_nombre)).setText(spell.getName());
                ((TextView) convertView.findViewById(R.id.spells_descripcion)).setText(spell.getDescription() + "\n\n" + spell.getTooltip());
                convertView.findViewById(R.id.spells_imageCoste).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_imageAlcance).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_imageEnfriamiento).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_alcance).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_coste).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_enfriamiento).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_textAlcance).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_textCoste).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.spells_textEnfriamiento).setVisibility(View.VISIBLE);
                if (spell.getRangeBurn().equals("self")) {
                    ((TextView) convertView.findViewById(R.id.spells_alcance)).setText(context.getResources().getString(R.string.alcance_propio));
                } else {
                    ((TextView) convertView.findViewById(R.id.spells_alcance)).setText(spell.getRangeBurn());
                }
                ((TextView) convertView.findViewById(R.id.spells_coste)).setText(spell.getCostBurn());
                ((TextView) convertView.findViewById(R.id.spells_enfriamiento)).setText(spell.getCooldownBurn() + " " + context.getResources().getString(R.string.segundos));
                url = spell.getImage().getFull();
            } else {
                passive = campeon.getPassive();
                ((TextView) convertView.findViewById(R.id.spells_nombre)).setText(passive.getName());
                ((TextView) convertView.findViewById(R.id.spells_descripcion)).setText(passive.getDescription());
                convertView.findViewById(R.id.spells_imageCoste).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_imageAlcance).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_imageEnfriamiento).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_alcance).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_coste).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_enfriamiento).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_textAlcance).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_textCoste).setVisibility(View.GONE);
                convertView.findViewById(R.id.spells_textEnfriamiento).setVisibility(View.GONE);
                url = passive.getImage().getFull();
            }
            // Trigger the download of the URL asynchronously into the image view.
            Picasso.with(context) //
                    .load(url) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .into((ImageView) convertView.findViewById(R.id.spells_Imagen));
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
        return campeon.getSpells().size() + 1;
    }

    /**
     * Devuelve la ruta de la imagen del array datos
     *
     * @param position posición del campeón u objeto para el que se devolverá la ruta
     * @return Ruta de la imagen del campeón u objeto
     */
    @Override
    public String getItem(int position) {
        String url;
        if (position == 0){
            url = campeon.getPassive().getImage().getFull();
        }
        else {
            url = campeon.getSpells().get(position -1).getImage().getFull();
        }

        return url;
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
