package edu.aurora.oilchange.ui;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.net.URL;

/**
 * Utility class for scaling GUI on QHD+ displays.
 * Scales when resolution is greater than or equal to 2560x1440
 */
public class DisplayScaler {
    private final static int SCALE_2X_HORIZONTAL = 2560;
    private final static int SCALE_2X_VERTICAL = 1440;

    /**
     * Determines if a display counts as 'QHD+', or a resolution of 2560x1440 or more.
     * @return true if this display is QHD+, false if it is not
     */
    public static boolean isQhd() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        return bounds.getWidth() >= SCALE_2X_HORIZONTAL && bounds.getHeight() >= SCALE_2X_VERTICAL;
    }

    /**
     * Returns the string corresponding to the current scaling type, determined by {@link #isQhd()}.
     * @return the scaling type as a string
     */
    public static String scaleName() {
        if (isQhd()) {
            return "qhd";
        } else {
            return "fhd";
        }
    }

    /**
     * Prefixes the given text with the scaling name
     * @param text a string of text
     * @return the text prefixed with the value from {@link #scaleName()}
     */
    public static String prefix(String text) {
        return scaleName() + text;
    }

    /**
     * Gets the path to this resource with the needed scale prefix as a directory.
     * @param name the resource to look for
     * @return the URL corresponding to the scaled resource
     */
    public static URL getScaledResource(String name) {
        return DisplayScaler.class.getResource(scaleName() + "/" + name);
    }
}
