package com.lol.lolpro.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by sergio on 29/05/14.
 */
public class spider extends AsyncTask{
    private String urlInicial;
    private static Map<Integer, String> _modelMap;
    BBDDHelper helper;


    static {
        _modelMap = new HashMap<Integer, String>();
        _modelMap.put(1, "annie");
        _modelMap.put(2, "olaf");
        _modelMap.put(3, "galio");
        _modelMap.put(4, "twistedfate");
        _modelMap.put(5, "XinZhao");
        _modelMap.put(6, "Urgot");
        _modelMap.put(7, "LeBlanc");
        _modelMap.put(8, "Vladimir");
        _modelMap.put(9, "Fiddlesticks");
        _modelMap.put(10, "Kayle");
        _modelMap.put(11, "MasterYi");
        _modelMap.put(12, "Alistar");
        _modelMap.put(13, "Ryze");
        _modelMap.put(14, "Sion");
        _modelMap.put(15, "Sivir");
        _modelMap.put(16, "Soraka");
        _modelMap.put(17, "Teemo");
        _modelMap.put(18, "Tristana");
        _modelMap.put(19, "Warwick");
        _modelMap.put(20, "Nunu");
        _modelMap.put(21, "MissFortune");
        _modelMap.put(22, "Ashe");
        _modelMap.put(23, "Tryndamere");
        _modelMap.put(24, "Jax");
        _modelMap.put(25, "Morgana");
        _modelMap.put(26, "Zilean");
        _modelMap.put(27, "Singed");
        _modelMap.put(28, "Evelynn");
        _modelMap.put(29, "Twitch");
        _modelMap.put(30, "Karthus");
        _modelMap.put(31, "ChoGath");
        _modelMap.put(32, "Amumu");
        _modelMap.put(33, "Rammus");
        _modelMap.put(34, "Anivia");
        _modelMap.put(35, "Shaco");
        _modelMap.put(36, "DrMundo");
        _modelMap.put(37, "Sona");
        _modelMap.put(38, "Kassadin");
        _modelMap.put(39, "Irelia");
        _modelMap.put(40, "Janna");
        _modelMap.put(41, "Gangplank");
        _modelMap.put(42, "Corki");
        _modelMap.put(43, "Karma");
        _modelMap.put(44, "Taric");
        _modelMap.put(45, "Veigar");
        _modelMap.put(48, "Trundle");
        _modelMap.put(50, "Swain");
        _modelMap.put(51, "Caitlyn");
        _modelMap.put(53, "Blitzcrank");
        _modelMap.put(54, "Malphite");
        _modelMap.put(55, "Katarina");
        _modelMap.put(56, "Nocturne");
        _modelMap.put(57, "Maokai");
        _modelMap.put(58, "Renekton");
        _modelMap.put(59, "JarvanIV");
        _modelMap.put(61, "Orianna");
        _modelMap.put(62, "Wukong");
        _modelMap.put(63, "Brand");
        _modelMap.put(64, "LeeSin");
        _modelMap.put(67, "Vayne");
        _modelMap.put(68, "Rumble");
        _modelMap.put(69, "Cassiopeia");
        _modelMap.put(72, "Skarner");
        _modelMap.put(74, "Heimerdinger");
        _modelMap.put(75, "Nasus");
        _modelMap.put(76, "Nidalee");
        _modelMap.put(77, "Udyr");
        _modelMap.put(78, "Poppy");
        _modelMap.put(79, "Gragas");
        _modelMap.put(80, "Pantheon");
        _modelMap.put(81, "Ezreal");
        _modelMap.put(82, "Mordekaiser");
        _modelMap.put(83, "Yorick");
        _modelMap.put(84, "Akali");
        _modelMap.put(85, "Kennen");
        _modelMap.put(86, "Garen");
        _modelMap.put(89, "Leona");
        _modelMap.put(90, "Malzahar");
        _modelMap.put(91, "Talon");
        _modelMap.put(92, "Riven");
        _modelMap.put(96, "KogMaw");
        _modelMap.put(98, "Shen");
        _modelMap.put(99, "Lux");
        _modelMap.put(101, "Xerath");
        _modelMap.put(102, "Shyvana");
        _modelMap.put(103, "Ahri");
        _modelMap.put(104, "Graves");
        _modelMap.put(105, "Fizz");
        _modelMap.put(106, "Volibear");
        _modelMap.put(110, "Varus");
        _modelMap.put(111, "Nautilus");
        _modelMap.put(112, "Viktor");
        _modelMap.put(113, "Sejuani");
        _modelMap.put(114, "Fiora");
        _modelMap.put(115, "Ziggs");
        _modelMap.put(117, "Lulu");
        _modelMap.put(119, "Draven");
        _modelMap.put(120, "Hecarim");
        _modelMap.put(122, "Darius");
        _modelMap.put(126, "Jayce");
        _modelMap.put(143, "Zyra");
        _modelMap.put(131, "Diana");
        _modelMap.put(107, "Rengar");
        _modelMap.put(134, "Syndra");
        _modelMap.put(121, "KhaZix");
        _modelMap.put(60, "Elise");
        _modelMap.put(238, "Zed");
        _modelMap.put(267, "Nami");
        _modelMap.put(254, "Vi");
        _modelMap.put(412, "Thresh");
        _modelMap.put(133, "Quinn");
        _modelMap.put(154, "Zac");
        _modelMap.put(127, "Lissandra");
        _modelMap.put(266, "Aatrox");
        _modelMap.put(236, "Lucian");
        _modelMap.put(222, "Jinx");
        _modelMap.put(157, "Yasuo");
        _modelMap.put(161, "Vel'Koz");
        _modelMap.put (201, "Braum");
    }

