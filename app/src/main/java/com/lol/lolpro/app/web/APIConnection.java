package com.lol.lolpro.app.web;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.lolpro.app.EasyX509TrustManager;
import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Campeones.BaseCampeones;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Skin;
import com.lol.lolpro.app.json.EstadoCampeones.BaseEstadoCampeones;
import com.lol.lolpro.app.json.EstadoCampeones.ChampionState;
import com.lol.lolpro.app.json.Objetos.BaseObjeto;
import com.lol.lolpro.app.json.Objetos.Item;
import com.lol.lolpro.app.json.Objetos.Tree;
import com.lol.lolpro.app.json.Realm.BaseRealm;
import com.lol.lolpro.app.utillidades.Constants;
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
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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

    private static final String CERT_NAME_RIOT = "_.api.pvp.net";
    private static final String CERT_NAME_DIGICERT_CA3 = "DigiCertSHA2HighAssuranceServerCA";
    private static final String BASE_URI = "https://euw.api.pvp.net/api/lol/";
    private static final String GLOBAL_URI = "https://global.api.pvp.net/api/lol/";
    private static final String CHAMPION_URI = "static-data/euw/v1.2/champion?locale=es_ES&champData=image,stats,lore,partype,skins,passive,spells&";
    private static final String ITEM_URI = "static-data/euw/v1.2/item?locale=es_ES&itemListData=all&";
    private static final String METADATA_URI = "static-data/euw/v1.2/realm?";
    private static final String CHAMPION_FREE_URI = "euw/v1.2/champion?freeToPlay=true&";
    private static final String API_KEY = "api_key=56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";
    private static final String VERSION_HEADER = "&version=";

    private static final String CERT_ALIAS_RIOT = "RIOT";
    private static final String CERT_ALIAS_DIGICERT_CA3 = "DIGICERT_CA3";


    private KeyStore keyStore;
    private X509TrustManager tm;
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
            return null;
        }
    }

    private URI createURI(int type, String version) {
        try {
            return new URI(new StringBuffer(createURI(type).toString()).append(VERSION_HEADER).append(version).toString());
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private boolean hasCert() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                return keyStore.isCertificateEntry(CERT_ALIAS_RIOT);
            } else {
                return (keyStore.isCertificateEntry(CERT_ALIAS_RIOT) && keyStore.isCertificateEntry(CERT_ALIAS_DIGICERT_CA3));
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertCert() {
        try {
            CertificateFactory certFact = CertificateFactory.getInstance("X.509");
            InputStream caInput = context.getAssets().open(APIConnection.CERT_NAME_RIOT);
            Certificate cert;
            try {
                //MUST respect this order to work properly on older Android versions 2.3.x
                //  The import order must be from bottom to up, use different aliases and
                //  ROOT alias must be "ca"
                cert = certFact.generateCertificate(caInput);
                keyStore.setCertificateEntry(CERT_ALIAS_RIOT, cert);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                    caInput.reset();
                    caInput = context.getAssets().open(APIConnection.CERT_NAME_DIGICERT_CA3);
                    cert = certFact.generateCertificate(caInput);
                    keyStore.setCertificateEntry(CERT_ALIAS_DIGICERT_CA3, cert);
                }
            } finally {
                caInput.close();
            }
            // Create an SSLContext that uses our TrustManager
            sslCont = SSLContext.getInstance("TLS");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tm = new EasyX509TrustManager(keyStore);
                sslCont.init(null, new TrustManager[]{tm}, null);
            } else {
                // Create a KeyStore containing our trusted CAs
                // Create a TrustManager that trusts the CAs in our KeyStore
                tmf.init(keyStore);
                sslCont.init(null, tmf.getTrustManagers(), null);
            }
            return true;
        } catch (Exception e) {
            Log.e("error", "Error al validar el certificado del servidor");
            return false;
        }
    }

    //Definir varios casos, campeones, ofertas...
    public void connect2API(int type) {
        URI uriConsulta = createURI(type);
        if (uriConsulta != null) {
            if (!hasCert()) {
                insertCert();
            }
            String respuesta = new ConnectionResult(sslCont).getHttpsResult(uriConsulta);
            if(!respuesta.isEmpty()){
                extractAndStoreData(respuesta, type);
            }
        }
    }

    public void extractAndStoreData(String answer, int type) {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (type) {
            case CHAMPIONS:
            case UPDATE_CHAMPIONS:
                try {
                    BaseCampeones bCampeones = objectMapper.readValue(answer, BaseCampeones.class);
                    for (Champion campeon: bCampeones.getData().getMCampeones().values()) {
                        bdConnection.insertarCampeon(campeon, type == UPDATE_CHAMPIONS);
                        bdConnection.insertarHabilidadesCampeon (campeon, type == UPDATE_CHAMPIONS);
                        bdConnection.insertarAspectosCampeon (campeon, type == UPDATE_CHAMPIONS);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bdConnection.borrarHabilidadesDesfasadas();
                break;
            case OBJECTS:
            case UPDATE_OBJECTS:
                Map<String, String> hyperTags = new HashMap<String, String>();
                try {
                    BaseObjeto bObjeto = objectMapper.readValue(answer, BaseObjeto.class);
                    // Cogemos los hyperTags del tree
                    for (Tree tree: bObjeto.getTree()) {
                        for (String tag: tree.getTags()){
                            hyperTags.put(tag, tree.getHeader());
                        }
                    }
                    for (Item item: bObjeto.getData().getMItems().values()) {
                        bdConnection.insertarObjeto(item, type == UPDATE_OBJECTS);
                        if (type == UPDATE_OBJECTS) {
                            bdConnection.borrarTagsObjeto(item.getId());
                        }
                        if (item.getTags() != null && !item.getTags().isEmpty()) {
                            for (String tag : item.getTags()) {
                                bdConnection.insertarTagObjeto(item.getId(), tag, hyperTags.get(tag.toUpperCase()));
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case IMAGES_AND_VERSIONS:
                try {
                    BaseRealm bRealm = objectMapper.readValue(answer, BaseRealm.class);
                    bdConnection.guardarRutaVersiones(bRealm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case CHAMPION_FREE:
                try {
                    BaseEstadoCampeones bEstadoCampeones = objectMapper.readValue(answer, BaseEstadoCampeones.class);
                    bdConnection.updateFreeChamps(bEstadoCampeones);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public boolean hayNuevaVersion() {
        String versionAntiguaCampeon = bdConnection.obtenerVersionCampeon();
        String versionAntiguaObjetos = bdConnection.obtenerVersionObjeto();
        connect2API(APIConnection.IMAGES_AND_VERSIONS);
        if (!versionAntiguaCampeon.equals(bdConnection.obtenerVersionCampeon()) || !versionAntiguaObjetos.equals(bdConnection.obtenerVersionObjeto())) {
            return true;
        }
        return false;
    }

    public boolean hayNuevosGratuitos() {
        String[][] campeonesAntiguosGratuitos = bdConnection.obtenerGratuitos();
        connect2API(APIConnection.CHAMPION_FREE);
        String[][] campeonesNuevosGratuitos = bdConnection.obtenerGratuitos();
        return !Arrays.equals(campeonesAntiguosGratuitos, campeonesNuevosGratuitos);
    }

    public void closeAPI() {
        dbMan.closeDatabase(true);
    }
}