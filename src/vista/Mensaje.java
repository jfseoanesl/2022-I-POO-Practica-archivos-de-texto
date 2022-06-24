/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author JAIRO
 */
public class Mensaje {
    
    public static void mostrar(JDialog ventana, String titulo, String msg, int icono){
         JOptionPane.showMessageDialog(ventana, msg, titulo, icono);
    }
    
}
