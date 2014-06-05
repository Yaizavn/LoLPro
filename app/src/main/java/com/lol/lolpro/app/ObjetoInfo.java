package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ObjetoInfo extends Fragment {

    private BBDDHelper helper;
    public ObjetoInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((Principal)getActivity()).updateTitle(2);
        View view= inflater.inflate(R.layout.fragment_objeto_info, container, false);
        helper = new BBDDHelper(view.getContext());
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[] datos = helper.obtenerDatosObjetos(id);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getActivity().getResources().getDisplayMetrics());
        if (datos != null){
            //BBDDHelper helper = new BBDDHelper(getActivity());
            //datos = helper.obtenerDatosObjetos(id);
            String tienda = "Si";
            if (datos[4].contentEquals("0")){
                tienda = "No";
            }
            ((TextView) view.findViewById(R.id.nombre)).setText(datos[0]);
            ((TextView) view.findViewById(R.id.costeBase)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.coste)).setText(datos[2]);
            ((TextView) view.findViewById(R.id.descripcion)).setText(Utils.sanitizeItemDescription(datos[3]));
            ((TextView) view.findViewById(R.id.puedesComprar)).setText(tienda);
            Picasso.with(getActivity()) //
                    .load(datos[5]) //
                    .placeholder(R.drawable.abc_ab_bottom_solid_light_holo) //TODO imagen palceholder
                    .error(R.drawable.abc_ab_bottom_solid_dark_holo) //TODO imagen error
                    .resize(px,px)
                    .centerCrop() // Keep proportion
                    .into((ImageView) view.findViewById(R.id.Imagen));
        }
        // Inflate the layout for this fragment
        return view;
    }

}