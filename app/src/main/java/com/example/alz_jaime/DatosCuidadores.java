package com.example.alz_jaime;

public class DatosCuidadores {
    public static String Nombre = "Nombre";
    public static String Direccion = "Direccion";
    public static String Telefono = "Telefono";

    public static void setCuidador(String nombre, String direccion, String telefono){
        Nombre = nombre;
        Direccion = direccion;
        Telefono = telefono;
    }

    public static String getNombre(){return Nombre;}
    public static String getDireccion(){return Direccion;}
    public static String getTelefono(){return Telefono;}
}
