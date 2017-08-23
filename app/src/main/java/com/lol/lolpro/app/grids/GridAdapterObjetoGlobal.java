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
import com.lol.lolpro.app.json.Objetos.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Adaptador para mostrar los objetos y campeones en un gridview usando Picasso
 */
public class GridAdapterObjetoGlobal extends BaseAdapter {

	private final Context context;
	private List<Item> lItem;

	/**
	 * Constructor
	 * @param context   recibe el activity al que está asociado el fragment
	 */
	public GridAdapterObjetoGlobal(Context context) {

		this(context, null);

	}

	/**
	 * Constructor
	 * @param context   recibe el activity al que está asociado el fragment
	 * @param lItemAux  Listado de items a mostrar
	 */
	public GridAdapterObjetoGlobal(Context context, List<Item> lItemAux) {

		DBManager dbMan;

		this.context = context;

		if (lItemAux == null) {

			dbMan = DBManager.getInstance();
			dbMan.openDatabase(false);

			lItemAux = dbMan.getDatabaseHelper().obtenerObjetos();

			dbMan.closeDatabase(false);

		}

		this.lItem = new ArrayList<>(lItemAux);

	}

	/**
	 * Crea un view con la imagen del campeón u objeto que se encuentra en una posición determinada para añadirlo al grid
	 *
	 * @param position	Posición de la imagen a crear
	 * @param convertView ImageView en el que se insertará la imagen
	 * @param parent	  null
	 * @return Imageview con la imagen creada
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.cell_image_name_object, parent, false);
		}
		item = getItem(position);
		convertView.setTag(item);
		// Trigger the download of the URL asynchronously into the image view.
		Picasso.with(context) //
				.load(item.getImage().getFull()) //
				.fit()
				.placeholder(R.drawable.cargar)
				.error(R.drawable.error)
				.into((ImageView) convertView.findViewById(R.id.name_object_image));
		((TextView) convertView.findViewById(R.id.name_object_text)).setText(item.getName());
		return convertView;
	}

	/**
	 * Devuelve la longitud del array datos, es decir tantos campeones u objetos como haya en el array
	 *
	 * @return Número de campeones u objetos en datos
	 */
	@Override
	public int getCount() {
		return lItem.size();
	}

	/**
	 * Devuelve la ruta de la imagen del array datos
	 *
	 * @param position posición del campeón u objeto para el que se devolverá la ruta
	 * @return Ruta de la imagen del campeón u objeto
	 */
	@Override
	public Item getItem(int position) {
		return lItem.get(position);
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
