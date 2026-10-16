
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class LoginWeb {
    private SimpleArrayDictionary<String, Usuario> usuarios = new SimpleArrayDictionary<>();
    private Scanner scanner = new Scanner(System.in);

    public LoginWeb() {
        usuarios.put("joaco", new Usuario("joaco", "1234"));
    }

    public void consolaLogin() {
        boolean salir = false;
        System.out.println("Bienvenido al sistema de login!!");
        System.out.println("Sus opciones son:");

        while (!salir) {
            System.out.println("---------------------------------");
            System.out.println("1. Registrarse");
            System.out.println("2. Ingresar");
            System.out.println("0. Salir");
            System.out.println("---------------------------------");

            System.out.print("Elija una opcion: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    registrar();
                    break;
                case "2":
                    ingresar();
                    break;
                case "0":
                    salir = true;
                    System.out.println("Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
    }

    public void registrar() {
        System.out.println("--------- Registro ---------");
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine().trim().toLowerCase();

        if (nombre.isEmpty() || nombre.contains(" ")) {
            System.out.println("El nombre no puede estar vacio ni tener espacios.");
            return;
        }

        if (usuarios.containsKey(nombre)) {
            System.out.println("El usuario '" + nombre + "' ya existe, elija otro nombre.");
            return;
        }

        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine().trim();

        if (contraseña.isEmpty() || contraseña.contains(" ")) {
            System.out.println("La contraseña no puede estar vacia ni tener espacios.");
            return;
        }

        System.out.print("Repita la contraseña: ");
        String repetida = scanner.nextLine().trim();

        if (!contraseña.equals(repetida)) {
            System.out.println("Las contraseñas no coinciden, no se creo la cuenta.");
            return;
        }

        usuarios.put(nombre, new Usuario(nombre, contraseña));
        System.out.println("Cuenta creada con exito! Ya puede ingresar.");
    }
    
    public void ingresar() {
        System.out.println("--------- Ingreso ---------");
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine().trim().toLowerCase();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        if (!usuarios.containsKey(nombre)) {
            System.out.println("El usuario '" + nombre + "' no existe.");
            return;
        }

        Usuario usuario = usuarios.get(nombre);

        if (usuario.estaBloqueado()) {
            System.out.println("La cuenta esta bloqueada por demasiados intentos fallidos.");
            return;
        }

        while (!usuario.estaBloqueado()) {
            System.out.print("Contraseña: ");
            String contraseña = scanner.nextLine().trim();

            if (contraseña.isEmpty()) {
                System.out.println("La contraseña no puede estar vacia.");
                continue;
            }

            if (usuario.verificarcontraseña(contraseña)) {
                System.out.println("Bienvenido, " + usuario.getNombre() + "!");
                return;
            }

            if (usuario.estaBloqueado()) {
                System.out.println("contraseña incorrecta. La cuenta fue bloqueada.");
            } else {
                System.out.println("Contraseña incorrecta. Le quedan "
                        + usuario.getIntentosRestantes() + " intento(s).");
            }
        }
    }
}
