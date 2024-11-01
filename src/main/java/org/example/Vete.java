package org.example;

import org.example.software.RecepcionadorMail;

import java.util.Scanner;

public class Vete {

    private boolean isOn;
    private RecepcionadorMail recepcionador;

    public Vete() {
        isOn = false; // Sistema de recepción de correos empieza apagado
        recepcionador = new RecepcionadorMail(); // Instancia el recepcionador de correos
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Bienvenido al sistema de Veterinaria.");
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Encender recepción de correos");
            System.out.println("2. Apagar recepción de correos");
            System.out.println("3. Procesar un mensaje de correo");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    encenderRecepcion();
                    break;
                case "2":
                    apagarRecepcion();
                    break;
                case "3":
                    procesarMensaje();
                    break;
                case "4":
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void encenderRecepcion() {
        if (!isOn) {
            isOn = true;
            System.out.println("La recepción de correos está encendida.");
        } else {
            System.out.println("La recepción de correos ya estaba encendida.");
        }
    }

    private void apagarRecepcion() {
        if (isOn) {
            isOn = false;
            System.out.println("La recepción de correos está apagada.");
        } else {
            System.out.println("La recepción de correos ya estaba apagada.");
        }
    }

    private void procesarMensaje() {
        if (!isOn) {
            System.out.println("El sistema de recepción de correos está apagado. Enciéndalo primero.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el mensaje del correo: ");
        String message = scanner.nextLine();

        if (message != null && !message.isEmpty()) {
            try {
                recepcionador.procesarMensaje(message);
                System.out.println("Mensaje procesado correctamente.");
            } catch (Exception e) {
                System.out.println("Error al procesar el mensaje: " + e.getMessage());
            }
        } else {
            System.out.println("No se ingresó ningún mensaje.");
        }
    }

    public static void main(String[] args) {
        Vete sistemaVeterinaria = new Vete();
        sistemaVeterinaria.iniciar();
    }
}
