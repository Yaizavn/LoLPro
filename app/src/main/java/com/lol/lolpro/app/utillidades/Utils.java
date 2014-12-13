package com.lol.lolpro.app.utillidades;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.lol.lolpro.app.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase de utilidades
 */
public class Utils {
    /**
     * Constructor vacío
     */
    private Utils() {
    }

    /**
     * Se encarga de convertir dp a píxeles
     *
     * @param context  Activity principal
     * @param dipValue Cantidad de dip a convertir
     * @return Píxeles a los que corresponde los dip
     */
    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    /**
     * Se encarga de eliminar las etiquetas html de la historia de un campeón
     *
     * @param description String con etiquetas html
     * @return String sin etiquetas html
     */
    public static String sanitizeChampStory(String description) {
        return description.replaceAll("<br>", "\\\n");
    }

    public static String clearQuotes(String description) {
        return description.replaceAll("\"", "");
    }

    public static String replaceVarsSpells(String description, ArrayList<ArrayList<String>> vars) {
        int i = vars.size();
        for (int j = 0; j < i; j++) {
            description = description.replaceAll("\\{\\{ " + vars.get(j).get(0) + " \\}\\}", vars.get(j).get(1));
        }
        description = description.replaceAll("\\(\\{\\{.+?\\}\\}\\)", " ");
        description = description.replaceAll("\\(\\+\\{\\{.+?\\}\\}%\\)", " ");
        description = description.replaceAll("\\(\\+\\{\\{.+?\\}\\}\\)", " ");
        description = description.replaceAll("\\{\\{.+?\\}\\}%", " ");
        description = description.replaceAll("\\{\\{.+?\\}\\}", " ");
        return description;
    }

    public static String sanitizeAttackSource(String value, String description, Context contexto) {
        DecimalFormat df = new DecimalFormat("###.##");
        if (description.equals("bonusattackdamage") || description.equals("attackdamage")) {
            String[] coeffsVariables = value.split("/");
            description = "";
            if (coeffsVariables.length == 1) {
                description = String.valueOf(df.format(Float.parseFloat(value) * 100));
            } else {
                int length = coeffsVariables.length - 1;
                for (int i = 0; i < length; i++) {
                    description += df.format(Float.parseFloat(coeffsVariables[i]) * 100) + "/";
                }
                description += df.format(Float.parseFloat(coeffsVariables[length]) * 100);
            }
            description += "% " + contexto.getResources().getString(R.string.bonus_attack_damage);
        } else if (description.equals("spelldamage")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_spell_damage);
        } else if (description.equals("health")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_health);
        } else if (description.equals("bonushealth")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_bonus_health);
        } else if (description.equals("mana")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_mana);
        } else if (description.equals("armor")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_armor);
        } else if (description.equals("spellblock")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_spell_block);
        } else if (description.equals("bonusarmor")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_bonus_armor);
        } else if (description.equals("bonusspellblock")) {
            description = df.format(Float.parseFloat(value) * 100) + "% " + contexto.getResources().getString(R.string.bonus_bonus_spell_block);
        } else if (description.equals("@stacks") || description.equals("@text") || description.equals("@special.nautilusq") ||
                description.equals("@special.dariusr3") || description.equals("@special.BraumWArmor")
                || description.equals("@special.BraumWMR") || description.equals("@cooldownchampion")
                || description.equals("@dynamic.abilitypower") || description.equals("@dynamic.attackdamage")) {
            description = value;
        } else if (description.equals("@special.viw")) {
            description = "1% por " + value + " Daño de Ataque adicional";
        } else if (description.equals("@special.jaxrarmor") || description.equals("@special.jaxrmr")) {
            description = "";
        }
        return description;
    }

    /**
     * Se encarga de eliminar las etiquetas html de la descripción de un campeón
     *
     * @param description String con etiquetas html
     * @return String sin etiquetas html
     */
    public static String sanitizeText(String description) {
        return description.replaceAll("<br>", "\\\n").replaceAll("<.*?>", "");
    }

    public static boolean existsDB(Context context) {
        String[] bbdds = context.databaseList();
        for (String bbdd : bbdds) {
            if (bbdd.equals(context.getResources().getString(R.string.app_name))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasInternetConnection(Context ctx) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = cm.getAllNetworkInfo();
        int longitud = redes.length;
        for (int i = 0; i < longitud && !isConnected; i++) {
            if (redes[i].getType() == ConnectivityManager.TYPE_WIFI || redes[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                isConnected = redes[i].getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return isConnected;
    }

    public static String htmlEncode(String text) {
        if (text == null) {
            return "";
        } else {
            return TextUtils.htmlEncode(text);
        }
    }
}
