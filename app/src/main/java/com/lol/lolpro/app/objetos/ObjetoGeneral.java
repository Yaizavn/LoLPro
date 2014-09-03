package com.lol.lolpro.app.objetos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.grids.GridAdapterObjetoGlobal;
import com.lol.lolpro.app.R;
import com.squareup.picasso.Picasso;


/**
 * Implementa la funcionalidad del fragment
 */
public class ObjetoGeneral extends Fragment {

    OnHeadlineSelectedListener mCallback = null;


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
        String[] datos = args.getStringArray("data");
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getActivity().getResources().getDisplayMetrics());
        if (datos != null) {
            String tienda = view.getResources().getString(R.string.si);
            if (datos[4].contentEquals("0")) {
                tienda = view.getResources().getString(R.string.no);
            }
            ((TextView) view.findViewById(R.id.nombre)).setText(datos[0]);
            ((TextView) view.findViewById(R.id.costeBase)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.coste)).setText(datos[2]);
            ((TextView) view.findViewById(R.id.precioVenta)).setText(datos[3]);
            if (datos[6].compareTo("")!=0) {
                ((TextView) view.findViewById(R.id.descripcion)).setText(datos[6] + "\n\n" + datos[5]);
            }
            else{
                ((TextView) view.findViewById(R.id.descripcion)).setText(datos[5]);
            }
            ((TextView) view.findViewById(R.id.puedesComprar)).setText(tienda);
            Picasso.with(getActivity()) //
                    .load(datos[13]) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .resize(px, px)
                    .centerCrop() // Keep proportion
                    .into((ImageView) view.findViewById(R.id.Imagen));
        }
        // Inflate the layout for this fragment
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
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       GridView grid = (GridView) view.findViewById(R.id.gridViewCampeonAdmitido);
        Bundle args = getArguments();
        String[][] datos = (String[][]) args.getSerializable("campeonReq");
        if (datos!=null) {
            view.findViewById(R.id.TextoCampeonesPermitidos).setVisibility(View.VISIBLE);
            view.findViewById(R.id.gridViewCampeonAdmitido).setVisibility(View.VISIBLE);
            grid.setAdapter(new GridAdapterObjetoGlobal(getActivity(), datos, 100));

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    //Send the event to the host activity
                    mCallback.onChampionSelected(Integer.parseInt(v.getTag().toString()));
                }
            });
        }
        else{
            view.findViewById(R.id.TextoCampeonesPermitidos).setVisibility(View.GONE);
            view.findViewById(R.id.gridViewCampeonAdmitido).setVisibility(View.GONE);
        }
    }

    public interface OnHeadlineSelectedListener {
        /**
         * Método definido en principal que se encarga de el tratamiento al seleccionar a un campeón
         *
         * @param index Posición del campeón seleccionado
         */
        public void onChampionSelected(int index);
    }

}