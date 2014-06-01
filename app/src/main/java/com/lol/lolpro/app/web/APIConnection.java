package com.lol.lolpro.app.web;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.lol.lolpro.app.BBDDHelper;

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
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by yaiza on 31/05/14.
 */
public class APIConnection {

    public static final int CAMPEONES = 0;
    public static final int IMAGENES = 1;
    public static final int OBJETOS = 2;

    //TODO trucar la ruta BASE_URI en funcion del idioma... de forma que optenemos los campeones en su idioma ;)
    private static final String CERT_NAME = "lolcert.pem";
    private static final String BASE_URI = "https://euw.api.pvp.net/api/lol/static-data/euw/v1.2/";
    private static final String CHAMPION_URI = "champion?locale=es_ES&champData=image,stats&";
    private static final String ITEM_URI = "item?locale=es_ES&itemListData=gold,image&";
    private static final String IMG_URI = "realm?";
    private static final String API_KEY = "api_key=56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";

    private static final String CERT_ALIAS = "LOLCert";
    //TODO singleton para evitar validar muchos certificados
    //public static final APIConnection API_CONNECTION = ;
    //TODO sacar variables de certificado y eso

    private KeyStore keyStore;
    private TrustManagerFactory tmf;
    private SSLContext sslCont;
    private Context context;

    private BBDDHelper bdConnection;

    //ToDo Inicializar todo con el singleton
    public APIConnection(Context contexto) {
        context = contexto;
        bdConnection = new BBDDHelper(contexto);
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
    public URI createURI(int type) {
        // Ahora mismo solo devuelve la URI de obtener campeones
        StringBuffer url = new StringBuffer(BASE_URI);
        switch (type) {
            case CAMPEONES:
                url = url.append(CHAMPION_URI).append(API_KEY);
                break;
            case IMAGENES:
                url = url.append(IMG_URI).append(API_KEY);
                break;
            case OBJETOS:
                url = url.append(ITEM_URI).append(API_KEY);
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

    public boolean hasCert() {
        try {
            return keyStore.isCertificateEntry(CERT_ALIAS);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertCert() {
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
    public String connect2API(int type/*CONSTANTES.CAMPEONES, TIPOS.OFERTA*/) {
        String respuesta = null;
        try {
            URI uriConsulta = createURI(type);
            if (uriConsulta != null) {
                if (!hasCert()) {
                    insertCert();
                }
                ConnectionResult resultado = new ConnectionResult(sslCont);
                respuesta = resultado.execute(uriConsulta).get();
                extractAndStoreData(respuesta, type/*, CONSTANTES.CAMPEONES, TIPOS.OFERTA*/);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    //Cogemos la ultima version y la direccion de las imagenes
    public void getUpdatedImageURL() {

    }

    public void extractAndStoreData(String answer, int type) {
        Pattern patt = null;
        Matcher match = null;
        String rutaImagen;
        switch (type) {
            case CAMPEONES:
                rutaImagen = bdConnection.obtenerRutaVersionCampeon();
                patt = Patrones.PATTERN_CHAMPION;
                match = patt.matcher(answer);
                while (match.find()) {
                    bdConnection.guardarCampeones(Integer.parseInt(match.group(1)),TextUtils.htmlEncode(match.group(2)),
                            TextUtils.htmlEncode(match.group(3)), TextUtils.htmlEncode(match.group(8)),
                            TextUtils.htmlEncode(match.group(9)), TextUtils.htmlEncode(match.group(6)),
                            TextUtils.htmlEncode(match.group(5)), TextUtils.htmlEncode(match.group(7)),
                            TextUtils.htmlEncode(match.group(11)), TextUtils.htmlEncode(match.group(10)),
                            TextUtils.htmlEncode(rutaImagen + match.group(4)));
                }
                break;
            case IMAGENES:
                patt = Patrones.PATTERN_PATH_AND_VERSIONS;
                match = patt.matcher(answer);
                if(match.find()) {
                    bdConnection.guardarRutaVersiones(match.group(3), match.group(2), match.group(1));
                }
                else{
                    Log.e("error","Patron de versiones erroneo");
                }
                break;
            case OBJETOS:
                int purchasable = 0;
                rutaImagen = bdConnection.obtenerRutaVersionObjeto();
                patt = Patrones.PATTERN_ITEMS;
                match = patt.matcher(answer);
                while (match.find()) {
                    purchasable = Boolean.parseBoolean(match.group(5))?1:0;
                    bdConnection.guardarObjetos(Integer.parseInt(match.group(1)), TextUtils.htmlEncode(match.group(2)),
                            Integer.parseInt(match.group(3)), Integer.parseInt(match.group(4)),
                            TextUtils.htmlEncode(match.group(6)), purchasable,
                            TextUtils.htmlEncode(rutaImagen+match.group(7)));
                }
                break;
        }
    }
}