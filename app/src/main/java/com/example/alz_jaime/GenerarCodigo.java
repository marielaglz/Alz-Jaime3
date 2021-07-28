package com.example.alz_jaime;

public class GenerarCodigo {
    public static int Codigo1;
    public static int Codigo2;
    public static int Codigo3;
    public static int Codigo4;
    public static int Codigo5;
    public static String Correo;
    public static String code;

    public static void Generar() {
        Codigo1 = (int) (Math.random() * 10);
        Codigo2 = (int) (Math.random() * 10);
        Codigo3 = (int) (Math.random() * 10);
        Codigo4 = (int) (Math.random() * 10);
        Codigo5 = (int) (Math.random() * 10);
    }

    public static void setCorreo(String correo){Correo = correo;}

    public static int getCodigo1(){return Codigo1;}
    public static int getCodigo2(){return Codigo2;}
    public static int getCodigo3(){return Codigo3;}
    public static int getCodigo4(){return Codigo4;}
    public static int getCodigo5(){return Codigo5;}
    public static String getCorreo(){return Correo;}
    public static String getCode(){
        code = "";

        code += String.valueOf(Codigo1) + "  ";
        code += String.valueOf(Codigo2) + "  ";
        code += String.valueOf(Codigo3) + "  ";
        code += String.valueOf(Codigo4) + "  ";
        code += String.valueOf(Codigo5);

        return code;
    }
    public static String getCodeComprobacion(){
        String comprobado = "";

        comprobado += String.valueOf(Codigo1);
        comprobado += String.valueOf(Codigo2);
        comprobado += String.valueOf(Codigo3);
        comprobado += String.valueOf(Codigo4);
        comprobado += String.valueOf(Codigo5);

        return comprobado;
    }
}
