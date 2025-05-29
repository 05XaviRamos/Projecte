package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Item;
import model.SQL;

public class Gui extends Application {
	ListView<String> drinkListView;
	ComboBox<String> tableComboBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		drinkListView = new ListView<>();
		Button updateBtn = new Button();
		Button deleteBtn = new Button();
		Button insertBtn = new Button();
		Label id = new Label("ID:");
		Label name = new Label("Name:");
		Label type_id = new Label("Type ID:");
		Label brand_id = new Label("Brand ID:");
		Label country_code = new Label("Country code:");
		Label alcohol_content = new Label("Alcohol content:");
		Label description = new Label("Description:");
		Label volume = new Label("Volume:");
		Label price = new Label("Price:");
		TextField idTf = new TextField();
		TextField nameTf = new TextField();
		TextField type_idTf = new TextField();
		TextField brand_idTf = new TextField();
		TextField country_codeTf = new TextField();
		TextField alcohol_contentTf = new TextField();
		TextField descriptionTf = new TextField();
		TextField volumeTf = new TextField();
		TextField priceTf = new TextField();

		double labelStartY = 350;
		double textFieldStartY = 350;

		id.setLayoutX(10);
		id.setLayoutY(labelStartY);
		idTf.setLayoutX(100);
		idTf.setLayoutY(textFieldStartY);

		name.setLayoutX(10);
		name.setLayoutY(labelStartY + 30);
		nameTf.setLayoutX(100);
		nameTf.setLayoutY(textFieldStartY + 30);

		type_id.setLayoutX(10);
		type_id.setLayoutY(labelStartY + 60);
		type_idTf.setLayoutX(100);
		type_idTf.setLayoutY(textFieldStartY + 60);

		brand_id.setLayoutX(10);
		brand_id.setLayoutY(labelStartY + 90);
		brand_idTf.setLayoutX(100);
		brand_idTf.setLayoutY(textFieldStartY + 90);

		country_code.setLayoutX(10);
		country_code.setLayoutY(labelStartY + 120);
		country_codeTf.setLayoutX(100);
		country_codeTf.setLayoutY(textFieldStartY + 120);

		alcohol_content.setLayoutX(250);
		alcohol_content.setLayoutY(labelStartY);
		alcohol_contentTf.setLayoutX(350);
		alcohol_contentTf.setLayoutY(textFieldStartY);

		description.setLayoutX(250);
		description.setLayoutY(labelStartY + 30);
		descriptionTf.setLayoutX(350);
		descriptionTf.setLayoutY(textFieldStartY + 30);

		volume.setLayoutX(250);
		volume.setLayoutY(labelStartY + 60);
		volumeTf.setLayoutX(350);
		volumeTf.setLayoutY(textFieldStartY + 60);

		price.setLayoutX(250);
		price.setLayoutY(labelStartY + 90);
		priceTf.setLayoutX(350);
		priceTf.setLayoutY(textFieldStartY + 90);


		tableComboBox = new ComboBox<>();
		tableComboBox.getItems().add(new String("Choose a table"));
		tableComboBox.getItems().add(new String("drinks"));
		tableComboBox.getItems().add(new String("drink_types"));
		tableComboBox.getItems().add(new String("brands"));
		tableComboBox.getItems().add(new String("countries"));
		tableComboBox.getSelectionModel().selectFirst();
		updateBtn.setText("Update");
		updateBtn.setLayoutX(675);
		updateBtn.setLayoutY(25);
		deleteBtn.setText("Delete");
		deleteBtn.setLayoutX(675);
		deleteBtn.setLayoutY(160);
		insertBtn.setText("Create");
		insertBtn.setLayoutX(675);
		insertBtn.setLayoutY(300);
		drinkListView.setLayoutY(25);
		Pane root = new Pane();
		drinkListView.setPrefWidth(600);
		drinkListView.setPrefHeight(300);
		root.getChildren().addAll(drinkListView, updateBtn, tableComboBox, deleteBtn, insertBtn, id, idTf, name, nameTf, type_id, type_idTf, brand_id, brand_idTf, country_code, country_codeTf, alcohol_content, alcohol_contentTf, description, descriptionTf, volume, volumeTf, price, priceTf);



