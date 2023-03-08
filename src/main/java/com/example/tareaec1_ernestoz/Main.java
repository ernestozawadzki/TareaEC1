package com.example.tareaec1_ernestoz;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para crear la aplicacion de calculadora
 * @author Ernesto Zawadzki Hernandez
 */
public class Main extends Application {

    Button agregar, suma, resta, por, dividir;
    int countPersona = 1;
    int num1, num2;
    Label result = new Label();
    TextField txt1 = new TextField(); TextField txt2 = new TextField();
    ComboBox box1 = new ComboBox(); ComboBox box2 = new ComboBox(); ComboBox box3 = new ComboBox();
    List<String> persona1 = new ArrayList<>();
    List<String> persona2 = new ArrayList<>();
    List<String> persona3 = new ArrayList<>();
    List<String> persona4 = new ArrayList<>();

    public static void main(String[] args) { launch(args); }

    /**
     * crea la ventana, escena y organizacion de los elementos graficos.
     * tambien contiene las acciones que realizan los botones.
     * @param window ventana donde se ubica la interfaz
     */
    @Override
    public void start(Stage window) {

        window.setTitle("Calculadora");
        window.setResizable(false);

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        Scene scene = new Scene(layout, 500, 500);

        agregar = new Button("+ Persona");
        layout.add(agregar, 2, 8);

        agregar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que exista un nombre, provincia y numero para crear una nueva persona
             * activa los otros botones cuando se crean dos personas y se cierra al crear cuatro
             * @param event accion que ocurre al tocar el boton (agregar una persona)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(txt1.getText().trim().isEmpty()) && !(box1.getSelectionModel().isEmpty()) && !(txt2.getText().trim().isEmpty())) {

                    try {

                        Integer.parseInt(txt2.getText());

                    } catch(NumberFormatException nfe) { result.setText("EDAD DEBE SER UN INTEGER"); }

                    if(Integer.parseInt(txt2.getText()) > 0) {

                        String prov = (String) box1.getValue();

                        if(countPersona == 1) {

                            persona1.add(txt1.getText());
                            persona1.add(prov);
                            persona1.add((txt2.getText()));
                            box2.getItems().add(persona1);
                            box3.getItems().add(persona1);

                        }

                        if(countPersona == 2) {

                            persona2.add(txt1.getText());
                            persona2.add(prov);
                            persona2.add((txt2.getText()));
                            box2.getItems().add(persona2);
                            box3.getItems().add(persona2);

                        }

                        if(countPersona == 3) {

                            persona3.add(txt1.getText());
                            persona3.add(prov);
                            persona3.add((txt2.getText()));
                            box2.getItems().add(persona3);
                            box3.getItems().add(persona3);

                        }

                        if(countPersona == 4) {

                            persona4.add(txt1.getText());
                            persona4.add(prov);
                            persona4.add((txt2.getText()));
                            box2.getItems().add(persona4);
                            box3.getItems().add(persona4);

                        }

                        result.setText("PERSONA AGREGADA");
                        countPersona++;

                        if(countPersona > 2) {

                            suma.setDisable(false);
                            resta.setDisable(false);
                            por.setDisable(false);
                            dividir.setDisable(false);

                        } if(countPersona > 4) { agregar.setDisable(true); }

                    } else {result.setText("EDAD DEBE SER MAYOR A 0");}

                } else {result.setText("LLENAR TODOS LOS CAMPOS");}
            }
        });

        suma = new Button("+");
        suma.setDisable(true);
        layout.add(suma, 1, 13);

        suma.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para sumarlas entre si
             * @param event accion que ocurre al tocar el boton (suma las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) {

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 + num2));

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        resta = new Button("-");
        resta.setDisable(true);
        layout.add(resta, 1, 14);

        resta.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para restarlas entre si
             * @param event accion que ocurre al tocar el boton (resta las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) {

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 - num2));

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        por = new Button("x");
        por.setDisable(true);
        layout.add(por, 2, 13);

        por.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para multiplicarlas entre si
             * @param event accion que ocurre al tocar el boton (multiplica las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) {

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 * num2));

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        dividir = new Button("/");
        dividir.setDisable(true);
        layout.add(dividir, 2, 14);

        dividir.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para dividirlas entre si
             * @param event accion que ocurre al tocar el boton (divide las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) {

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 / num2));

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        Label lab1 = new Label("Resultado:");
        layout.add(lab1, 1, 1);
        Label lab2 = new Label("AGREGAR PERSONAS");
        layout.add(lab2, 1, 3);
        Label lab3 = new Label("-----------------------");
        layout.add(lab3, 1, 4);
        Label lab4 = new Label("Nombre:");
        layout.add(lab4, 1, 5);
        Label lab5 = new Label("Provincia:");
        layout.add(lab5, 1, 6);
        Label lab6 = new Label("Edad:");
        layout.add(lab6, 1, 7);
        Label lab7 = new Label("OPERACIONES");
        layout.add(lab7, 1, 9);
        Label lab8 = new Label("-------------------");
        layout.add(lab8, 1, 10);
        Label lab9 = new Label("Persona 1:");
        layout.add(lab9, 1, 11);
        Label lab10 = new Label("Persona 2:");
        layout.add(lab10, 1, 12);
        Label gap = new Label("");
        layout.add(gap, 1, 2);

        layout.add(result, 2, 1);
        layout.add(txt1, 2, 5);
        layout.add(txt2, 2, 7);

        layout.add(box1, 2, 6);
        box1.getItems().add("Alajuela");
        box1.getItems().add("Cartago");
        box1.getItems().add("Guanacaste");
        box1.getItems().add("Heredia");
        box1.getItems().add("Limon");
        box1.getItems().add("Puntarenas");
        box1.getItems().add("San Jose");

        layout.add(box2, 2, 11);
        layout.add(box3, 2, 12);

        window.setScene(scene);
        window.show();

    }
}