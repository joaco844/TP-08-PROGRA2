/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class Usuario {
    private static final int MAX_INTENTOS = 3;

    private String nombre;
    private String contraseña;
    private int intentosFallidos = 0;

    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }


    public boolean verificarcontraseña(String intento) {
        if (contraseña.equals(intento)) {
            intentosFallidos = 0;   
            return true;
        }
        intentosFallidos++;
        return false;
    }

    public boolean estaBloqueado() {
        return intentosFallidos >= MAX_INTENTOS;
    }

    public int getIntentosRestantes() {
        return MAX_INTENTOS - intentosFallidos;
    }

    @Override
    public String toString() {
        return nombre + (estaBloqueado() ? " (bloqueado)" : "");
    }
}
