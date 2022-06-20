/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Excepciones.ExcepcionArchivo;
import java.util.List;
import modelo.AireAcondicionado;
import modelo.ListaAiresCrud;

/**
 *
 * @author Jairo F
 */
public class Principal {

    public static void main(String[] args) {

        AireAcondicionado a = new AireAcondicionado(123, "Whirpool", 1200, 16.5);
        AireAcondicionado b = new AireAcondicionado(456, "Phillip", 1200, 12.5);
        AireAcondicionado c = new AireAcondicionado(789, "Samsung", 600, 18);

        ListaAiresCrud catalogo = new ListaAiresCrud();

        try {
            catalogo.registrarAire(a);
            catalogo.registrarAire(b);
            catalogo.registrarAire(c);
            System.out.println("Caso 1. Registro de datos");
        } catch (ExcepcionArchivo e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCaso 2: Lectura de datos");
        try {
            List<AireAcondicionado> lista = catalogo.leer();
            for(AireAcondicionado i:lista){
                System.out.println(i);
            }
        } catch (ExcepcionArchivo e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("\nCaso 3: Busqueda de no serial 123");
        try {
            AireAcondicionado buscado = catalogo.buscar(new AireAcondicionado(123));
            if(buscado!=null){
                    System.out.println("!! Resultado: encontrado ¡¡");
                    System.out.println(buscado);
            }
            else{
                System.out.println("!! Resultado: No encontrado ¡¡");
            }
        } catch (ExcepcionArchivo e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("\nCaso 4: Eliminar por no serial 883");
        try {
            AireAcondicionado eliminado = catalogo.eliminar(new AireAcondicionado(123));
            if(eliminado!=null){
                    System.out.println("!! Resultado: eliminado ¡¡");
                    System.out.println(eliminado);
            }
            else{
                System.out.println("!! Resultado: No encontrado ¡¡");
            }
        } catch (ExcepcionArchivo e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("\nCaso 3: Busqueda de no serial 123");
        try {
            AireAcondicionado buscado = catalogo.buscar(new AireAcondicionado(123));
            if(buscado!=null){
                    System.out.println("!! Resultado: encontrado ¡¡");
                    System.out.println(buscado);
            }
            else{
                System.out.println("!! Resultado: No encontrado ¡¡");
            }
        } catch (ExcepcionArchivo e) {
            System.out.println(e.getMessage());
        }
    }
}
