package com.lol.lolpro.app.web;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by yaiza on 31/05/14.
 */
public class APIConnection{

    //TODO trucar la ruta BASE_URI en funcion del idioma... de forma que optenemos los campeones en su idioma ;)
    private static final String CERT_NAME = "lolcert.pem";
    private static final String BASE_URI = "https://euw.api.pvp.net/api/lol/static-data/euw/v1.2/champion?locale=es_ES&dataById=true&api_key=56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";
    private static final String API_KEY = "56b9dedb-45bf-42f1-ab0e-4af9c8e058a2";
    //TODO singleton para evitar validar muchos certificados
    //public static final APIConnection API_CONNECTION = ;
    //TODO sacar variables de certificado y eso

    SSLContext sslCont;

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
        //ToDo Implementar
        return true;
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
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

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
    public String connect2API(){
        String respuesta = null;
        try {
            URI uriConsulta = createURI();
            if(uriConsulta != null) {
                ConnectionResult resultado = new ConnectionResult(sslCont);
                resultado.doInBackground(uriConsulta);
                resultado.get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
//patron a parte y se le pasa ejjeje Yaiza