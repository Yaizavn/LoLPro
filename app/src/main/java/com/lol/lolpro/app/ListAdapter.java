package com.lol.lolpro.app;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private String[][]datos;

    //Para campeones por lo menos
    public ListAdapter(Context context) {
        this.context = context;
        datos = new String[0][0];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) convertView;
        if (datos!=null&&datos[position]!=null) {
            if (view == null) {
                view = new TextView(context);
            }
            SpannableString text = new SpannableString(getTitulo(position));
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            view.setText("■ "+text);
            view.setTextColor(Color.parseColor("#0B0080"));
            view.setTag(getItem(position));
        }
        return view;
    }

    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public String getItem(int position) {
        // TODO caonstante
        // Devuelve la ruta de la noticia
        return datos[position][0];
    }

    public String getTitulo(int position) {
        // TODO caonstante
        return datos[position][1];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refresh(){
        //datos=noticias.clone();
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Spider sp = new Spider ();
                datos = sp.analizarURLs();
                return null;
            }
            @Override
            public void onPostExecute(Void unused) {
                notifyDataSetChanged();
                return;
            }
        }.execute();
    }
}
