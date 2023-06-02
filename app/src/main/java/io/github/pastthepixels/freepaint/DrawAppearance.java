package io.github.pastthepixels.freepaint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.preference.PreferenceManager;

import org.jetbrains.annotations.Nullable;

public class DrawAppearance {
    public int stroke = -1;
    public int fill = -1;
    public int strokeSize = 5;

    // Constructor with just stroke/fill
    public DrawAppearance(int stroke, int fill) {
        this.stroke = stroke;
        this.fill = fill;
    }

    // Constructor with every option
    public DrawAppearance(int stroke, int fill, int strokeSize) {
        this.strokeSize = strokeSize;
        this.stroke = stroke;
        this.fill = fill;
    }

    public void loadFromSettings(Context context) {
        this.stroke = PreferenceManager.getDefaultSharedPreferences(context).getInt("strokeColor", -1);
        this.fill = PreferenceManager.getDefaultSharedPreferences(context).getInt("fillColor", -1);
        this.strokeSize = (int) Float.parseFloat(PreferenceManager.getDefaultSharedPreferences(context).getString("strokeSize", "5"));
    }

    /*
     * Initialises a <code>Paint</code> with a default configuration.
     */
    public void initialisePaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeSize);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @SuppressLint("DefaultLocale")
    public static String colorToRGBA(int color) {
        return String.format("rgba(%d, %d, %d, %d)", Color.red(color), Color.green(color), Color.blue(color), Color.alpha(color));
    }

    public static String colorToHex(int color) {
        return "#" + Integer.toHexString(Color.rgb(Color.red(color), Color.green(color), Color.blue(color)));
    }

    public static float getColorAlpha(int color) {
        return (float)Color.alpha(color)/255f;
    }

    public DrawAppearance clone() {
        return new DrawAppearance(stroke, fill, strokeSize);
    }
}
