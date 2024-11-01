/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.procesador;

/*
* la clase Analex es la encargada de analizar el texto y devolver los tokens
*
* la funcion dt es la que se encarga de analizar el texto y devolver el token correspondiente
*
*
*
* */


public class Analex {

    private Cinta M;
    private Token R = new Token();
    private String AC = "";

    public Analex(Cinta cinta) {
        M = cinta;
            Init();
    }



    public void Init() {
        M.init();
        analizartexto();
    }



    private void analizartexto() {
        int Estado = 0;
        while (true) {
            char c = M.caracterActual();
            switch (Estado) {
                case 0:
                    if (M.letra(c)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 3;
                    } else if (c == ",".charAt(0)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 13;
                    } else if (c == "\"".charAt(0)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 5;
                    } else if (M.digito(c)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 7;
                    } else if (c == "_".charAt(0)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 15;
                    } else if (c == "[".charAt(0)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 9;
                    } else if (c == "]".charAt(0)) {
                        AC = String.valueOf(c);
                        M.avanzar();
                        Estado = 11;
                    } else if (M.espacio(c)) {
                        M.avanzar();
                    } else if (c == Cinta.FinDeArchivo) {
                        Estado = 1;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 1:
                    AC = "FinDeArchivo";
                    R = new Token(Token.FIN, -1, AC);
                    return;
                case 2:
                    R = new Token(Token.ERROR, -1, AC);
                    return;
                case 3:
                    if (M.letra(c)) {
                        AC += String.valueOf(c);
                        M.avanzar();
                    } else {
                        Estado = 4;
                    }
                    break;
                case 4:
                    Token token = TablaDePalabrasClave.estaEnTPC(AC);
                    if (token != null) {
                        R = token;
                        return;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 5:
                    if (c != Cinta.FinDeArchivo && c != Cinta.SaltoDeLinea && c != "\"".charAt(0)) {
                        AC += String.valueOf(c);
                        M.avanzar();
                    } else if (c == "\"".charAt(0)) {
                        AC += String.valueOf(c);
                        M.avanzar();
                        Estado = 6;
                    } else {
                        Estado = 2;
                    }
                    break;
                case 6:
                    R = new Token(Token.STRING, 1, AC);
                    return;
                case 7:
                    if (M.digito(c)) {
                        AC += String.valueOf(c);
                        M.avanzar();
                    } else {
                        Estado = 8;
                    }
                    break;
                case 8:
                    R = new Token(Token.NUM, Integer.parseInt(AC), AC);
                    return;
                case 9:
                    Estado = 10;
                    break;
                case 10:
                    R = new Token(Token.CA, -1, AC);
                    return;
                case 11:
                    Estado = 12;
                    break;
                case 12:
                    R = new Token(Token.CC, -1, AC);
                    return;
                case 13:
                    Estado = 14;
                    break;
                case 14:
                    R = new Token(Token.COMA, -1, AC);
                    return;
                case 15:
                    Estado = 16;
                    break;
                case 16:
                    R = new Token(Token.GB, -1, AC);
                    return;
                default:
                    break;
            }
        }
    }



    public Token Preanalisis() {
        return R;
    }

    public String Lexema() {
        return AC;
    }

    public void Avanzar() {
        analizartexto();
    }
}
