package com.lol.lolpro.app.web;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by sergio on 31/05/14.
 */
public class ConnectionResult{

    private SSLContext sslCont;
    public ConnectionResult(SSLContext cont){
        sslCont = cont;
    }

    public String getHttpsResult(URI uri){
        try {
            // Tell the URLConnection to use a SocketFactory from our SSLContext
            URL url = uri.toURL();
            HttpsURLConnection urlConnection =
                    (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(sslCont.getSocketFactory());
            InputStream in = urlConnection.getInputStream();
            String line;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            urlConnection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            Log.e("error", "Error al conectarse a la API");
            //TODO comprobar si disponemos de conexion a internet
            e.printStackTrace();
        }
        return null;
    }
}
