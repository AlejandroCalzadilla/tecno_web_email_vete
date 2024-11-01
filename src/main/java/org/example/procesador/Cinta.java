/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.procesador;

/**
 * Clase que representa una cinta de texto.
 * se instancia con un texto y se puede avanzar caracter por caracter.
 * la funcion caracterActual() retorna el caracter actual.
 * la funcion avanzar() mueve la cabecera al siguiente caracter.
 * la funcion init() reinicia la cabecera al inicio del texto.
 */
public class Cinta {


    public static final char FinDeArchivo = 0;
    public static final char SaltoDeLinea = 10;
    public static final char Espacio = 32;
    public static final char Tabulacion = 9;
    public static final char CR = 15;

    private int cabecera;
    private String texto;

    public Cinta(String texto) {
        this.texto = texto;
    }

    public void init() {
        this.cabecera = 0;
    }

    public char caracterActual() {
        if (this.cabecera == this.texto.length())
            return Cinta.FinDeArchivo;
        return this.texto.charAt(cabecera);
    }

    public void avanzar() {
        if (this.cabecera != this.texto.length())
            this.cabecera++;
    }

    public boolean espacio(char c) {
        return (c == Cinta.SaltoDeLinea || c == Cinta.Espacio || c == Cinta.Tabulacion || c == Cinta.CR);
    }

    public boolean digito(char c) {
        return (48 <= c && c <= 57);
    }

    public boolean letra(char c) {
        return ((65 <= c && c <= 90) || (97 <= c && c <= 122) );
    }
}
