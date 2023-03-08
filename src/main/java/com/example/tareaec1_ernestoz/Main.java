package com.example.tareaec1_ernestoz;

import javafx.application.Application;  //funciones importadas para interfaz y listas
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

    Button agregar, suma, resta, por, dividir;                          //botones para la interfaz
    int countPersona = 1;                                               //contador para cantidad de personas
    int num1, num2;                                                     //variables para hacer operaciones
    Label result = new Label();                                         //label para resultados y mensajes de error
    TextField txt1 = new TextField(); TextField txt2 = new TextField(); //campos de texto para nombre y edad
    ComboBox box1 = new ComboBox(); ComboBox box2 = new ComboBox(); ComboBox box3 = new ComboBox(); //drop down lists para provincias y personas
    List<String> persona1 = new ArrayList<>();                          //lista para colocar datos de la primera persona
    List<String> persona2 = new ArrayList<>();                          //lista para colocar datos de la segunda persona
    List<String> persona3 = new ArrayList<>();                          //lista para colocar datos de la tercera persona
    List<String> persona4 = new ArrayList<>();                          //lista para colocar datos de la cuarta persona

    public static void main(String[] args) { launch(args); }            //main() que ejecuta el codigo

    /**
     * crea la ventana, escena y organizacion de los elementos graficos.
     * tambien contiene las acciones que realizan los botones.
     * @param window ventana donde se ubica la interfaz
     */
    @Override
    public void start(Stage window) {

        window.setTitle("Calculadora");            //nombre de la ventana
        window.setResizable(false);                //ventana de tamano fijo

        GridPane layout = new GridPane();          //creacion de un grid para colocar elementos
        layout.setAlignment(Pos.CENTER);           //orientacion del grid respecto a la ventana
        layout.setHgap(10);                        //distancia horizontal entre elementos
        layout.setVgap(10);                        //distancia vertical entre elementos
        Scene scene = new Scene(layout, 500, 500); //crea la escena donde colocamos el grid y le damos un tamano

        agregar = new Button("+ Persona");         //dar texto al boton 'agregar'
        layout.add(agregar, 2, 8);                 //posicion del boton 'agregar'

        agregar.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que exista un nombre, provincia y numero para crear una nueva persona
             * activa los otros botones cuando se crean dos personas y se cierra al crear cuatro
             * @param event accion que ocurre al tocar el boton (agregar una persona)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(txt1.getText().trim().isEmpty()) && !(box1.getSelectionModel().isEmpty()) && !(txt2.getText().trim().isEmpty())) { //verifica que no hayan datos vacios

                    try {

                        Integer.parseInt(txt2.getText());       //verifica que edad sea un integer

                    } catch(NumberFormatException nfe) { result.setText("EDAD DEBE SER UN INTEGER"); }

                    if(Integer.parseInt(txt2.getText()) > 0) {  //verifica que edad sea mayor a cero (puede ser solo diferente a cero, es decision personal)

                        String prov = (String) box1.getValue(); //variable para almacenar la provincia seleccionada

                        if(countPersona == 1) {             //crea la primera persona

                            persona1.add(txt1.getText());   //agrega el nombre a la lista
                            persona1.add(prov);             //agrega la provincia a la lista
                            persona1.add((txt2.getText())); //agrega la edad a la lista
                            box2.getItems().add(persona1);  //agrega persona 1 como opcion
                            box3.getItems().add(persona1);  //agrega persona 1 como opcion

                        }

                        if(countPersona == 2) {             //crea la segunda persona

                            persona2.add(txt1.getText());   //agrega el nombre a lista
                            persona2.add(prov);             //agrega la provincia a la lista
                            persona2.add((txt2.getText())); //agrega la edad a la lista
                            box2.getItems().add(persona2);  //agrega la persona 2 como opcion
                            box3.getItems().add(persona2);  //agrega la persona 2 como opcion

                        }

                        if(countPersona == 3) {             //crea la tercera persona

                            persona3.add(txt1.getText());   //agrega el nombre a lista
                            persona3.add(prov);             //agrega la provincia a la lista
                            persona3.add((txt2.getText())); //agrega la edad a la lista
                            box2.getItems().add(persona3);  //agrega la persona 3 como opcion
                            box3.getItems().add(persona3);  //agrega la persona 3 como opcion

                        }

                        if(countPersona == 4) {             //crea la ultima persona

                            persona4.add(txt1.getText());   //agrega el nombre a la lista
                            persona4.add(prov);             //agrega la provincia a la lista
                            persona4.add((txt2.getText())); //agrega la edad a la lista
                            box2.getItems().add(persona4);  //agrega la persona 4 como opcion
                            box3.getItems().add(persona4);  //agrega la persona 4 como opcion

                        }

                        result.setText("PERSONA AGREGADA"); 
                        countPersona++;                     //incrementa el contador por 1

                        if(countPersona > 2) {              //verifica si se han creado 2 personas

                            suma.setDisable(false);         //abilita el boton de suma
                            resta.setDisable(false);        //abilita el boton de resta
                            por.setDisable(false);          //abilita el boton de multiplicar
                            dividir.setDisable(false);      //abilita el boton de dividir

                        } if(countPersona > 4) { agregar.setDisable(true); } //desabilita el boton cuando se crea la cantidad maxima de persona (4)

                    } else {result.setText("EDAD DEBE SER MAYOR A 0");}

                } else {result.setText("LLENAR TODOS LOS CAMPOS");}
            }
        });

        suma = new Button("+");  //da texto al boton de suma
        suma.setDisable(true);   //desabilita inicialmente el boton de suma
        layout.add(suma, 1, 13); //da posicion al boton de suma

        suma.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para sumarlas entre si
             * @param event accion que ocurre al tocar el boton (suma las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) { //verifica que ambas personas (operandos) se hayan elegido

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }      //para usar su edad como el primer numero
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }      //para usar su edad como el segundo numero
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 + num2));                                       //actualiza el resultado

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        resta = new Button("-");  //da texto al boton de resta
        resta.setDisable(true);   //desabilita el boton de resta inicialmente
        layout.add(resta, 1, 14); //da posicion al boton de resta

        resta.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para restarlas entre si
             * @param event accion que ocurre al tocar el boton (resta las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) { //verifica que ambas personas se eligieron

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }      //para usar su edad como segundo numero
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }      //para usar su edad como segundo numero
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 - num2));                                       //actualiza el resultado

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        por = new Button("x");  //da texto al boton de multiplicar
        por.setDisable(true);   //desabilita el boton de multiplicar inicialmente
        layout.add(por, 2, 13); //da posicion al boton de multiplicar

        por.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para multiplicarlas entre si
             * @param event accion que ocurre al tocar el boton (multiplica las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) { //verifica que ambas personas se eligieron

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }      //para usar su edad
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }      //para usar su edad
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 * num2));                                       //actualiza el resultado

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        dividir = new Button("/");  //da texto al boton de dividir
        dividir.setDisable(true);   //desactiva el boton de dividir inicialmente
        layout.add(dividir, 2, 14); //da posicion al boton de dividir

        dividir.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * verifica que se hayan seleccionado dos personas
             * obtiene las edades de las personas seleccionadas para dividirlas entre si
             * @param event accion que ocurre al tocar el boton (divide las edades)
             */
            @Override
            public void handle(ActionEvent event) {

                if( !(box2.getSelectionModel().isEmpty()) && !(box3.getSelectionModel().isEmpty()) ) { //verifica que se hayan elegido las dos personas

                    if(box2.getValue() == persona1) { num1 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box2.getValue() == persona2) { num1 = Integer.parseInt(persona2.get(2)); }      //para usar su edad
                    if(box2.getValue() == persona3) { num1 = Integer.parseInt(persona3.get(2)); }
                    if(box2.getValue() == persona4) { num1 = Integer.parseInt(persona4.get(2)); }

                    if(box3.getValue() == persona1) { num2 = Integer.parseInt(persona1.get(2)); }      //verifica cual persona se escoge
                    if(box3.getValue() == persona2) { num2 = Integer.parseInt(persona2.get(2)); }      //para usar su edad
                    if(box3.getValue() == persona3) { num2 = Integer.parseInt(persona3.get(2)); }
                    if(box3.getValue() == persona4) { num2 = Integer.parseInt(persona4.get(2)); }

                    result.setText(String.valueOf(num1 / num2));                                       //actualiza el resultado (no confirmado, posiblemente proaganda del PLP)

                } else { result.setText("Elegir personas 1 y 2"); }
            }
        });

        Label lab1 = new Label("Resultado:");               //crea labels con texto para guiar al usuario
        layout.add(lab1, 1, 1);                             //posiciona los labels en el grid
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

        layout.add(result, 2, 1);   //da posicion al label donde se obtiene el resultado
        layout.add(txt1, 2, 5);     //da posicion a la caja de texto del nombre
        layout.add(txt2, 2, 7);     //da posicion a la caja de texto de la edad

        layout.add(box1, 2, 6);             //da posicion al dropdown con las provincias
        box1.getItems().add("Alajuela");    //agrega las provincias como opciones en el dropdown
        box1.getItems().add("Cartago");
        box1.getItems().add("Guanacaste");
        box1.getItems().add("Heredia");
        box1.getItems().add("Limon");
        box1.getItems().add("Puntarenas");
        box1.getItems().add("San Jose");

        layout.add(box2, 2, 11);    //da posicion al dropdown para seleccionar una persona
        layout.add(box3, 2, 12);    //da posicion al dropdown para seleccionar otra persona

        window.setScene(scene);     //selecciona la escena de la ventana
        window.show();              //muestra la ventana

    }
}
