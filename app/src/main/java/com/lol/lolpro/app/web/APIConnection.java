package com.lol.lolpro.app.web;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lol.lolpro.app.BBDDHelper;
import com.lol.lolpro.app.DBManager;

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
import java.util.Arrays;
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
    private static final String ITEM_URI = "static-data/euw/v1.2/item?locale=es_ES&itemListData=gold,image&";
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
        switch (type) {
            case CHAMPIONS:
                rutaImagen = bdConnection.obtenerRutaVersionCampeon();
                patt = Patrones.PATTERN_CHAMPION;
                match = patt.matcher(answer);
                while (match.find()) {
                    bdConnection.guardarCampeones(Integer.parseInt(match.group(1)), TextUtils.htmlEncode(match.group(2)),
                            TextUtils.htmlEncode(match.group(3)), TextUtils.htmlEncode(match.group(6)),
                            TextUtils.htmlEncode(match.group(17)), TextUtils.htmlEncode(match.group(18)),
                            TextUtils.htmlEncode(match.group(19)), TextUtils.htmlEncode(match.group(20)),
                            TextUtils.htmlEncode(match.group(10)), TextUtils.htmlEncode(match.group(11)),
                            TextUtils.htmlEncode(match.group(8)), TextUtils.htmlEncode(match.group(9)),
                            TextUtils.htmlEncode(match.group(13)), TextUtils.htmlEncode(match.group(14)),
                            TextUtils.htmlEncode(match.group(15)), TextUtils.htmlEncode(match.group(16)),
                            TextUtils.htmlEncode(match.group(7)), TextUtils.htmlEncode(match.group(22)),
                            TextUtils.htmlEncode(match.group(23)), TextUtils.htmlEncode(match.group(24)),
                            TextUtils.htmlEncode(match.group(24)), TextUtils.htmlEncode(match.group(26)),
                            TextUtils.htmlEncode(match.group(27)),TextUtils.htmlEncode(match.group(21)),
                            TextUtils.htmlEncode(rutaImagen + match.group(4)));
                }
                break;
            case OBJECTS:
                int purchasable = 0;
                rutaImagen = bdConnection.obtenerRutaVersionObjeto();
                patt = Patrones.PATTERN_ITEMS;
                match = patt.matcher(answer);
                while (match.find()) {
                    purchasable = Boolean.parseBoolean(match.group(5)) ? 1 : 0;
                    bdConnection.guardarObjetos(Integer.parseInt(match.group(1)), TextUtils.htmlEncode(match.group(2)),
                            Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)),
                            TextUtils.htmlEncode(match.group(6)), purchasable,
                            TextUtils.htmlEncode(rutaImagen + match.group(7)));
                }
                break;
            case IMAGES_AND_VERSIONS:
                patt = Patrones.PATTERN_PATH_AND_VERSIONS;
                match = patt.matcher(answer);
                if (match.find()) {
                    bdConnection.guardarRutaVersiones(match.group(3), match.group(2), TextUtils.htmlEncode(match.group(1)));
                } else {
                    Log.e("error", "Patron de versiones erroneo");
                }
                break;
            case CHAMPION_FREE:
                patt = Patrones.PATTERN_CHAMPION_FREE;
                match = patt.matcher(answer);
                int[] ids = new int[10];
                int pos = 0;
                while (match.find()) {
                    ids[pos++] = Integer.parseInt(match.group(1));
                }
                bdConnection.modificarGratuito(ids);
                break;
            case UPDATE_CHAMPIONS:
                rutaImagen = bdConnection.obtenerRutaVersionCampeon();
                patt = Patrones.PATTERN_CHAMPION;
                match = patt.matcher(answer);
                while (match.find()) {
                    bdConnection.modificarCampeones(Integer.parseInt(match.group(1)), TextUtils.htmlEncode(match.group(2)),
                            TextUtils.htmlEncode(match.group(3)), TextUtils.htmlEncode(match.group(6)),
                            TextUtils.htmlEncode(match.group(17)), TextUtils.htmlEncode(match.group(18)),
                            TextUtils.htmlEncode(match.group(19)), TextUtils.htmlEncode(match.group(20)),
                            TextUtils.htmlEncode(match.group(10)), TextUtils.htmlEncode(match.group(11)),
                            TextUtils.htmlEncode(match.group(8)), TextUtils.htmlEncode(match.group(9)),
                            TextUtils.htmlEncode(match.group(13)), TextUtils.htmlEncode(match.group(14)),
                            TextUtils.htmlEncode(match.group(15)), TextUtils.htmlEncode(match.group(16)),
                            TextUtils.htmlEncode(match.group(7)), TextUtils.htmlEncode(match.group(22)),
                            TextUtils.htmlEncode(match.group(23)), TextUtils.htmlEncode(match.group(24)),
                            TextUtils.htmlEncode(match.group(24)), TextUtils.htmlEncode(match.group(26)),
                            TextUtils.htmlEncode(match.group(27)),TextUtils.htmlEncode(match.group(21)),
                            TextUtils.htmlEncode(rutaImagen + match.group(4)));
                }
                break;
            case UPDATE_OBJECTS:
                int purchasable2 = 0;
                rutaImagen = bdConnection.obtenerRutaVersionObjeto();
                patt = Patrones.PATTERN_ITEMS;
                match = patt.matcher(answer);
                while (match.find()) {
                    purchasable2 = Boolean.parseBoolean(match.group(5)) ? 1 : 0;
                    bdConnection.modificarObjetos(Integer.parseInt(match.group(1)), TextUtils.htmlEncode(match.group(2)),
                            Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)),
                            TextUtils.htmlEncode(match.group(6)), purchasable2,
                            TextUtils.htmlEncode(rutaImagen + match.group(7)));
                }
                break;

        }
    }

    public boolean haCambiadoVersion() {
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
        if (Arrays.equals(campeonesAntiguosGratuitos, campeonesNuevosGratuitos)) {
            return false;
        }
        return true;
    }

    public void closeAPI(){
        dbMan.closeDatabase(true);
    }
}