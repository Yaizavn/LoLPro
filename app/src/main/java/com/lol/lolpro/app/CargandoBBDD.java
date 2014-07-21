package com.lol.lolpro.app;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.Toast;

import com.lol.lolpro.app.web.APIConnection;

/**
 * Clase que se encarga de la inicialización al cargar la aplicación de todos los datos necesarios
 */
public class CargandoBBDD extends AsyncTask<Void, Integer, Void> {

    ProgressDialog progress;
    ActionBarActivity contexto;
    APIConnection api;
    int accion = 0; //0 es no hacer nada, 1 es inicializar, 2 es actualizar

    /**
     * Constructor
     *
     * @param context Recibe el activity principal
     */
    public CargandoBBDD(ActionBarActivity context) {
        contexto = context;
        progress = new ProgressDialog(contexto);
    }

    /**
     * Tarea que se ejecuta de forma asíncrona y se encarga de coger los datos necesarios del api para tener los datos de la aplicación actualizados
     *
     * @param voids null
     * @return null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        if (accion == 1) {
            inicializarBBDD();
        } else if (accion == 2) {
            if (api.haCambiadoVersion()) {
                actualizarBBDD();
            } else if (api.hanCambiadoGratuitos()) {
                actualizarGratuitos();
            }
        }
        return null;
    }

    /**
     * Se encarga de mostrar una barra de progrso para indicar al usuario como va la carga de datos
     */
    public void mostrarDialog() {
        progress.setCanceledOnTouchOutside(false);
        progress.setTitle(contexto.getResources().getString(R.string.actualizando));
        progress.setMessage(contexto.getResources().getString(R.string.descargando));
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
        boolean existeDb = Utils.existsDB(contexto);
        boolean hasInternet = Utils.hasInternetConnection(contexto);
        if(hasInternet) {
            api = new APIConnection(contexto);
            if (!existeDb) {
                accion = 1;
            } else {
                accion = 2;
            }
            mostrarDialog();
        }
        else {
            Toast.makeText(contexto, contexto.getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Se encarga del tratamiento neceasrio una vez realizada la operación asíncrona
     *
     * @param unused null
     */
    public void onPostExecute(Void unused) {
        if(accion != 0) {
            api.closeAPI();
            GridView grid = (GridView) contexto.findViewById(R.id.gridView);
            GridAdapter gA = (GridAdapter) grid.getAdapter();
            gA.refresh();
            progress.dismiss();
        }
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
