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
import javafx.stage.Stage;
import model.Item;
import model.SQL;

public class Gui extends Application {
	ListView<String> drinkListView;
	ComboBox<Item> typeComboBox;
	ComboBox<Item> brandComboBox;
	ComboBox<Item> drinkComboBox;
	ComboBox<Item> deleteDrinkComboBox;
	ComboBox<Item> selectDrinkComboBox;
	ComboBox<String> columnComboBox;
	Integer drinkType;
	String drinkName;
	Integer brand;

	@Override
	public void start(Stage primaryStage) throws Exception {
		drinkListView = new ListView<>();
		GridPane pane = new GridPane();
		GridPane create = new GridPane();
		GridPane update = new GridPane();
		GridPane delete = new GridPane();
		GridPane select = new GridPane();
		Button btn1main = new Button();
		Button btn2main = new Button();
		Button btn3main = new Button();
		Button btn4main = new Button();
		Button createBtn = new Button();
		Button updateBtn = new Button();
		Button deleteBtn = new Button();
		Button createBack = new Button();
		Button updateBack = new Button();
		Button deleteBack = new Button();
		Button selectBack = new Button();
		TextField tfCreate = new TextField();
		TextField tfUpdate = new TextField();
		Label nameLabel = new Label();
		Label updateLabel = new Label();
		btn1main.setText("Create a new drink");
		btn2main.setText("See all drinks");
		btn3main.setText("Update a drink");
		btn4main.setText("Delete a drink");
		createBack.setText("Go back");
		updateBack.setText("Go back");
		deleteBack.setText("Go back");
		selectBack.setText("Go back");
		pane.add(btn1main,0,0);
		pane.add(btn2main, 0, 1);
		pane.add(btn3main, 1, 0);
		pane.add(btn4main, 1, 1);
		Scene mainScene = new Scene(pane, 300, 250);
		Scene createScene = new Scene(create, 300, 250);
		Scene updateScene = new Scene(update, 300, 250);
		Scene deleteScene = new Scene(delete, 300, 250);
		Scene selectScene = new Scene(select, 300, 250);		
		btn1main.setOnAction(event ->{
			primaryStage.setScene(createScene);
			primaryStage.setTitle("Creation menu");		        				
		});
		btn2main.setOnAction(event ->{
			primaryStage.setScene(selectScene);
			primaryStage.setTitle("View menu");		        				
		});
		btn3main.setOnAction(event ->{
			primaryStage.setScene(updateScene);
			primaryStage.setTitle("Update menu");		        				
		});
		btn4main.setOnAction(event ->{
			primaryStage.setScene(deleteScene);
			primaryStage.setTitle("Delete menu");		        				
		});
		createBack.setOnAction(event ->{
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(mainScene);		        				
		});
		updateBack.setOnAction(event ->{
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(mainScene);		        				
		});
		deleteBack.setOnAction(event ->{
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(mainScene);	        				
		});
		selectBack.setOnAction(event ->{
			primaryStage.setTitle("Main menu");
			primaryStage.setScene(mainScene);		        				
		});
		brandComboBox = new ComboBox<>();
		brandComboBox.getItems().add(new Item(null,"Choose Brand"));
		brandComboBox.getSelectionModel().selectFirst();
		typeComboBox = new ComboBox<>();
		typeComboBox.getItems().add(new Item(null,"Choose drink type"));
		typeComboBox.getSelectionModel().selectFirst();
		drinkComboBox = new ComboBox<>();
		drinkComboBox.getItems().add(new Item(null,"Choose drink"));
		drinkComboBox.getSelectionModel().selectFirst();
		deleteDrinkComboBox = new ComboBox<>();
		deleteDrinkComboBox.getItems().add(new Item(null,"Choose drink"));
		deleteDrinkComboBox.getSelectionModel().selectFirst();
		selectDrinkComboBox = new ComboBox<>();
		selectDrinkComboBox.getItems().add(new Item(null,"Choose drink"));
		selectDrinkComboBox.getSelectionModel().selectFirst();
		columnComboBox = new ComboBox<>();
		columnComboBox.getItems().add(new String("Choose a column"));
		columnComboBox.getItems().add(new String("name"));
		columnComboBox.getItems().add(new String("alcohol_content"));
		columnComboBox.getItems().add(new String("description"));
		columnComboBox.getItems().add(new String("volume"));
		columnComboBox.getItems().add(new String("price"));
		columnComboBox.getSelectionModel().selectFirst();
        try {
            ResultSet rs = SQL.getDrinkType();
            while (rs.next()) {
                typeComboBox.getItems().add(new Item(rs.getInt("type_id"),rs.getString("name")));
            }            
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        try {
        	ResultSet rs = SQL.getBrand();
            while (rs.next()) {
                brandComboBox.getItems().add(new Item(rs.getInt("brand_id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        try {
        	ResultSet rs = SQL.getDrink();
            while (rs.next()) {
                drinkComboBox.getItems().add(new Item(rs.getInt("drink_id"),rs.getString("name")));
                deleteDrinkComboBox.getItems().add(new Item(rs.getInt("drink_id"),rs.getString("name")));
                selectDrinkComboBox.getItems().add(new Item(rs.getInt("drink_id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        
        createBtn.setText("Create the drink");
        nameLabel.setText("Name:");
        updateBtn.setText("Update the drink");
        updateLabel.setText("New value:");
        deleteBtn.setText("Delete the drink");
        create.add(tfCreate, 1, 1);
        create.add(nameLabel, 0, 1);
        create.add(createBtn, 0, 2);
        create.add(brandComboBox, 0, 0);
        create.add(typeComboBox, 1, 0);
        create.add(createBack, 1, 2);
        update.add(drinkComboBox, 0, 0);
        update.add(columnComboBox, 1, 0);
        update.add(tfUpdate, 1, 1);
        update.add(updateLabel, 0, 1);
        update.add(updateBtn, 0, 2);
        update.add(updateBack, 1, 2);
        delete.add(deleteDrinkComboBox, 0, 0);
        delete.add(deleteBtn, 0, 1);
        delete.add(deleteBack, 1, 1);
        select.add(drinkListView, 0, 0);
        select.add(selectBack, 1, 1);
        select.add(selectDrinkComboBox, 0, 1);
        createBtn.setOnAction(__ -> {
        	drinkName = tfCreate.getText();        	
        	try {
				SQL.insertSql(drinkType, brand, drinkName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        });
        updateBtn.setOnAction(__ -> {    	
        	try {
				SQL.updateSql(drinkComboBox.getValue().getId(), tfUpdate.getText(), columnComboBox.getValue());
			} catch (SQLException e) {
				e.printStackTrace();
			}
        });
        deleteBtn.setOnAction(__ -> {    	
        	try {
				SQL.deleteSql(deleteDrinkComboBox.getValue().getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
        });
        typeComboBox.setOnAction(__ -> {
        	drinkType = typeComboBox.getValue().getId();         	
        });
        brandComboBox.setOnAction(__ -> {
        	brand = brandComboBox.getValue().getId();         	
        });
        drinkComboBox.setOnAction(__ -> {
        	if (selectDrinkComboBox.getValue().getId() == null) {
        		try {
        			drinkListView.getItems().clear();
                    ResultSet rs = SQL.selectSql();
                    while (rs.next()) {
                        String fullName = rs.getString("name");
                        String info = " | "+rs.getDouble("alcohol_content")+" | "+rs.getString("description")+" | " + rs.getDouble("volume");
                        drinkListView.getItems().add(fullName + info);
                    }
                } catch (SQLException e) {
                }
        	} else {
        		try {
        			drinkListView.getItems().clear();
                    ResultSet rs = SQL.selectSql(selectDrinkComboBox.getValue().getId());
                    while (rs.next()) {
                        String fullName = rs.getString("name");
                        String info = " | "+rs.getDouble("alcohol_content")+" | "+rs.getString("description")+" | " + rs.getDouble("volume");
                        drinkListView.getItems().add(fullName + info);
                    }
                } catch (SQLException e) {
                }
        	}
        });
        try {
			drinkListView.getItems().clear();
            ResultSet rs = SQL.selectSql();
            while (rs.next()) {
                String fullName = rs.getString("name");
                String info = " | "+rs.getDouble("alcohol_content")+" | "+rs.getString("description")+" | " + rs.getDouble("volume");
                drinkListView.getItems().add(fullName + info);
            }
        } catch (SQLException e) {
        }
        
        

		primaryStage.setTitle("Main menu");
		primaryStage.setScene(mainScene);
		primaryStage.show();
        
        
        
	}
	
}
