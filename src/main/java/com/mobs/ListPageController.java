package com.mobs;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
//import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ListPageController {

    @FXML private FlowPane mobListContainer;
    @FXML private ComboBox<String> sortComboBox;
    @FXML private ComboBox<String> filterComboBox;
    @FXML private ScrollPane scrollPane;

    private final List<Mob> mobs = MobsList.getAllMobs();
    private List<Mob> filteredMobs = new ArrayList<>(mobs);
    
    // Image caching for performance
    private final Map<String, Image> imageCache = new ConcurrentHashMap<>();
    private final ExecutorService imageLoadingExecutor = Executors.newFixedThreadPool(4);

    // Static variables to preserve state
    private static double savedVValue = 0.0;
    private static String savedFilter = "All";
    private static String savedSort = "Alphabetical";

    @FXML
    public void initialize() {
        filterComboBox.getItems().addAll("All", "Overworld", "The Nether", "The End");
        filterComboBox.setValue(savedFilter);

        sortComboBox.getItems().addAll("Alphabetical", "Damage", "Health", "Size");
        sortComboBox.setValue(savedSort);

        Platform.runLater(() -> {
            // Apply saved filter and sort
            handleFilterSelection();
            
            // Restore scroll position after a brief delay to ensure content is loaded
            Platform.runLater(() -> {
                if (scrollPane != null) {
                    scrollPane.setVvalue(savedVValue);
                }
            });
        });
    }

    @FXML
    private void handleFilterSelection() {
        String selectedDimension = filterComboBox.getValue();
        savedFilter = selectedDimension;

        if (selectedDimension == null || selectedDimension.equals("All")) {
            filteredMobs = new ArrayList<>(mobs);
        } else {
            filteredMobs = mobs.stream()
                .filter(mob -> mob.getDimension().equalsIgnoreCase(selectedDimension))
                .collect(Collectors.toList());
        }

        // Reset scroll position to top when filter changes
        if (scrollPane != null) {
            scrollPane.setVvalue(0.0);
        }

        handleSortSelection();
    }

    @FXML
    private void handleSortSelection() {
        String selectedSort = sortComboBox.getValue();
        if (selectedSort == null) return;
        
        savedSort = selectedSort;

        if (filteredMobs == null) {
            filteredMobs = new ArrayList<>(mobs);
        }

        List<Mob> sortedMobs = new ArrayList<>(filteredMobs);

        switch (selectedSort) {
            case "Alphabetical":
                sortedMobs.sort(Comparator.comparing(Mob::getName));
                break;
            case "Damage":
                sortedMobs.sort(Comparator.comparingInt(Mob::getDamage).reversed());
                break;
            case "Health":
                sortedMobs.sort(Comparator.comparingInt(Mob::getHealth).reversed());
                break;
            case "Size":
                sortedMobs.sort(Comparator.comparingDouble(Mob::getSize).reversed());
                break;
        }

        // Reset scroll position to top when sort changes
        if (scrollPane != null) {
            scrollPane.setVvalue(0.0);
        }

        populateMobList(sortedMobs);
    }

    private void populateMobList(List<Mob> mobList) {
        mobListContainer.getChildren().clear();

        for (Mob mob : mobList) {
            VBox entry = createMobEntry(mob);
            mobListContainer.getChildren().add(entry);
        }

        mobListContainer.prefWrapLengthProperty().bind(mobListContainer.widthProperty());
    }

    private VBox createMobEntry(Mob mob) {
        VBox entry = new VBox();
        entry.setPrefSize(200, 220);
        entry.setAlignment(Pos.TOP_CENTER);
        entry.setPadding(new Insets(10));
        entry.setStyle("-fx-border-color: lightgray; -fx-background-color: white; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Spacer above image
        Region spacerTop = new Region();
        VBox.setVgrow(spacerTop, Priority.ALWAYS);

        // Create image container with loading spinner
        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(125, 125);
        imageContainer.setMaxSize(125, 125);
        imageContainer.setMinSize(125, 125);

        // Modern loading spinner
        ProgressIndicator loadingSpinner = createModernSpinner();
        imageContainer.getChildren().add(loadingSpinner);

        // Load image asynchronously
        loadImageAsync(mob.getImageUrl(), imageContainer, loadingSpinner);

        // Spacer below image
        Region spacerBottom = new Region();
        VBox.setVgrow(spacerBottom, Priority.ALWAYS);

        // Label
        Label label = new Label(mob.getName());
        label.setWrapText(true);
        label.setPrefWidth(180);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-family: 'Minecrafter'; " +
                       "-fx-font-size: 12px; " +
                       "-fx-font-weight: bold; " +
                       "-fx-text-fill: #333333; " +
                       "-fx-padding: 5 0;");

        // Assemble card
        entry.getChildren().addAll(spacerTop, imageContainer, spacerBottom, label);
        entry.setOnMouseClicked(e -> {
            // Save scroll position before navigating
            if (scrollPane != null) {
                savedVValue = scrollPane.getVvalue();
            }
            DescriptionController.setSelectedMobAndNavigate(mob, mobListContainer.getScene());
        });

        return entry;
    }

    private ProgressIndicator createModernSpinner() {
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setPrefSize(40, 40);
        spinner.setMaxSize(40, 40);
        
        // Modern styling for the spinner
        spinner.setStyle(
            "-fx-progress-color: #4A90E2; " +
            "-fx-accent: #4A90E2; " +
            "-fx-control-inner-background: transparent;"
        );
        
        return spinner;
    }

    private void loadImageAsync(String imageUrl, StackPane container, ProgressIndicator spinner) {
        // Check cache first
        Image cachedImage = imageCache.get(imageUrl);
        if (cachedImage != null && !cachedImage.isError()) {
            Platform.runLater(() -> replaceSpinnerWithImage(container, spinner, cachedImage));
            return;
        }

        // Load image in background thread
        Task<Image> imageLoadTask = new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                // Load image with background loading enabled for better performance
                Image image = new Image(imageUrl, 125, 125, true, true, true);
                
                // Wait for image to load or fail
                while (image.getProgress() < 1.0 && !image.isError()) {
                    Thread.sleep(10);
                }
                
                if (!image.isError()) {
                    imageCache.put(imageUrl, image);
                }
                
                return image;
            }
        };

        imageLoadTask.setOnSucceeded(e -> {
            Image loadedImage = imageLoadTask.getValue();
            if (loadedImage != null && !loadedImage.isError()) {
                Platform.runLater(() -> replaceSpinnerWithImage(container, spinner, loadedImage));
            } else {
                Platform.runLater(() -> showErrorPlaceholder(container, spinner));
            }
        });

        imageLoadTask.setOnFailed(e -> {
            Platform.runLater(() -> showErrorPlaceholder(container, spinner));
        });

        imageLoadingExecutor.submit(imageLoadTask);
    }

    private void replaceSpinnerWithImage(StackPane container, ProgressIndicator spinner, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        
        // Fade in effect for smooth transition
        imageView.setOpacity(0);
        container.getChildren().remove(spinner);
        container.getChildren().add(imageView);
        
        // Simple fade in animation
        javafx.animation.FadeTransition fadeIn = new javafx.animation.FadeTransition(
            javafx.util.Duration.millis(300), imageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    private void showErrorPlaceholder(StackPane container, ProgressIndicator spinner) {
        Label errorLabel = new Label("Image\nUnavailable");
        errorLabel.setAlignment(Pos.CENTER);
        errorLabel.setStyle(
            "-fx-font-family: 'Minecrafter'; " +
            "-fx-font-size: 10px; " +
            "-fx-text-fill: #888888; " +
            "-fx-text-alignment: center;"
        );
        
        container.getChildren().remove(spinner);
        container.getChildren().add(errorLabel);
    }

    // Clean up resources when controller is destroyed
    public void cleanup() {
        if (imageLoadingExecutor != null && !imageLoadingExecutor.isShutdown()) {
            imageLoadingExecutor.shutdown();
        }
    }
}