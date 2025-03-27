package Tester;

import usuarios.Asociacion;
import usuarios.Ciudadano;
import usuarios.Fundacion;
import usuarios.GestorUsuarios;
import usuarios.Usuario;

public class UsuariosTester {
    public static void main(String[] args) {
        GestorUsuarios gestor = new GestorUsuarios();

        try {
            Ciudadano juan = new Ciudadano("Juan Bravo", "1234", "01234567K");
            Ciudadano ana = new Ciudadano("Ana López", "5678", "01234567L");
            Ciudadano luisa = new Ciudadano("Luisa Gómez", "9012", "01234567G");
            Fundacion canal = new Fundacion("Canal Cif", "3456", "A1234567B");

            gestor.registrarUsuario(juan);
            gestor.registrarUsuario(ana);
            gestor.registrarUsuario(luisa);
            gestor.registrarUsuario(canal);

            Asociacion conservemos = new Asociacion("conservemos el manzanares", "7890", juan);
            Asociacion amigos = new Asociacion("amigos de los pájaros", "1234", ana);

            conservemos.agregarMiembro(amigos);
            conservemos.agregarMiembro(juan);
            amigos.agregarMiembro(ana);
            amigos.agregarMiembro(luisa);

            gestor.registrarUsuario(conservemos);
            gestor.registrarUsuario(amigos);

            for (Usuario usuario : gestor.obtenerTodosLosUsuarios()) {
                System.out.println(usuario);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}