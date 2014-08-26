package com.lol.lolpro.app.objetos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lol.lolpro.app.R;


public class ObjetoArbol extends Fragment {

    public ObjetoArbol() {
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
        View view = inflater.inflate(R.layout.fragment_objeto_arbol, container, false);
            /*Bundle args = getArguments();
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
            // Inflate the layout for this fragment*/
        return view;
    }
}
