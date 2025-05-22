package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Item;
import model.SQL;

public class Gui extends Application {
	static Connection connection;
	ComboBox<Item> typeComboBox;
	ComboBox<Item> brandComboBox;
	ComboBox<Item> drinkComboBox;
	ComboBox<String> columnComboBox;
	Integer drinkType;
	String drinkName;
	Integer brand;

	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:alcoholic_drinks.db");
		} catch (SQLException e) {
			System.out.println("No s'ha pogut establir la connexió amb la base de dades.");
			e.printStackTrace(System.err);
			System.exit(1);
		}
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		GridPane create = new GridPane();
		GridPane update = new GridPane();
		GridPane delete = new GridPane();
		Button btn1main = new Button();
		Button btn2main = new Button();
		Button btn3main = new Button();
		Button btn4main = new Button();
		Button createBtn = new Button();
		Button updateBtn = new Button();
		TextField tfCreate = new TextField();
		TextField tfUpdate = new TextField();
		Label nameLabel = new Label();
		Label updateLabel = new Label();
		btn1main.setText("Create a new drink");
		btn2main.setText("See all drinks");
		btn3main.setText("Update a drink");
		btn4main.setText("Delete a drink");
		pane.add(btn1main,0,0);
		pane.add(btn2main, 0, 1);
		pane.add(btn3main, 1, 0);
		pane.add(btn4main, 1, 1);
		Scene mainScene = new Scene(pane, 300, 250);
		Scene createScene = new Scene(create, 300, 250);
		Scene updateScene = new Scene(update, 300, 250);
		Scene deleteScene = new Scene(delete, 300, 250);
		btn1main.setOnAction(event ->{
			primaryStage.setScene(createScene);
			primaryStage.setTitle("Creation menu");		        				
		});
		btn3main.setOnAction(event ->{
			primaryStage.setScene(updateScene);
			primaryStage.setTitle("Update menu");		        				
		});
		btn4main.setOnAction(event ->{
			primaryStage.setScene(deleteScene);
			primaryStage.setTitle("Update menu");		        				
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
		columnComboBox = new ComboBox<>();
		columnComboBox.getItems().add(new String("Choose drink type"));
		columnComboBox.getItems().add(new String("name"));
		columnComboBox.getItems().add(new String("alcohol_content"));
		columnComboBox.getItems().add(new String("description"));
		columnComboBox.getItems().add(new String("volume"));
		columnComboBox.getItems().add(new String("price"));
		columnComboBox.getSelectionModel().selectFirst();
        try {
            ResultSet rs = getDrinkType();
            while (rs.next()) {
                typeComboBox.getItems().add(new Item(rs.getInt("type_id"),rs.getString("name")));
            }            
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        try {
        	ResultSet rs = getBrand();
            while (rs.next()) {
                brandComboBox.getItems().add(new Item(rs.getInt("brand_id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        try {
        	ResultSet rs = getDrink();
            while (rs.next()) {
                drinkComboBox.getItems().add(new Item(rs.getInt("drink_id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("No s'han pogut carregar els rols.");
        }
        
        createBtn.setText("Create the drink");
        nameLabel.setText("Name:");
        updateBtn.setText("Update the drink");
        updateLabel.setText("New value:");
        create.add(tfCreate, 1, 1);
        create.add(nameLabel, 0, 1);
        create.add(createBtn, 0, 2);
        create.add(brandComboBox, 0, 0);
        create.add(typeComboBox, 1, 0);
        update.add(drinkComboBox, 0, 0);
        update.add(columnComboBox, 1, 0);
        update.add(tfUpdate, 1, 1);
        update.add(updateLabel, 0, 1);
        update.add(updateBtn, 0, 2);
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
        typeComboBox.setOnAction(__ -> {
        	drinkType = typeComboBox.getValue().getId();         	
        });
        brandComboBox.setOnAction(__ -> {
        	brand = brandComboBox.getValue().getId();         	
        });
        
        

		primaryStage.setTitle("Main menu");
		primaryStage.setScene(mainScene);
		primaryStage.show();
        
        
        
	}
	public static ResultSet getDrinkType() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(5);
        return statement.executeQuery("SELECT type_id, name FROM drink_types");
    }
	public static ResultSet getBrand() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(5);
        return statement.executeQuery("SELECT brand_id, name FROM brands");
    }
	public static ResultSet getDrink() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(5);
        return statement.executeQuery("SELECT drink_id, name FROM drinks");
    }
}
