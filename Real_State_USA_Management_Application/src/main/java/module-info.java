module pt.ipp.isep.dei.esoft.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires AuthLib;
    requires org.apache.commons.lang3;
    requires java.desktop;
    requires java.sql;
    requires commons.math3;
    requires annotations;

    exports pt.ipp.isep.dei.esoft.project.ui;
    exports pt.ipp.isep.dei.esoft.project.application.controller.authorization;

    opens pt.ipp.isep.dei.esoft.project.application.controller.authorization;

    opens fxml to javafx.fxml;

    opens pt.ipp.isep.dei.esoft.project.application.controller;
    exports pt.ipp.isep.dei.esoft.project.application.controller;

    opens pt.ipp.isep.dei.esoft.project.ui.console to javafx.graphics;

    opens pt.ipp.isep.dei.esoft.project.ui.gui.controller;
    exports pt.ipp.isep.dei.esoft.project.ui.gui.controller;

    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.graphics;


}