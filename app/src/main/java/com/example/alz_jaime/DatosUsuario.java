package com.example.alz_jaime;

public class DatosUsuario {
    public static String nombre = "Nombre";
    public static String apellidos = "Apellidos";
    public static String telefono = "Telefono";
    public static String edad = "Edad";
    public static String diagnostico = "Diagnostico";
    public static String contraseña = "contraseña";
    public static String correo = "correo";
    public static String historial = "historial";

    public static void setDatos(String nom, String ape, String tel, String ed, String diag,String cor, String con,String his){
        nombre = nom;
        apellidos = ape;
        telefono = tel;
        edad = ed;
        diagnostico = diag;
        correo = cor;
        contraseña = con;
        historial = his;
    }

    public static String getNombre(){return nombre;}
    public static String getApellidos(){return apellidos;}
    public static String getTelefono(){return telefono;}
    public static String getEdad(){return edad;}
    public static String getDiagnostico(){return diagnostico;}
    public static String getCorreo(){return correo;}
    public static String getContraseña(){return contraseña;}
    public static String getHistorial(){return historial;}

    public static void setContraseña(String pass){contraseña = pass;}
    public static void setCorreo(String mail){correo = mail;}
    public static void setHistorial(String histo){historial = histo + "   ";}
}
