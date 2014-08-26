package com.lol.lolpro.app.web;

import com.lol.lolpro.app.utillidades.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Clase que se encarga de obtner los últimos urls y los títulos de las últimas noticias de http://euw.leagueoflegends.com
 */
public class Spider {
    public static final String NEWSURL = "http://euw.leagueoflegends.com/es/news/";

    /**
     * Constructor vacío
     */
    public Spider() {
    }

    /**
     * Se encarga de obtener el documento html asociado a NEWSURL
     *
     * @return Links de las noticias y su título
     */
    public String[][] analyzeURLs() {
        Document doc;
        try {
            doc = Jsoup.connect(NEWSURL).get();
        } catch (Exception ex) {
            return new String[][]{};
        }
        return newsLinks(doc);
    }

    /**
     * Se encarga de obtener del documetno pasado por parámetro las urls de las noticias
     *
     * @param doc Document html de la página que contiene las noticias
     * @return String con tanatas filas como noticas y:
     * EN la columna 1 tiene la url de la noticia
     * en la columna 2 tiene el título de la noticia
     */
    private String[][] newsLinks(Document doc) {
        Elements links = doc.select("h4 > a[href]");
        String[][] news = new String[links.size()][2];
        int pos = 0;
        for (Element link : links) {
            news[pos][Constants.NEWS_URL] = link.attr("abs:href");
            news[pos++][Constants.NEWS_TITLE] = link.ownText();
        }
        return news;
    }
}