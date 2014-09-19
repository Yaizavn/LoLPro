package com.lol.lolpro.app.bbdd;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.grids.GridAdapterFreeChamps;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;
import com.lol.lolpro.app.web.APIConnection;

/**
 * Clase que se encarga de la descarga de los datos
 */
public class DescargarBBDD extends AsyncTask<Void, Integer, Void> {

    ProgressDialog progress;
    ActionBarActivity contexto;
    APIConnection api;
    int accion = Constants.DB_DONOTHING;
    boolean hayNuevosGratuitos = false, hayNuevaVersion = false;

    /**
     * Constructor
     *
     * @param context Recibe el activity principal
     */
    public DescargarBBDD(ActionBarActivity context) {
        contexto = context;
        progress = new ProgressDialog(contexto);
    }

    /**
     * Se encarga del tratamiento necesario antes de comenzar la tarea asíncrona, en este caso de indicarle a la tarea asíncrona si debe crear la base de datos o solo actualizarla
     */
    public void onPreExecute() {
        if (!Utils.existsDB(contexto)) {
            accion = Constants.DB_DOWNLOAD;
        } else {
            accion = Constants.DB_UPDATE;
        }
        api = new APIConnection(contexto);
        inicializarDialog();
    }

    /**
     * Tarea que se ejecuta de forma asíncrona y se encarga de descargar los datos del api
     *
     * @param voids null
     * @return null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        if (accion == Constants.DB_DOWNLOAD) {
            inicializarBBDD();
        } else if (accion == Constants.DB_UPDATE) {
            hayNuevaVersion = api.hayNuevaVersion();
            if (hayNuevaVersion) {
                actualizarBBDD();
            }
        }
        hayNuevosGratuitos = api.hayNuevosGratuitos();
        this.publishProgress(100);
        return null;
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
     * Se encarga del tratamiento neceasrio una vez realizada la operación asíncrona
     *
     * @param unused null
     */
    public void onPostExecute(Void unused) {
        if (accion != Constants.DB_DONOTHING) {
            api.closeAPI();
            progress.dismiss();
            if (accion == Constants.DB_DOWNLOAD || hayNuevosGratuitos || hayNuevaVersion) {
                refreshUI();
            }
        }
    }

    /**
     * Se encarga de mostrar una barra de progreso para indicar al usuario como va la descarga de datos
     */
    public void inicializarDialog() {
        progress.setCanceledOnTouchOutside(false);
        progress.setTitle(contexto.getResources().getString(R.string.actualizando));
        progress.setMessage(contexto.getResources().getString(R.string.descargando));
        progress.setMax(100);
        progress.setProgress(0);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.show();
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
    }

    /**
     * Se encarga de indicar que tareas serían necesarias en caso de que la base de datos esté creada y sea neceasrio actualizarla ya que ha habido un cambio de versión
     */
    public void actualizarBBDD() {
        api.connect2API(APIConnection.UPDATE_CHAMPIONS);
        this.publishProgress(33);
        api.connect2API(APIConnection.UPDATE_OBJECTS);
        this.publishProgress(66);
    }

    private void refreshUI() {
        GridView grid = (GridView) contexto.findViewById(R.id.gridInicio);
        if (grid != null) {
            GridAdapterFreeChamps gA = (GridAdapterFreeChamps) grid.getAdapter();
            gA.refresh();
        }
    }
}
