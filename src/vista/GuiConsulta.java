/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jairo F
 */
public class GuiConsulta extends JDialog{
    private JPanel panelBusqueda, panelPrinicipal, panelOpciones;
    private JScrollPane panelTabla;
    private JLabel lbBuqueda;
    private JButton btnEliminar;
    private JTextField txtBusqueda;
    private JTable tablaRegistros;
    private DefaultTableModel modeloTabla;
    private String titulosTabla[]={"No Serial", "Marca", "Potencia (btu)", "Tmp Minima"};
    private String datosTabla[][]={{"123456","Olimpo","1600", "16"}};
    private Container contenedor;

    public GuiConsulta(Frame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Catalogo Aires - Consulta - Version 1.0");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.iniciarComponentes();
        this.setSize(500, 600);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    
    private void iniciarComponentes(){
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new FlowLayout());
        
        this.panelPrinicipal= new JPanel();
        this.panelPrinicipal.setLayout(new BorderLayout());
        
        this.iniciarPanelBusqueda();
        this.iniciarPanelTabla();
        this.iniciarPanelOpciones();
        
        this.panelPrinicipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.contenedor.add(this.panelPrinicipal);
    }
    
    private void iniciarPanelBusqueda(){
        
        this.panelBusqueda=new JPanel();
        this.panelBusqueda.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        this.lbBuqueda=new JLabel("No Serie: ");
        this.txtBusqueda= new JTextField(15);
        
        this.panelBusqueda.add(this.lbBuqueda);
        this.panelBusqueda.add(this.txtBusqueda);
        
        this.panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrinicipal.add(this.panelBusqueda, BorderLayout.NORTH);
        
    }
    
    private void iniciarPanelTabla(){
        
        this.tablaRegistros = new JTable();
        this.modeloTabla = new DefaultTableModel(this.datosTabla, this.titulosTabla);
        this.tablaRegistros.setModel(this.modeloTabla);
        
        this.panelTabla = new JScrollPane(this.tablaRegistros);
        
        this.panelPrinicipal.add(this.panelTabla, BorderLayout.CENTER);
    
    }
    
    private void iniciarPanelOpciones(){
        this.panelOpciones= new JPanel();
        this.panelOpciones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        this.btnEliminar = new JButton("Eliminar");
        
        this.panelOpciones.add(this.btnEliminar);
        
        this.panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrinicipal.add(this.panelOpciones, BorderLayout.SOUTH);
    }

    
    
}
