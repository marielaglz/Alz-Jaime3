package com.example.alz_jaime;

public class DBUsuarios {
    String Nombres;
    String Apellidos;
    String Telefono;
    String Email;
    String Contrasena;
    String Edad;
    String Padecimiento;
    String Historial;

    public DBUsuarios(String nombres, String apellidos, String telefono, String edad, String email, String contrasena, String padecimiento, String historial) {
        this.Apellidos = apellidos;
        this.Nombres = nombres;
        this.Edad = edad;
        this.Telefono = telefono;
        this.Email = email;
        this.Contrasena = contrasena;
        this.Padecimiento = padecimiento;
        this.Historial = historial;
    }

    public String getNombres() {
        return Nombres;
    }
    public void setNombres(String nombres) {
        Nombres = nombres;
    }
    public String getApellidos() {
        return Apellidos;
    }
    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getContrasena() {
        return Contrasena;
    }
    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
    public String getEdad() {
        return Edad;
    }
    public void setEdad(String edad) {
        Edad = edad;
    }
    public String getPadecimiento() {
        return Padecimiento;
    }
    public void setPadecimiento(String padecimiento) {
        Padecimiento = padecimiento;
    }
    public String getHistorial() { return Historial; }
    public void setHistorial(String historial) { Historial = historial;}
}
