package com.lol.lolpro.app.inicio;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.web.SpiderNoticias;

/**
 * Adaptador para mostrar las noticias en InicioGlobal
 */
public class ListAdapterNoticias extends BaseAdapter {

    private final Context context;
    private String[][] news;

    /**
     * Constructor
     *
     * @param context recibe el activity al que está asociado el fragment
     */
    public ListAdapterNoticias(Context context) {
        this.context = context;
        news = new String[0][0];
    }

    /**
     * Crea un view con la con la noticia
     *
     * @param position    Posición de la noticia a crear
     * @param convertView Text en el que se insertará la noticia
     * @param parent      null
     * @return textView con la noticia creada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) convertView;
        if (news != null && news[position] != null) {
            if (view == null) {
                view = new TextView(context);
            }
            SpannableString text = new SpannableString(getTitle(position));
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            view.setText("■ " + text);
            view.setTextColor(Color.parseColor("#0B0080"));
            view.setTag(getItem(position));
        }
        return view;
    }

    /**
     * Obtiene el número de noticias
     *
     * @return Número de noticias
     */
    @Override
    public int getCount() {
        return news.length;
    }

    /**
     * Obtiene la url de la noticia que ocupa la posición pasada por parámetro
     *
     * @param position Posición de la noticia a obtener el url
     * @return URl de la noticia en posición
     */
    @Override
    public String getItem(int position) {
        // Devuelve la ruta de la noticia
        return news[position][Constants.NEWS_URL];
    }

    /**
     * Obtiene el título de la noticia que ocupa la posición pasada por parámetro
     *
     * @param position Posición de la noticia a obtener el título
     * @return Título de la noticia en posición
     */
    public String getTitle(int position) {
        return news[position][Constants.NEWS_TITLE];
    }

    /**
     * Obtiene la posición en la que será insertada la noticia
     *
     * @param position Posición de la noticia a obtener la posición
     * @return Posición de la noticia
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Se encarga de informar al adaptador de un cambio para que este pueda refrescar la interfaz
     */
    public void refresh() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                SpiderNoticias sp = new SpiderNoticias();
                news = sp.analyzeURLs();
                return null;
            }

            @Override
            public void onPostExecute(Void unused) {
                notifyDataSetChanged();
            }
        }.execute();
    }
}
