package com.lol.lolpro.app.objetos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.grids.GridAdapterCampeonGlobal;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.ChampionDecorator;
import com.lol.lolpro.app.json.Objetos.Item;
import com.lol.lolpro.app.utillidades.Champion_callback;
import com.lol.lolpro.app.utillidades.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Implementa la funcionalidad del fragment
 */
public class ObjetoGeneral extends Fragment {

    Champion_callback mCallback = null;

    /**
     * Constructor vacío
     */
    public ObjetoGeneral() {
        // Required empty public constructor
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista de información general de un objeto en particular
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de los objetos
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_objeto_general, container, false);
        Bundle args = getArguments();
        Item item = args.getParcelable("item");
        if (item != null) {
            String tienda = view.getResources().getString(R.string.si);
            if (!item.getGold().getPurchasable()) {
                tienda = view.getResources().getString(R.string.no);
            }
            ((TextView) view.findViewById(R.id.objeto_nombre)).setText(item.getName());
            ((TextView) view.findViewById(R.id.objeto_costeBase)).setText(item.getGold().getBase().toString());
            ((TextView) view.findViewById(R.id.objeto_coste)).setText(item.getGold().getTotal().toString());
            ((TextView) view.findViewById(R.id.objeto_precioVenta)).setText(item.getGold().getSell().toString());
            ((TextView) view.findViewById(R.id.objeto_descripcion)).setText(Utils.sanitizeText(item.getDescription()));
            ((TextView) view.findViewById(R.id.objeto_puedesComprar)).setText(tienda);
            Picasso.with(getActivity()) //
                    .load(item.getImage().getFull()) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .into((ImageView) view.findViewById(R.id.objeto_Imagen));
        }
        return view;
    }


    /**
     * Método al que se llamará una vez el fragment ha sido asociado a un activity
     *
     * @param activity Activity al que está asociado un fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //This makes sure that the container activity has implemented the callback interface.
        //If not, it throws an exception
        try {
            mCallback = (Champion_callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement Champion_callback");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       GridView grid = (GridView) view.findViewById(R.id.objeto_gridViewCampeonAdmitido);
        Bundle args = getArguments();
        List<ChampionDecorator> lChampionReq = args.getParcelableArrayList("campeonReq");
        if (lChampionReq!=null) {
            view.findViewById(R.id.objeto_TextoCampeonesPermitidos).setVisibility(View.VISIBLE);
            view.findViewById(R.id.objeto_gridViewCampeonAdmitido).setVisibility(View.VISIBLE);
            grid.setAdapter(new GridAdapterCampeonGlobal(getActivity(), lChampionReq));

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    //Send the event to the host activity
                    mCallback.onChampionSelected((Champion) parent.getItemAtPosition(new Long(id).intValue()));
                }
            });
        }
        else{
            view.findViewById(R.id.objeto_TextoCampeonesPermitidos).setVisibility(View.GONE);
            view.findViewById(R.id.objeto_gridViewCampeonAdmitido).setVisibility(View.GONE);
        }
    }
}