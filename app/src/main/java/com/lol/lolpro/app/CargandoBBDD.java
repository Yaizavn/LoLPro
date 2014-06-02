package com.lol.lolpro.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.lol.lolpro.app.web.APIConnection;

public class CargandoBBDD extends AsyncTask<Void, Integer, Void> {

    ProgressDialog progress;
    Context contexto;
    APIConnection api;
    int accion=0; //0 es no hacer nada, 1 es inicializar, 2 es actualizar

    public CargandoBBDD(Context context) {
        this.contexto=context;
        progress = new ProgressDialog(contexto);
    }

    @Override
    protected Void doInBackground(Void...voids) {
        //realizar la operación aquí
        if (accion==1) {
            inicializarBBDD();
        } else if (accion==2) {
            actualizarBBDD();
        }
        else{
            //CAMPEONES EN OFERTA
        }
        return null;
    }

    public void mostrarDialog (){
        progress.setTitle("Actualizando datos");
        progress.setMessage("Descarga en progreso");
        progress.setMax(100);
        progress.setProgress(0);
        progress.setProgressStyle(progress.STYLE_HORIZONTAL);
        progress.show();
    }

    @Override
    protected void onProgressUpdate(Integer...integers){
        progress.setProgress(integers[0]);
    }

    public void onPreExecute() {
        Boolean existeDb = false;
        String[] bbdds = contexto.databaseList();
        int length =bbdds.length;
        for (int i=0; i<length; i++) {
            if (bbdds[i].compareTo(contexto.getResources().getString(R.string.app_name)) == 0) {
                existeDb=true;
            }
        }
        api = new APIConnection(contexto);
        if (!existeDb) {
            accion=1;
            mostrarDialog();
        } else {
            if (api.haCambiadoVersion()) {
                accion=2;
                mostrarDialog();
            }
        }
    }

    public void onPostExecute(Void unused) {
        progress.dismiss();
    }

    public void inicializarBBDD (){
        api.connect2API(APIConnection.IMAGES_AND_VERSIONS);
        this.publishProgress(25);
        api.connect2API(APIConnection.CHAMPIONS);
        this.publishProgress(50);
        api.connect2API(APIConnection.OBJECTS);
        this.publishProgress(75);
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }

    public void actualizarBBDD(){
        api.connect2API(APIConnection.CHAMPIONS);
        this.publishProgress(33);
        api.connect2API(APIConnection.OBJECTS);
        this.publishProgress(66);
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }
}