		drinkListView.setOnMouseClicked(event -> {
			String selectedItem = drinkListView.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				// Dividir la cadena seleccionada por " | " para extraer los valores individuales
				String[] parts = selectedItem.split(" \\| ");

				// Asignar los valores a los TextField correspondientes
				if (tableComboBox.getValue().equals("brands")) {
					if (parts.length > 0) {
						idTf.setText(parts[0].trim());
					}
					if (parts.length > 1) {
						nameTf.setText(parts[1].trim());
					}
					if (parts.length > 2) {
						country_codeTf.setText(parts[2].trim());
					}
				} else {
					if (parts.length > 0) {
						idTf.setText(parts[0].trim());
					}
					if (parts.length > 1) {
						nameTf.setText(parts[1].trim());
					}
					if (parts.length > 2) {
						type_idTf.setText(parts[2].trim());
					}
					if (parts.length > 3) {
						brand_idTf.setText(parts[3].trim());
					}
					if (parts.length > 4) {
						country_codeTf.setText(parts[4].trim());
					}
					if (parts.length > 5) {
						alcohol_contentTf.setText(parts[5].trim());
					}
					if (parts.length > 6) {
						descriptionTf.setText(parts[6].trim());
					}
					if (parts.length > 7) {
						volumeTf.setText(parts[7].trim());
					}
					if (parts.length > 8) {
						priceTf.setText(parts[8].trim());
					}
				}

			}
		});

		Scene sc = new Scene(root, 800, 600);
		tableComboBox.setOnAction(__ -> {
			drinkListView.getItems().clear();
			ResultSet rs;
			try {
				rs = SQL.selectSql(tableComboBox.getValue());
				if (tableComboBox.getValue().equals("drinks")) {
					while (rs.next()) {
						drinkListView.getItems().add(rs.getInt("drink_id") + " | " + rs.getString("name") + " | " + rs.getInt("type_id") + " | " + rs.getInt("brand_id") + " | " + rs.getString("country_code") + " | " + rs.getDouble("alcohol_content") + " | " + rs.getString("description") + " | " + rs.getDouble("volume") + " | " + rs.getDouble("price"));
					}
					type_id.setVisible(true);
					type_idTf.setVisible(true);
					brand_id.setVisible(true);
					brand_idTf.setVisible(true);
					country_code.setVisible(true);
					country_codeTf.setVisible(true);
					alcohol_content.setVisible(true);
					alcohol_contentTf.setVisible(true);
					description.setVisible(true);
					descriptionTf.setVisible(true);
					volume.setVisible(true);
					volumeTf.setVisible(true);
					price.setVisible(true);
					priceTf.setVisible(true);
					country_code.setLayoutY(labelStartY + 120);
					country_codeTf.setLayoutY(textFieldStartY + 120);
				} else if (tableComboBox.getValue().equals("drink_types")) {
					while (rs.next()) {
						drinkListView.getItems().add(rs.getInt("type_id") + " | " + rs.getString("name"));
					}
					type_id.setVisible(false);
					type_idTf.setVisible(false);
					brand_id.setVisible(false);
					brand_idTf.setVisible(false);
					country_code.setVisible(false);
					country_codeTf.setVisible(false);
					alcohol_content.setVisible(false);
					alcohol_contentTf.setVisible(false);
					description.setVisible(false);
					descriptionTf.setVisible(false);
					volume.setVisible(false);
					volumeTf.setVisible(false);
					price.setVisible(false);
					priceTf.setVisible(false);
				} else if (tableComboBox.getValue().equals("brands")) {
					while (rs.next()) {
						drinkListView.getItems().add(rs.getInt("brand_id") + " | " + rs.getString("name") + " | " + rs.getString("country_code"));
					}
					type_id.setVisible(false);
					type_idTf.setVisible(false);
					brand_id.setVisible(false);
					brand_idTf.setVisible(false);
					country_code.setVisible(true);
					country_codeTf.setVisible(true);
					alcohol_content.setVisible(false);
					alcohol_contentTf.setVisible(false);
					description.setVisible(false);
					descriptionTf.setVisible(false);
					volume.setVisible(false);
					volumeTf.setVisible(false);
					price.setVisible(false);
					priceTf.setVisible(false);
					country_code.setLayoutY(type_id.getLayoutY());
					country_codeTf.setLayoutY(type_idTf.getLayoutY());
				} else if (tableComboBox.getValue().equals("countries")) {
					while (rs.next()) {
						drinkListView.getItems().add(rs.getString("country_code") + " | " + rs.getString("name"));
					}
					type_id.setVisible(false);
					type_idTf.setVisible(false);
					brand_id.setVisible(false);
					brand_idTf.setVisible(false);
					country_code.setVisible(false);
					country_codeTf.setVisible(false);
					alcohol_content.setVisible(false);
					alcohol_contentTf.setVisible(false);
					description.setVisible(false);
					descriptionTf.setVisible(false);
					volume.setVisible(false);
					volumeTf.setVisible(false);
					price.setVisible(false);
					priceTf.setVisible(false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		updateBtn.setOnAction(__ -> {
			if (tableComboBox.getValue().equals("drinks")) {
				try {
					SQL.updateSql(Integer.parseInt(type_idTf.getText()), Integer.parseInt(brand_idTf.getText()), country_codeTf.getText(), Double.parseDouble(alcohol_contentTf.getText()), descriptionTf.getText(), Double.parseDouble(volumeTf.getText()), Double.parseDouble(priceTf.getText()), nameTf.getText(), Integer.parseInt(idTf.getText()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (tableComboBox.getValue().equals("brands")) {
				try {
					SQL.updateSql(Integer.parseInt(idTf.getText()), tableComboBox.getValue(), nameTf.getText(), country_codeTf.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					SQL.updateSql(Integer.parseInt(idTf.getText()), tableComboBox.getValue(), nameTf.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		deleteBtn.setOnAction(__ -> {
			try {
				SQL.deleteSql(Integer.parseInt(idTf.getText()), tableComboBox.getValue());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		insertBtn.setOnAction(__ -> {
			if (tableComboBox.getValue().equals("drinks")) {
				try {
					SQL.insertSql(Integer.parseInt(type_idTf.getText()), Integer.parseInt(brand_idTf.getText()), country_codeTf.getText(), Double.parseDouble(alcohol_contentTf.getText()), descriptionTf.getText(), Double.parseDouble(volumeTf.getText()), Double.parseDouble(priceTf.getText()), nameTf.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (tableComboBox.getValue().equals("brands")) {
				try {
					SQL.insertSql(tableComboBox.getValue(), nameTf.getText(), country_codeTf.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					SQL.insertSql(tableComboBox.getValue(), nameTf.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		primaryStage.setTitle("Main menu");
		primaryStage.setScene(sc);
		primaryStage.show();



	}

}
