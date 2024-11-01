package org.example;

import org.example.procesador.Cinta;
import org.example.procesador.TablaDePalabrasClave;
import org.example.procesador.Token;
import org.example.software.Datos.Conexion;
import org.example.utils.Mensaje;
import org.example.utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        Cinta cinta = new Cinta("Hola mundo");
        Token token = new Token(1, 1, "mundo");
        TablaDePalabrasClave tabla = new TablaDePalabrasClave();
        System.out.println( "esta en la tabla "+tabla.estaEnTPC("HELP").getToStr());
        System.out.println(token.getNombre()+ " atriu"+ token.getAtributo());
        //cinta.avanzar();
        //cinta.avanzar();


        // Obtener la instancia de la conexión
        Conexion conexion = Conexion.getInstancia();

        // Abrir la conexión
        conexion.abrirConexion();

        // Obtener el objeto Connection
        Connection conn = conexion.getConexion();

        if (conn != null) {
            try {
                // Crear un Statement
                Statement stmt = conn.createStatement();

                // Ejecutar una consulta
                ResultSet rs = stmt.executeQuery("SELECT * FROM persona");

                // Procesar los resultados
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("per_cod"));
                    System.out.println("Nombre: " + rs.getString("per_nom"));
                }

                // Cerrar el ResultSet y el Statement
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión
                conexion.cerrarConexion();
            }
        } else {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");
        }

        /*
        String[] headers = {"ID", "Name", "Age"};

        // Create the data for the table
        Object[][] data = {
                {1, "John Doe", 30},
                {2, "Jane Smith", 25},
                {3, "Mike Johnson", 40}
        };


        // Create the DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);

        // Define the title for the table
        String title = "User Information";

        // Call the dibujarTablaHtml function
        Mensaje mensaje = Utils.dibujarTablaHtml(tableModel, headers, title);

        // Create the JFrame
        JFrame frame = new JFrame("HTML Table Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the JEditorPane
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(mensaje.getData());
        editorPane.setEditable(false);

        // Add the JEditorPane to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Add the JScrollPane to the JFrame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);

         */



       // System.out.println("letras :"+ cinta.letra('7')+ "avansar:"  +" cabezera :" + cinta.caracterActual());
    }



}