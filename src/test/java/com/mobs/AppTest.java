package com.mobs;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class AppTest {

    @Start
    void onStart(Stage stage) throws Exception {
        Application app = new App();
        app.start(stage);
        
    }
    @Test
    void shouldContainButton() {
        verifyThat("#viewMobsButton", hasText("View Mobs"));
    }

    @Test
    void shouldClickOnMob(FxRobot robot) {
        robot.clickOn("#viewMobsButton");

        Label allayMobLabel = robot.lookup((node) -> node instanceof Label && ((Label) node).getText().equals("Allay")).query();

        robot.clickOn(allayMobLabel.getParent());

        verifyThat("#mobName", hasText("Allay"));
    }
}