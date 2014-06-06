package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Activity principal en el que se colocarán todos los fragments
 */
public class Principal extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        Campeones.OnHeadlineSelectedListener, Inicio.OnHeadlineSelectedListener,
        Objetos.OnHeadlineSelectedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * Se encarga de inicializar el activity para que contenga lo necesario para poder dibujar los fragments
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Inicio())
                    .commit();
        }
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        this.deleteDatabase(this.getResources().getString(R.string.app_name));
        CargandoBBDD progressDialog = new CargandoBBDD(this);
        progressDialog.execute();
    }

    /**
     * Se encarga de la navegación a través de la barra de navegaciónd e su izquierda
     *
     * @param position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;
        switch (position) {
            case Constants.DRAWER_UNDEFINED:
                break;
            case Constants.DRAWER_INITIAL:
                fragment = new Inicio();
                break;
            case Constants.DRAWER_CHAMPION:
                fragment = new Campeones();
                break;
            case Constants.DRAWER_OBJECT:
                fragment = new Objetos();
                break;
            default:
                Toast.makeText(this, "Opcion no disponible!", Toast.LENGTH_SHORT).show();
                fragment = new Inicio();
                break;
        }
        if (fragment != null) {
            supportInvalidateOptionsMenu();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error ", "Mostrar Fragment " + position);
        }
    }

    /**
     * Se encarga de actualizar el título cuando pulsas otro ítem d e la barra de navegación
     *
     * @param number
     */
    public void updateTitle(int number) {
        mTitle = getResources().getStringArray(R.array.titulosMenuIzquierda)[number];
        mNavigationDrawerFragment.getList().setItemChecked(number, true);
        getActionBar().setTitle(mTitle);
    }

    /**
     * Se envarga de restaurar la barra de acción
     */
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * Se encarga de crear el menú de opciones
     *
     * @param menu menu que contendrá el menú de opciones
     * @return true si el menú ha podido ser creado, false en caso contrario
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.principal, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Acción al pulsar sobre un item de la barra settings
     *
     * @param item item seleccionado
     * @return true si la acción ha podido ser realizada, false en caso contrario
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_data_refresh || id == R.id.action_image_refresh) {
            this.deleteDatabase(this.getResources().getString(R.string.app_name));
            CargandoBBDD progressDialog = new CargandoBBDD(this);
            progressDialog.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Se encarga del manejo al seleccionar un campeón en campeones o en inicio
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onChampionSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new Champion();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Se encarga del manejo al seleccionar un objeto en objetos
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onObjectSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new ObjetoInfo();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
