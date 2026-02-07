package com.mobs;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DescriptionController {

    @FXML private ImageView mobImage;
    @FXML private Label mobName;
    @FXML private Label mobBehavior;

    @FXML private Label mobDimension;
    @FXML private Label mobSpawn;

    @FXML private Label mobHealth;
    @FXML private Label mobHealthHearts;

    @FXML private Label mobDamage;
    @FXML private Label mobDamageHearts;

    @FXML private Label mobSize;

    @FXML private Label mobDescription;

    private static Mob selectedMob;

    public static void setSelectedMobAndNavigate(Mob mob, @SuppressWarnings("exports") Scene currentScene) {
        selectedMob = mob;
        try {
            App.setRoot("description");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        if (selectedMob != null) {
            mobName.setText(selectedMob.getName());
            mobImage.setImage(new Image(selectedMob.getImageUrl()));
            mobBehavior.setText(selectedMob.getBehavior());

            mobDimension.setText(selectedMob.getDimension());
            mobSpawn.setText(selectedMob.getSpawn());

            mobHealth.setText(String.valueOf(selectedMob.getHealth()));
            mobHealthHearts.setText(selectedMob.getHealthHearts());

            mobDamage.setText(String.valueOf(selectedMob.getDamage()));
            mobDamageHearts.setText(selectedMob.getDamageHearts());

            mobSize.setText(selectedMob.getSizeDescription());

            mobDescription.setText(selectedMob.getDescription());
        }
    }

    @FXML
    private void goBack() throws Exception {
        App.setRoot("listpage");
    }
}