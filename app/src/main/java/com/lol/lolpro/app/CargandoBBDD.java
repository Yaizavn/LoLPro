package com.lol.lolpro.app;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.GridView;

import com.lol.lolpro.app.web.APIConnection;

/**
 * Clase que se encarga de la inicialización al cargar la aplicación de todos los datos necesarios
 */
public class CargandoBBDD extends AsyncTask<Void, Integer, Void> {

    ProgressDialog progress;
    ActionBarActivity contexto;
    APIConnection api;
    int accion = 3; //0 es no hacer nada, 1 es inicializar, 2 es actualizar

    /**
     * Constructor
     *
     * @param context Recibe el activity principal
     */
    public CargandoBBDD(ActionBarActivity context) {
        this.contexto = context;
        progress = new ProgressDialog(contexto);
    }

    /**
     * Tarea que se ejecuta de forma asíncrona y se encarga de coger los datos necesarios del api para tener los datos de la aplicación actualizados
     * @param voids null
     * @return null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        if (accion == 1) {
            inicializarBBDD();
        } else if (accion == 2) {
            //TODO deberiamos hacer esto en preexecute para que en caso de que no haya cambio de api ni de gratuitos no mostrara e progress dialog, pero no funciona porque no te deja hacer api.haCambiadoVersion o api.hacambiadoGratuitos si no es asincrono y preexecute no lo es
            if (api.haCambiadoVersion()) {
                actualizarBBDD();
            } else if (api.hanCambiadoGratuitos()) {
                actualizarGratuitos();
            }
        }
        //Spider sp= new Spider ();
        //noticias = sp.analizarURLs();
        return null;
    }

    /**
     * Se encarga de mostrar una barra de progrso para indicar al usuario como va la carga de datos
     */
    public void mostrarDialog() {
        progress.setTitle("Actualizando datos");
        progress.setMessage("Descarga en progreso");
        progress.setMax(100);
        progress.setProgress(0);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
    }

    /**
     * Se encarga de actualizar la barra de progreso
     *
     * @param integers integers[0] contiene el tanto por ciento al que se debe actualziar la barra de progreso
     */
    @Override
    protected void onProgressUpdate(Integer... integers) {
        progress.setProgress(integers[0]);
    }

    /**
     * Se encarga del tratamiento necesario antes de comenzar la tarea asíncrona, en este caso de indicarle a la tarea asíncrona si debe crear la base de datos o solo actualizarla
     */
    public void onPreExecute() {
        Boolean existeDb = false;
        String[] bbdds = contexto.databaseList();
        for (String bbdd:bbdds) {
            if (bbdd.compareTo(contexto.getResources().getString(R.string.app_name)) == 0) {
                existeDb = true;
            }
        }
        api = new APIConnection(contexto);
        if (!existeDb) {
            accion = 1;
        } else {
            accion = 2;
        }
        mostrarDialog();
    }

    /**
     * Se encarga del tratamiento neceasrio uan vez realizada la operación asíncrona
     *
     * @param unused null
     */
    public void onPostExecute(Void unused) {
        GridView grid = (GridView) contexto.findViewById(R.id.gridView);
        GridAdapter gA = (GridAdapter) grid.getAdapter();
        gA.refresh();
        progress.dismiss();
    }

    /**
     * Se encarga de indicar que tareas serían necesarias en caso de que la base de datos no esté creada
     */
    public void inicializarBBDD() {
        api.connect2API(APIConnection.IMAGES_AND_VERSIONS);
        this.publishProgress(25);
        api.connect2API(APIConnection.CHAMPIONS);
        this.publishProgress(50);
        api.connect2API(APIConnection.OBJECTS);
        this.publishProgress(75);
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }

    /**
     * Se encarga de indicar que tareas serían necesarias en caso de que la base de datos esté creada y sea neceasrio actualizarla ya que ha habido un cambio de versión
     */
    public void actualizarBBDD() {
        api.connect2API(APIConnection.UPDATE_CHAMPIONS);
        this.publishProgress(33);
        api.connect2API(APIConnection.UPDATE_OBJECTS);
        this.publishProgress(66);
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }

    /**
     * Se encarga de indicar las tareas neceasrias si solo hay que actualizar los campeones gratuitos semanales
     */
    public void actualizarGratuitos() {
        api.connect2API(APIConnection.CHAMPION_FREE);
        this.publishProgress(100);
    }
}
