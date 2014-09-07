package com.lol.lolpro.app.web;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.utillidades.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by yaiza on 31/05/14.
 */
public class APIConnection {

    public static final int CHAMPIONS = 0;
    public static final int IMAGES_AND_VERSIONS = 1;
    public static final int OBJECTS = 2;
    public static final int CHAMPION_FREE = 3;
    public static final int UPDATE_OBJECTS = 4;
    public static final int UPDATE_CHAMPIONS = 5;

    private static final String CERT_NAME = "lolcert.pem";
    private static final String BASE_URI = "https://euw.api.pvp.net/api/lol/";
    private static final String GLOBAL_URI = "https://global.api.pvp.net/api/lol/";
    private static final String CHAMPION_URI = "static-data/euw/v1.2/champion?locale=es_ES&champData=image,stats,lore,partype,skins,passive,spells&";
    private static final String ITEM_URI = "static-data/euw/v1.2/item?locale=es_ES&itemListData=all&";
    private static final String METADATA_URI = "static-data/euw/v1.2/realm?";
    private static final String CHAMPION_FREE_URI = "euw/v1.2/champion?freeToPlay=true&";
    private static final String API_KEY = "api_key=56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";

    private static final String CERT_ALIAS = "LOLCert";

    private KeyStore keyStore;
    private TrustManagerFactory tmf;
    private SSLContext sslCont;
    private Context context;

    private DBManager dbMan;
    public BBDDHelper bdConnection;

