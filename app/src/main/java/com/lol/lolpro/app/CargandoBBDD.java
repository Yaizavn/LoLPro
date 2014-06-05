package com.lol.lolpro.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.lol.lolpro.app.web.APIConnection;

public class CargandoBBDD extends AsyncTask<Void, Integer, Void> {

    ProgressDialog progress;
    ActionBarActivity contexto;
    APIConnection api;
    int accion=3; //0 es no hacer nada, 1 es inicializar, 2 es actualizar
    String[][] noticias;

    public CargandoBBDD(ActionBarActivity context) {
        this.contexto=context;
        progress = new ProgressDialog(contexto);
    }

    @Override
    protected Void doInBackground(Void...voids) {
        //realizar la operación aquí
        if (accion==1) {
            inicializarBBDD();
        } else if (accion==2) {
            //TODO deberiamos hacer esto en preexecute para que en caso de que no haya cambio de api ni de gratuitos no mostrara e progress dialog, pero no funciona porque no te deja hacer api.haCambiadoVersion o api.hacambiadoGratuitos si no es asincrono y preexecute no lo es
            if (api.haCambiadoVersion()) {
                actualizarBBDD();
            }
            else if (api.hanCambiadoGratuitos()){
                actualizarGratuitos();
            }
        }
        //Spider sp= new Spider ();
        //noticias = sp.analizarURLs();
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
        } else {
            accion=2;
        }
        mostrarDialog();
    }

    public void onPostExecute(Void unused) {
        GridView grid = (GridView) contexto.findViewById(R.id.gridView);
        GridAdapter gA= (GridAdapter) grid.getAdapter();
        gA.refresh();
        //ListView list = (ListView) contexto.findViewById(R.id.noticias);
        //((ListAdapter)list.getAdapter()).refresh();
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
        api.connect2API(APIConnection.UPDATE_CHAMPIONS);
        this.publishProgress(33);
        api.connect2API(APIConnection.UPDATE_OBJECTS);
        this.publishProgress(66);
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }

    public void actualizarGratuitos(){
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }
}
