package edu.aurora.oilchange.ui;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;

/**
 * Utility class for UI operations, including scaling GUI on QHD+ displays
 * and loading stylesheets.
 * The GUI is scaled when the resolution is greater than or equal to 2560x1440
 */
public class UiUtils {
    private final static int SCALE_2X_HORIZONTAL = 2560;
    private final static int SCALE_2X_VERTICAL = 1440;

    /**
     * Retrieve a loader object for the given view, applying scaling if applicable.
     * @param view the view to load, an FXML file
     * @return an FXMLLoader that can be used to load the given view
     */
    public static FXMLLoader getView(String view) {
        FXMLLoader loader;
        if (isQhd()) {
            loader = new FXMLLoader(UiUtils.class.getResource("qhd/" + view));
        } else {
            loader = new FXMLLoader(UiUtils.class.getResource("fhd/" + view));
        }
        return loader;
    }

    /**
     * Initializes a scene with needed stylesheets.
     * @param root the root node of the scene
     * @return a scene with stylesheets applied
     */
    public static Scene getScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(UiUtils.class.getResource("css/common.css").toExternalForm());
        if (isQhd()) {
            scene.getStylesheets().add(UiUtils.class.getResource("css/qhd.css").toExternalForm());
        }
        return scene;
    }

    /**
     * Determines if a display counts as 'QHD+', or a resolution of 2560x1440 or more.
     * @return true if this display is QHD+, false if it is not
     */
    private static boolean isQhd() {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        return bounds.getWidth() >= SCALE_2X_HORIZONTAL && bounds.getHeight() >= SCALE_2X_VERTICAL;
    }
}