    public APIConnection(Context contexto) {
        context = contexto;
        dbMan = DBManager.getInstance();
        dbMan.openDatabase(true);
        bdConnection = dbMan.getDatabaseHelper();
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Añdir configuracion de idioma, lo que estamos buscando(campeones, ofertas...)
    private URI createURI(int type) {
        // Ahora mismo solo devuelve la URI de obtener campeones
        StringBuffer url = new StringBuffer();
        switch (type) {
            case CHAMPIONS:
            case UPDATE_CHAMPIONS:
                url = url.append(GLOBAL_URI).append(CHAMPION_URI).append(API_KEY);
                break;
            case OBJECTS:
            case UPDATE_OBJECTS:
                url = url.append(GLOBAL_URI).append(ITEM_URI).append(API_KEY);
                break;
            case IMAGES_AND_VERSIONS:
                url = url.append(GLOBAL_URI).append(METADATA_URI).append(API_KEY);
                break;
            case CHAMPION_FREE:
                url = url.append(BASE_URI).append(CHAMPION_FREE_URI).append(API_KEY);
                break;
        }
        try {
            URI uri = new URI(url.toString());
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean hasCert() {
        try {
            return keyStore.isCertificateEntry(CERT_ALIAS);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertCert() {
        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            InputStream caInput = (InputStream) context.getAssets().open(APIConnection.CERT_NAME);
            Certificate cert;
            try {
                cert = certFact.generateCertificate(caInput);
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            keyStore.setCertificateEntry(CERT_ALIAS, cert);

            // Create a TrustManager that trusts the CAs in our KeyStore
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            sslCont = SSLContext.getInstance("TLS");
            sslCont.init(null, tmf.getTrustManagers(), null);

            return true;
        } catch (Exception e) {
            Log.e("error", "Error al validar el certificado del servidor");
            e.printStackTrace();
            return false;
        }
    }

    //Definir varios casos, campeones, ofertas...
    public String connect2API(int type) {
        String respuesta = null;
        URI uriConsulta = createURI(type);
        if (uriConsulta != null) {
            if (!hasCert()) {
                insertCert();
            }
            ConnectionResult resultado = new ConnectionResult(sslCont);
            respuesta = resultado.getHttpsResult(uriConsulta);
            extractAndStoreData(respuesta, type);
        }
        return respuesta;
    }

    public void extractAndStoreData(String answer, int type) {
        Pattern patt = null;
        Matcher match = null;
        String rutaImagen;
        String rutaImagenAspecto;
        String rutaImagenHabilidades;
        String rutaImagenHabilidadesPasivas;
        int i;
        switch (type) {
            case CHAMPIONS:
                ArrayList <ArrayList <String>>vars= null;
                ArrayList <String>datos = null;
                String [] effects;
                String [] tags;
                rutaImagen = bdConnection.obtenerRutaVersionCampeon();
                rutaImagenAspecto = bdConnection.obtenerRutaAspectosCampeon();
                rutaImagenHabilidades = bdConnection.obtenerRutaHabilidadesCampeon(0);
                rutaImagenHabilidadesPasivas = bdConnection.obtenerRutaHabilidadesCampeon(1);
                patt = Patrones.PATTERN_CHAMPION;
                match = patt.matcher(answer);
                Pattern patt2 = Patrones.PATTERN_SKINS;
                Matcher match2 = null;
                Pattern patt3 = Patrones.PATTERN_ABILITIES;
                Matcher match3 = null;
                Pattern patt4 = Patrones.PATTERN_PASSIVE;
                Matcher match4 = null;
                Pattern patt5 = Patrones.PATTERN_VARS;
                Matcher match5 = null;
                while (match.find()) {
                    i = 1;
                    bdConnection.guardarCampeones(Integer.parseInt(match.group(1)),
                            match.group(2), match.group(3),
                            match.group(4), match.group(7),
                            match.group(18),match.group(19),
                            match.group(20), match.group(21),
                            match.group(11), match.group(12),
                            match.group(9), match.group(10),
                            match.group(14), match.group(15),
                            match.group(16), match.group(17),
                            match.group(8), match.group(23),
                            match.group(24), match.group(25),
                            match.group(25), match.group(27),
                            match.group(28), match.group(22),
                            rutaImagen + match.group(5));
                    // Almacenamos las skins
                    match2 = patt2.matcher(match.group(6));
                    while (match2.find()) {
                        bdConnection.guardarAspectos(Integer.parseInt(match2.group(1)),
                                Integer.parseInt(match.group(1)), match2.group(2),
                                Integer.parseInt(match2.group(3)),
                                rutaImagenAspecto + match.group(2) + "_" +
                                        Integer.parseInt(match2.group(3)) + ".jpg"
                        );
                    }
                    // Almacenamos las habilidades y la pasiva
                    match3 = patt3.matcher(match.group(29));
                    effects=null;
                    while (match3.find()) {
                        vars = new ArrayList <ArrayList <String>>();
                        effects = Utils.clearQuotes(match3.group(8)).split(",");
                        if (match3.group(9)!= null) {
                            match5 = patt5.matcher(match3.group(9));
                            while (match5.find()) {
                                datos= new ArrayList<String>();
                                datos.add(TextUtils.htmlEncode(match5.group(1)));
                                datos.add(Utils.sanitizeAttackSource (match5.group(3).replaceAll(",", "/"), TextUtils.htmlEncode(match5.group(2)), context));
                                vars.add(datos);
                            }
                        }
                        datos= new ArrayList<String>();
                        datos.add("cost");
                        datos.add(TextUtils.htmlEncode(match3.group(6)));
                        vars.add(datos);
                        for (int j=1; j<effects.length; j++){
                            datos= new ArrayList<String>();
                            datos.add("e"+j);
                            datos.add(effects[j]);
                            vars.add(datos);
                        }
                        bdConnection.guardarHabilidades(Integer.parseInt(match.group(1)),
                                match3.group(1),
                                match3.group(2),
                                Utils.replaceVarsSpells(match3.group(3), vars),
                                Utils.replaceVarsSpells(match3.group(5), vars),
                                match3.group(10),
                                rutaImagenHabilidades + match3.group(4),
                                match3.group(7),
                                i++,
                                0
                        );
                    }
                    match4 = patt4.matcher(match.group(30));
                    while (match4.find()) {
                        bdConnection.guardarHabilidades(Integer.parseInt(match.group(1)),
                                match4.group(1),
                                match4.group(2),
                                "",
                                "",
                                "",
                                rutaImagenHabilidadesPasivas + match4.group(3),
                                "",
                                0,
                                1
                        );
                    }

                }
                break;
            case OBJECTS:
                // Cogemos los hyperTags del tree
                patt = Patrones.PATTERN_TREE_ITEMS;
                match = patt.matcher(answer);
                Map<String, String> hyperTags = new HashMap<String,String>();
                String header;
                while (match.find()) {
                    header = match.group(1);
                    tags = Utils.clearQuotes(match.group(2)).split(",");
                    for (String tag : tags){
                        hyperTags.put(tag, header);
                    }
                }

                // Insertamos los objetos y los tags asociados
                rutaImagen = bdConnection.obtenerRutaVersionObjeto();
                patt = Patrones.PATTERN_ITEMS;
                match = patt.matcher(answer);
                while (match.find()) {
                    bdConnection.guardarObjetos(Integer.parseInt(match.group(1)), match.group(2),
                            Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)),
                            Integer.parseInt(match.group(5)), match.group(6),
                            match.group(7), match.group(8), match.group(9),
                            match.group(10), match.group(11), match.group(12),
                            match.group(13), match.group(14),
                            rutaImagen + match.group(17));
                    if (match.group(15)!= null) {
                        tags = Utils.clearQuotes(match.group(15)).split(",");
                        for(String tag : tags){
                            bdConnection.guardarTagObjeto(Integer.parseInt(match.group(1)), tag, hyperTags.get(tag.toUpperCase()));
                        }
                    }
                }
                break;
            case IMAGES_AND_VERSIONS:
                patt = Patrones.PATTERN_PATH_AND_VERSIONS;
                match = patt.matcher(answer);
                if (match.find()) {
                    bdConnection.guardarRutaVersiones(match.group(3), match.group(2), match.group(1));
                } else {
                    Log.e("error", "Patron de versiones erroneo");
                }
                break;
            case CHAMPION_FREE:
                patt = Patrones.PATTERN_CHAMPION_FREE;
                match = patt.matcher(answer);
                ArrayList<Integer> ids = new ArrayList<Integer>();
                while (match.find()) {
                    ids.add(Integer.parseInt(match.group(1)));
                }
                bdConnection.modificarGratuito(ids);
                break;
            case UPDATE_CHAMPIONS:
                rutaImagen = bdConnection.obtenerRutaVersionCampeon();
                rutaImagenAspecto = bdConnection.obtenerRutaAspectosCampeon();
                patt = Patrones.PATTERN_CHAMPION;
                match = patt.matcher(answer);
                patt2 = Patrones.PATTERN_SKINS;
                match2 = null;
                while (match.find()) {
                    bdConnection.modificarCampeones(Integer.parseInt(match.group(1)),
                            match.group(2), match.group(3),
                            match.group(4), match.group(7),
                            match.group(18), match.group(19),
                            match.group(20), match.group(21),
                            match.group(11), match.group(12),
                            match.group(9), match.group(10),
                            match.group(14), match.group(15),
                            match.group(16), match.group(17),
                            match.group(8), match.group(23),
                            match.group(24), match.group(25),
                            match.group(25), match.group(27),
                            match.group(28), match.group(22),
                            rutaImagen + match.group(5));
                    match2 = patt2.matcher(match.group(6));
                    while (match2.find()) {
                        bdConnection.modificarAspectosCampeon(Integer.parseInt(match2.group(1)),
                                match2.group(2),
                                Integer.parseInt(match2.group(3)),
                                rutaImagenAspecto + match.group(2) + "_" +
                                        Integer.parseInt(match2.group(3)) + ".jpg"
                        );
                    }
                }
                break;
            case UPDATE_OBJECTS:
                int purchasable2, hideFromAll2;
                rutaImagen = bdConnection.obtenerRutaVersionObjeto();
                patt = Patrones.PATTERN_ITEMS;
                match = patt.matcher(answer);
                while (match.find()) {
                    purchasable2 = Boolean.parseBoolean(match.group(5)) ? 1 : 0;
                    hideFromAll2 = Boolean.parseBoolean(match.group(13)) ? 1 : 0;
                    bdConnection.modificarObjetos(Integer.parseInt(match.group(1)), match.group(2),
                            Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)),
                            Integer.parseInt(match.group(5)), match.group(6),
                            match.group(7), match.group(8), match.group(9),
                            match.group(10), match.group(11), match.group(12),
                            match.group(13), match.group(14),
                            rutaImagen + match.group(17));
                }
                break;

        }
    }

    public boolean hayNuevaVersion() {
        String versionAntiguaCampeon = bdConnection.obtenerVersionCampeon();
        String versionAntiguaObjetos = bdConnection.obtenerVersionObjeto();

        connect2API(APIConnection.IMAGES_AND_VERSIONS);
        if (versionAntiguaCampeon.compareTo(bdConnection.obtenerVersionCampeon()) != 0 || versionAntiguaObjetos.compareTo(bdConnection.obtenerVersionObjeto()) != 0) {
            return true;
        }
        return false;
    }

    public boolean hanCambiadoGratuitos() {
        String[][] campeonesAntiguosGratuitos = bdConnection.obtenerGratuitos();
        connect2API(APIConnection.CHAMPION_FREE);
        String[][] campeonesNuevosGratuitos = bdConnection.obtenerGratuitos();
        return Arrays.equals(campeonesAntiguosGratuitos, campeonesNuevosGratuitos);
    }

    public void closeAPI() {
        dbMan.closeDatabase(true);
    }
}