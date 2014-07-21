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


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Skins extends Fragment {


    public Skins() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[][] datos = (String[][]) args.getSerializable("skins");
        View view = inflater.inflate(R.layout.fragment_skins, container, false);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getActivity().getResources().getDisplayMetrics());
        if (datos != null){
            //for (int i=0; i<datos.length; i++) {
                ((TextView) view.findViewById(R.id.texVida)).setText(datos[0][0]);
                ((TextView) view.findViewById(R.id.texVida2)).setText(datos[0][1]);
                ((TextView) view.findViewById(R.id.texVida3)).setText(datos[0][2]);
            datos[0][3]="http://wiki.inf.utfsm.cl/images/e/e8/Beastie.png";
            Picasso.with(getActivity()).setLoggingEnabled(true);
                Picasso.with(getActivity()) //
                        .load(datos[0][3]) //
                        .placeholder(R.drawable.cargar)
                        //.error(R.drawable.error)
                        .resize(px, px)
                        .centerCrop() // Keep proportion
                        .into((ImageView) view.findViewById(R.id.imageView200));

            ((TextView) view.findViewById(R.id.texVida4)).setText(datos[1][0]);
            ((TextView) view.findViewById(R.id.texVida5)).setText(datos[1][1]);
            ((TextView) view.findViewById(R.id.texVida6)).setText(datos[1][2]);
            Picasso.with(getActivity()) //
                    .load(datos[1][3]) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .resize(px, px)
                    .centerCrop() // Keep proportion
                    .into((ImageView) view.findViewById(R.id.imageView201));

            ((TextView) view.findViewById(R.id.texVida7)).setText(datos[2][0]);
            ((TextView) view.findViewById(R.id.texVida8)).setText(datos[2][1]);
            ((TextView) view.findViewById(R.id.texVida9)).setText(datos[2][2]);
            Picasso.with(getActivity()) //
                    .load(datos[2][3]) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .resize(px, px)
                    .centerCrop() // Keep proportion
                    .into((ImageView) view.findViewById(R.id.imageView202));


            //}
        }
        // Inflate the layout for this fragment
        return view;
    }
}
