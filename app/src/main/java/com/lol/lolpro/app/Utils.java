package com.lol.lolpro.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by sergio on 4/06/14.
 */
public class Utils {
    private Utils(){};
    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    public static String sanitizeDescription(String description) {
        return description.replaceAll("<br>","\\\n").replaceAll("<.*?>","").trim();
        /*Pattern patt = Patrones.PATTERN_SANITIZE_DESCRIPTION;
        Matcher match = patt.matcher(description);
        while (match.find()) {
            if(match.group().equals("<br>")){
                match.appendReplacement(result, "\\\n");
            }
        }
        match = patt.matcher(result.toString());
        return match.replaceAll("");*/
    }
}
