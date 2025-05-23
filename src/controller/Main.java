package controller;

import view.Gui;

import java.sql.Connection;

import javafx.application.Application;
import model.SQL;

public class Main {
	static Connection connection;
	public static void main(String[] args) {
		SQL.connect(connection);
		Application.launch(Gui.class, args);

	}

}
