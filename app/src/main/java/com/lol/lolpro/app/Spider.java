package com.lol.lolpro.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
public class Spider{
    private String urlInicial;
    public static final String urlNoticias= "http://euw.leagueoflegends.com/es/news/";
    private final ArrayList<String> urlsError = new ArrayList<String>();

    public Spider() {
        urlInicial=urlNoticias;
    }


    public  String [][] analizarURLs() {
        Document doc;
        try {
            doc = Jsoup.connect(urlInicial).get();
        } catch (Exception ex) {
            if (ex instanceof SocketTimeoutException) {
                //  Este tipo de error indica que ha habido un problema al
                //  cargar la pagina pero puede ser valida por lo que no la
                //  etiquetamos como erronea permitiendo futuros intentos.
                return null;
            }
            //  Añadimos la URL a erroneas si es MalformedURLException
            //	HttpStatusException o UnsupportedMimeTypeException
            //  evitando futuros intentos
            urlsError.add(urlInicial);
            return null;
        }
        return linksNoticias(doc);
    }

    private  String [][] linksNoticias(Document doc) {
        Elements links = doc.select("h4 > a[href]");
        String[][] noticias = new String[links.size()][2];
        int pos=0;
        for (Element link : links) {
            noticias[pos][0] =link.attr("abs:href");
            noticias[pos++][1] =link.ownText();
        }
        return noticias;
    }

}