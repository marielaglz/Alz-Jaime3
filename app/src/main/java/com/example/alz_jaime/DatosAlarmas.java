package com.example.alz_jaime;

public class DatosAlarmas {
    public static String nommedi = "";
    public static String FullRegistro = "";
    public static int Hora;
    public static int Min;

    public static void setNommedi(String n){nommedi = n;}
    public static void setFullRegistro(String hora , String medicamento){
        FullRegistro += hora+ ": " + medicamento + "\n";
    }
    public static void setHora(int hora){Hora = hora;}
    public static void setMin(int min){Min = min;}

    public static String getNommedi(){return nommedi;}
    public static String getFullRegistro(){return  FullRegistro;}
    public static int getHora(){return Hora;}
    public static int getMin(){return Min;}
}
