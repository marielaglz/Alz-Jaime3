package com.example.alz_jaime;

public class BDCuidadores {
    String Nombre;
    String Direccion;
    String Telefono;

    public BDCuidadores(String nombre, String direccion, String telefono) {
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Telefono = telefono;
    }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }
    public String getDireccion() { return Direccion; }
    public void setDireccion(String direccion) { Direccion = direccion; }
    public String getTelefono() { return Telefono; }
    public void setTelefono(String telefono) { Telefono = telefono; }
}
