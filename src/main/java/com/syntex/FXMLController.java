package com.syntex;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FXMLController implements Initializable {

    @FXML
    private TextArea txtUsecase;
    @FXML
    private ComboBox<String> comboRegion;
    @FXML
    private TextField txtBudget;
    @FXML
    private VBox boxPriorities, boxResults;
    @FXML
    private Slider sliderPerf, sliderAesth, sliderNovelty, sliderPower;
    @FXML
    private Button btnNext, btnStart, btnMutate, btnFeedback;
    @FXML
    private StackPane panePreview;
    @FXML
    private ListView<String> listParts;
    @FXML
    private Label lblPerf, lblAesth, lblNovelty, lblPower, lblCost, lblGeneration;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