    private final Map<String,String> urlsProcesadas = new HashMap<String, String>();
    //	Lista en la que se almacenaran todas las URLs que todavia no han sido
    //  procesadas, es decir aquellos enlaces a los que hacen referencia
    //  las URLs tratadas. Esta lista permite evitar la excepcion
    //  "ConcurrentModificationException" asi como implementar la
    //  profundidad facilmente.
    private final List<String> urlsEsperando = new CopyOnWriteArrayList<String>();
    //  Almacenamos las URL erroneas para evitar volver a acceder a ellas en un
    //  futuro, aumentando el rendimiento.
    private final ArrayList<String> urlsError = new ArrayList<String>();


    public spider(String url){
        urlInicial = url;
        Iterator it = _modelMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            urlsEsperando.add(buildURL (urlInicial, (String) e.getValue()));
        }
    }
    //	Almacenamos las URL con su contenido como Map para tener URL y contenido
    //	relacionadas y hacer busquedas rapidas. La URL sera la clave por
    //  la que buscar en el Map.


    /*public void analizarURLs(){
        Document doc;
        try {
            doc = Jsoup.connect(urlInicial).get();
        } catch (Exception ex) {
            urlsEsperando.remove(urlInicial);
            if(ex instanceof SocketTimeoutException){
                //  Este tipo de error indica que ha habido un problema al
                //  cargar la pagina pero puede ser valida por lo que no la
                //  etiquetamos como erronea permitiendo futuros intentos.
                return;
            }
            //  Añadimos la URL a erroneas si es MalformedURLException
            //	HttpStatusException o UnsupportedMimeTypeException
            //  evitando futuros intentos
            urlsError.add(urlInicial);
            return;
        }
        urlsEsperando.remove(urlInicial);
        urlsProcesadas.put(urlInicial, doc.text());
        extractChampLinks(urlInicial, doc);
    }*/

    /*private void extractChampLinks(String urlBase, Document doc) {
        //  Seleccionamos todos los elementos del documento de tipo a y que
        //  contengan el atributo href, todos los campeones.
        //TODO coger Defensa: 4/10
        Elements links = doc.select("div.champ-name > a[href]");

        for (Element link : links) {
            aniadirEsperando(link.attr("abs:href"));
        }

        checkChampionURLs();
    }*/

    //TODO Y si da un error la url xq no haya conexion??
    private void checkChampionURLs() {
        //	La clase "CopyOnWriteArrayList" proporciona al iterador un
        //	snapshot de la lista, evitando la excepcion
        //	"ConcurrentModificationException"
        Iterator<String> it = urlsEsperando.iterator();
        while(it.hasNext()){
            extractChampionInfo(it.next());
        }
    }

    /**
     * Comprueba el String suministrado y, en caso de ser valido, crea una url a
     * partir el.
     * @param url Direccion web absoluta que usaremos como base para crear la
     *              nueva URL.
     * @param relativeUrl Direccion web relativa que queremos acoplar a la base.
     * @return Devuelve la URL resultante de la conversion ya limpia o, en caso
     * de no ser posible, devuelve null.
     */
    private String buildURL(String url, String relativeUrl){
        try {
            URL url2 = new URL(new URL(url), relativeUrl);
            return url2.getProtocol()+"://"+url2.getHost()+url2.getFile();
        } catch (MalformedURLException ex) {
            return "";
        }
    }

    private void extractChampionInfo(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception ex) {
            urlsEsperando.remove(url);
            if(ex instanceof SocketTimeoutException){
                //  Este tipo de error indica que ha habido un problema al
                //  cargar la pagina pero puede ser valida por lo que no la
                //  etiquetamos como erronea permitiendo futuros intentos.
                return;
            }
            //  Añadimos la URL a erroneas si es MalformedURLException
            //	HttpStatusException o UnsupportedMimeTypeException
            //  evitando futuros intentos
            urlsError.add(url);
            return;
        }
        urlsEsperando.remove(url);
        urlsProcesadas.put(url, doc.text());

        Elements nombre = doc.select("div.default-2-3 > h3");

        for (Element nmb : nombre) {
            Log.e("YAIZA", "ownText: "+nmb.ownText());
        }

        Elements nick = doc.select("div.default-2-3");

        for (Element nmb : nick) {
            Log.e("YAIZA", "ownText: "+nmb.ownText());
        }

        Elements imagen = doc.select("div.default-1-3 > img[src]");

        for (Element img : imagen) {
            Log.e("YAIZA", "imagen____"+img.attr("abs:src"));
        }

        helper.guardarDatos(nombre.get(0).toString(), nick.get(0).toString(), "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, imagen.get(0).toString());
    }

    /**
     * Comprueba que la URL proporcionada no haya sido procesada ya, se
     * encuentre ya lista para ser procesada o haya sido detectada como
     * pagina erronea.
     * @param url URL que se quiere analizar.
     */
    public void aniadirEsperando(String url){
        if(!urlsEsperando.contains(url) && !urlsError.contains(url) &&
                !urlsProcesadas.containsKey(url))
            urlsEsperando.add(url);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        helper = new BBDDHelper((Context)objects[0]);
        checkChampionURLs();
        return null;
    }
}
