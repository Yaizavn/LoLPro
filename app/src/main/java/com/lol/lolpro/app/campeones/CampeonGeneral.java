package com.lol.lolpro.app.campeones;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.squareup.picasso.Picasso;

/**
 * Implementa la funcionalidad del fragment
 */
public class CampeonGeneral extends Fragment {

	/**
	 * Constructor vacío
	 */
	public CampeonGeneral() {
		// Required empty public constructor
	}

	/**
	 * Se encarga del tratamiento necesario para poder crear la vista de información general de un campeón en particular
	 *
	 * @param inflater		   Sirve para traer un layout hecho en xml como una vista en java
	 * @param container		  Contenedos para otros elementos View
	 * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
	 * @return Vista de los campeones
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view;
		Bundle args;
		Champion campeon;

		args = getArguments();
		campeon = args.getParcelable("champion");
		view = inflater.inflate(R.layout.fragment_campeon_general, container, false);

		if (campeon != null) {

			((TextView) view.findViewById(R.id.campeon_nombre)).setText(campeon.getName());
			((TextView) view.findViewById(R.id.campeon_nick)).setText(campeon.getTitle());
			((TextView) view.findViewById(R.id.campeon_vida)).setText(campeon.getStats().getHp() + "(+" + campeon.getStats().getHpperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_regVida)).setText(campeon.getStats().getHpregen() + "(+" + campeon.getStats().getHpregenperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_danioAtaque)).setText(campeon.getStats().getAttackdamage() + "(+" + campeon.getStats().getAttackdamageperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_armadura)).setText(campeon.getStats().getArmor() + "(+" + campeon.getStats().getArmorperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_velAtaque)).setText(campeon.getStats().getAttackspeedoffset() + "(+" + campeon.getStats().getAttackspeedperlevel() + "% por nivel)");

			((TextView) view.findViewById(R.id.campeon_texMana)).setText(campeon.getPartype());
			((TextView) view.findViewById(R.id.campeon_texRegMana)).setText(campeon.getPartype());

//			String mp=datos[16];
//			String [] mpName = getResources().getStringArray((R.array.tipoFuerza));
//			boolean encontrado=false;
//			for (int i=0; i<mpName.length && !encontrado; i++){
//				if (mp.equals(mpName[i]) || mp.contains(mpName[i])){
//					((TextView) view.findViewById(R.id.campeon_texMana)).setText(getActivity().getResources().getStringArray(mp)[i]);
//					((TextView) view.findViewById(R.id.campeon_texRegMana)).setText(getActivity().getResources().getStringArray(R.array.regMP)[i]);
//					encontrado = true;
//				}
//			}
//			if (!encontrado){
//				view.findViewById(R.id.campeon_texMana).setVisibility(View.GONE);
//				view.findViewById(R.id.campeon_mana).setVisibility(View.GONE);
//				view.findViewById(R.id.campeon_imageMana).setVisibility(View.GONE);
//				view.findViewById(R.id.campeon_texRegMana).setVisibility(View.GONE);
//				view.findViewById(R.id.campeon_regMana).setVisibility(View.GONE);
//				view.findViewById(R.id.campeon_imageRegMana).setVisibility(View.GONE);
//			}
			((TextView) view.findViewById(R.id.campeon_mana)).setText(campeon.getStats().getMp() + "(+" + campeon.getStats().getMpperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_regMana)).setText(campeon.getStats().getMpregen() + "(+" + campeon.getStats().getMpregenperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_resMagica)).setText(campeon.getStats().getSpellblock() + "(+" + campeon.getStats().getSpellblockperlevel() + " por nivel)");
			((TextView) view.findViewById(R.id.campeon_velMov)).setText(String.format("%1$,.2f", campeon.getStats().getMovespeed()));
			Picasso.with(getActivity()) //
					.load(campeon.getImage().getFull()) //
					.placeholder(R.drawable.cargar)
					.error(R.drawable.error)
					.into((ImageView) view.findViewById(R.id.campeon_Imagen));
		}
		return view;
	}
}