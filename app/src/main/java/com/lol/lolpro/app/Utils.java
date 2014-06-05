package com.lol.lolpro.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

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

    /**
     * Se encarga de eliminar las etiquetas html de la descripción de un campeón
     *
     * @param description String con etiquetas html
     * @return String sin etiquetas html
     */
    public static String sanitizeItemDescription(String description) {
        return description.replaceAll("<br>", "\\\n").replaceAll("<.*?>", "");
    }
}
