package com.lol.lolpro.app;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
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
        _modelMap.put(5, "xinzhao");
        _modelMap.put(6, "urgot");
        _modelMap.put(7, "leblanc");
        _modelMap.put(8, "vladimir");
        _modelMap.put(9, "fiddlesticks");
        _modelMap.put(10, "kayle");
        _modelMap.put(11, "masteryi");
        _modelMap.put(12, "alistar");
        _modelMap.put(13, "ryze");
        _modelMap.put(14, "sion");
        _modelMap.put(15, "sivir");
        _modelMap.put(16, "soraka");
        _modelMap.put(17, "teemo");
        _modelMap.put(18, "tristana");
        _modelMap.put(19, "warwick");
        _modelMap.put(20, "nunu");
        _modelMap.put(21, "missfortune");
        _modelMap.put(22, "ashe");
        _modelMap.put(23, "tryndamere");
        _modelMap.put(24, "jax");
        _modelMap.put(25, "morgana");
        _modelMap.put(26, "zilean");
        _modelMap.put(27, "singed");
        _modelMap.put(28, "evelynn");
        _modelMap.put(29, "twitch");
        _modelMap.put(30, "karthus");
        _modelMap.put(31, "chogath");
        _modelMap.put(32, "amumu");
        _modelMap.put(33, "rammus");
        _modelMap.put(34, "anivia");
        _modelMap.put(35, "shaco");
        _modelMap.put(36, "drmundo");
        _modelMap.put(37, "sona");
        _modelMap.put(38, "kassadin");
        _modelMap.put(39, "irelia");
        _modelMap.put(40, "janna");
        _modelMap.put(41, "gangplank");
        _modelMap.put(42, "corki");
        _modelMap.put(43, "karma");
        _modelMap.put(44, "taric");
        _modelMap.put(45, "veigar");
        _modelMap.put(48, "trundle");
        _modelMap.put(50, "swain");
        _modelMap.put(51, "caitlyn");
        _modelMap.put(53, "blitzcrank");
        _modelMap.put(54, "malphite");
        _modelMap.put(55, "katarina");
        _modelMap.put(56, "nocturne");
        _modelMap.put(57, "maokai");
        _modelMap.put(58, "renekton");
        _modelMap.put(59, "jarvaniv");
        _modelMap.put(61, "orianna");
        _modelMap.put(62, "monkeyking");
        _modelMap.put(63, "brand");
        _modelMap.put(64, "leesin");
        _modelMap.put(67, "vayne");
        _modelMap.put(68, "rumble");
        _modelMap.put(69, "cassiopeia");
        _modelMap.put(72, "skarner");
        _modelMap.put(74, "heimerdinger");
        _modelMap.put(75, "nasus");
        _modelMap.put(76, "nidalee");
        _modelMap.put(77, "udyr");
        _modelMap.put(78, "poppy");
        _modelMap.put(79, "gragas");
        _modelMap.put(80, "pantheon");
        _modelMap.put(81, "ezreal");
        _modelMap.put(82, "mordekaiser");
        _modelMap.put(83, "yorick");
        _modelMap.put(84, "akali");
        _modelMap.put(85, "kennen");
        _modelMap.put(86, "garen");
        _modelMap.put(89, "leona");
        _modelMap.put(90, "malzahar");
        _modelMap.put(91, "talon");
        _modelMap.put(92, "riven");
        _modelMap.put(96, "kogmaw");
        _modelMap.put(98, "shen");
        _modelMap.put(99, "lux");
        _modelMap.put(101, "xerath");
        _modelMap.put(102, "shyvana");
        _modelMap.put(103, "ahri");
        _modelMap.put(104, "graves");
        _modelMap.put(105, "fizz");
        _modelMap.put(106, "volibear");
        _modelMap.put(110, "varus");
        _modelMap.put(111, "nautilus");
        _modelMap.put(112, "viktor");
        _modelMap.put(113, "sejuani");
        _modelMap.put(114, "fiora");
        _modelMap.put(115, "ziggs");
        _modelMap.put(117, "lulu");
        _modelMap.put(119, "draven");
        _modelMap.put(120, "hecarim");
        _modelMap.put(122, "darius");
        _modelMap.put(126, "jayce");
        _modelMap.put(143, "zyra");
        _modelMap.put(131, "diana");
        _modelMap.put(107, "rengar");
        _modelMap.put(134, "syndra");
        _modelMap.put(121, "khazix");
        _modelMap.put(60, "elise");
        _modelMap.put(238, "zed");
        _modelMap.put(267, "nami");
        _modelMap.put(254, "vi");
        _modelMap.put(412, "thresh");
        _modelMap.put(133, "quinn");
        _modelMap.put(154, "zac");
        _modelMap.put(127, "lissandra");
        _modelMap.put(266, "aatrox");
        _modelMap.put(236, "lucian");
        _modelMap.put(222, "jinx");
        _modelMap.put(157, "yasuo");
        _modelMap.put(161, "velkoz");
        _modelMap.put (201, "braum");
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
            // Obtenemos un Iterador y recorremos la lista.
            Iterator iter = urlsError.iterator();
            while (iter.hasNext())
                Log.e("error", iter.next().toString());
            return;
        }
        urlsEsperando.remove(url);
        urlsProcesadas.put(url, doc.text());

        Elements nombre = doc.select("div.default-2-3 > h3");
        Elements nick = doc.select("div.default-2-3");
        Elements ciudad=doc.select ("div.faction-small");
        Elements vida=doc.select ("span.stat-hp + span.stat-value");
        Elements regeneracionVida=doc.select ("span.stat-hp-regen + span.stat-value");
        Elements danioAtaque=doc.select ("span.stat-ad + span.stat-value");
        Elements armadura=doc.select ("span.stat-armor + span.stat-value");
        Elements velocidadAtaque=doc.select ("span.stat-as + span.stat-value");
        Elements resistenciaMagica=doc.select ("span.stat-mr + span.stat-value");
        Elements velocidadMovimiento=doc.select ("span.stat-ms + span.stat-value");
        Elements imagen = doc.select("div.default-1-3 > img[src]");

        helper.guardarDatos(TextUtils.htmlEncode(nombre.get(0).ownText()),
                TextUtils.htmlEncode(nick.get(0).ownText()),
                TextUtils.htmlEncode(ciudad.get(0).ownText()),
                TextUtils.htmlEncode(vida.get(0).ownText()),
                TextUtils.htmlEncode(regeneracionVida.get(0).ownText()),
                TextUtils.htmlEncode(danioAtaque.get(0).ownText()),
                TextUtils.htmlEncode(armadura.get(0).ownText()),
                TextUtils.htmlEncode(velocidadAtaque.get(0).ownText()),
                TextUtils.htmlEncode(resistenciaMagica.get(0).ownText()),
                TextUtils.htmlEncode(velocidadMovimiento.get(0).ownText()),
                imagen.get(0).attr("abs:src"));
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
