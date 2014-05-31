package com.lol.lolpro.app.web;

import android.content.Context;
import android.util.Log;

import com.lol.lolpro.app.BBDDHelper;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by yaiza on 31/05/14.
 */
public class APIConnection{

    //TODO trucar la ruta BASE_URI en funcion del idioma... de forma que optenemos los campeones en su idioma ;)
    private static final String CERT_NAME = "lolcert.pem";
    private static final String BASE_URI = "https://euw.api.pvp.net/api/lol/static-data/euw/v1.2/champion?locale=es_ES&champData=stats&api_key=56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";
    private static final String API_KEY = "56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";
    private static final String CERT_ALIAS = "LOLCert";
    //TODO singleton para evitar validar muchos certificados
    //public static final APIConnection API_CONNECTION = ;
    //TODO sacar variables de certificado y eso

    private KeyStore keyStore;
    private SSLContext sslCont;

    private BBDDHelper bdConnection;

    //ToDo Inicializar todo con el singleton
    public void APIConnection(){

    }

    //Añdir configuracion de idioma, lo que estamos buscando(campeones, ofertas...)
    public URI createURI(){
        // Ahora mismo solo devuelve la URI de obtener campeones
        try {
            URI uri = new URI(BASE_URI);
            return uri;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasCert(){
        try {
            return keyStore.isCertificateEntry(CERT_ALIAS);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertCert(Context contexto){
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = (InputStream) contexto.getAssets().open(APIConnection.CERT_NAME);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry(CERT_ALIAS, ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
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
    public String connect2API(/*CONSTANTES.CAMPEONES, TIPOS.OFERTA*/){
        String respuesta = null;
        try {
            URI uriConsulta = createURI();
            if(uriConsulta != null) {
                ConnectionResult resultado = new ConnectionResult(sslCont);
                respuesta = resultado.execute(uriConsulta).get();
                extractAndStoreData(respuesta, Patrones.PATRON_CAMPEONES/*, CONSTANTES.CAMPEONES, TIPOS.OFERTA*/);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public void extractAndStoreData(String respuesta, Pattern p){
        Matcher m = p.matcher(respuesta);

        int pos = 0;
        int pos2 = 0;
        while (m.find()){
            bdConnection.guardarCampeones(m.group(2), m.group(3), m.group(7), m.group(8), m.group(5),
                    m.group(4), m.group(6), m.group(10), m.group(9), /*TODO rutaPrincipal*/"FOTO!!");
        }
        //AQUI
    }
}