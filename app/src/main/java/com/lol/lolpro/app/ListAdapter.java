package com.lol.lolpro.app;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    private final Context context;
    private String[][] news;

    //Para campeones por lo menos
    public ListAdapter(Context context) {
        this.context = context;
        news = new String[0][0];
    }

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

    @Override
    public int getCount() {
        return news.length;
    }

    @Override
    public String getItem(int position) {
        // Devuelve la ruta de la noticia
        return news[position][Constants.NEWS_URL];
    }

    public String getTitle(int position) {
        return news[position][Constants.NEWS_TITLE];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refresh() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Spider sp = new Spider();
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
