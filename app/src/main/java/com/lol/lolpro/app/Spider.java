package com.lol.lolpro.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {
    public static final String NEWSURL = "http://euw.leagueoflegends.com/es/news/";

    public Spider() {
    }

    public String[][] analyzeURLs() {
        Document doc;
        try {
            doc = Jsoup.connect(NEWSURL).get();
        } catch (Exception ex) {
            return null;
        }
        return newsLinks(doc);
    }

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