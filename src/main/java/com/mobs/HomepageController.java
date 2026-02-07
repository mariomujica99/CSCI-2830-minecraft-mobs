package com.mobs;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class HomepageController {
    @FXML
    private void goToListPage(ActionEvent event) throws Exception {
        App.setRoot("listpage");
    }
}