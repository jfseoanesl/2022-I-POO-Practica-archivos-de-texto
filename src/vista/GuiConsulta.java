/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import Excepciones.ExcepcionArchivo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.AireAcondicionado;
import modelo.ListaAiresCrud;

/**
 *
 * @author Jairo F
 */
public class GuiConsulta extends JDialog {

    private JPanel panelBusqueda, panelPrinicipal, panelOpciones;
    private JScrollPane panelTabla;
    private JLabel lbBuqueda;
    private JButton btnEliminar;
    private JTextField txtBusqueda;
    private JTable tablaRegistros;
    private DefaultTableModel modeloTabla;
    private String titulosTabla[] = {"No Serial", "Marca", "Potencia (btu)", "Tmp Minima"};
    private String datosTabla[][] = {null};
    private Container contenedor;
    private ListaAiresCrud modelo;

    public GuiConsulta(Frame frame, boolean bln) {
        super(frame, bln);
        this.modelo = new ListaAiresCrud();
        this.setTitle("Catalogo Aires - Consulta - Version 1.0");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.iniciarComponentes();
        this.setSize(500, 600);
        //this.pack();
        try {
            List<AireAcondicionado> lista = this.modelo.leer();
            this.actualizarTabla(lista);

        } catch (ExcepcionArchivo e) {
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void iniciarComponentes() {
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new FlowLayout());

        this.panelPrinicipal = new JPanel();
        this.panelPrinicipal.setLayout(new BorderLayout());

        this.iniciarPanelBusqueda();
        this.iniciarPanelTabla();
        this.iniciarPanelOpciones();

        this.panelPrinicipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.contenedor.add(this.panelPrinicipal);
    }

    private void iniciarPanelBusqueda() {

        this.panelBusqueda = new JPanel();
        this.panelBusqueda.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.lbBuqueda = new JLabel("No Serie: ");
        this.txtBusqueda = new JTextField(15);
        this.txtBusqueda.addKeyListener(new eventoFiltroBusqueda());

        this.panelBusqueda.add(this.lbBuqueda);
        this.panelBusqueda.add(this.txtBusqueda);

        this.panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.panelPrinicipal.add(this.panelBusqueda, BorderLayout.NORTH);

    }

    private void iniciarPanelTabla() {

        this.tablaRegistros = new JTable();
        this.modeloTabla = new DefaultTableModel(this.datosTabla, this.titulosTabla);
        this.tablaRegistros.setModel(this.modeloTabla);

        this.panelTabla = new JScrollPane(this.tablaRegistros);

        this.panelPrinicipal.add(this.panelTabla, BorderLayout.CENTER);

    }

    private void iniciarPanelOpciones() {
        this.panelOpciones = new JPanel();
        this.panelOpciones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        this.btnEliminar = new JButton("Eliminar");
        this.btnEliminar.addActionListener(new eventoClickEliminar());

        this.panelOpciones.add(this.btnEliminar);

        this.panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.panelPrinicipal.add(this.panelOpciones, BorderLayout.SOUTH);
    }

    public void actualizarTabla(List<AireAcondicionado> lista) {

        this.modeloTabla.setNumRows(0);

        for (AireAcondicionado a : lista) {
            String datos[] = {String.valueOf(a.getnSerial()), a.getMarca(), String.valueOf(a.getnCaballos()), String.valueOf(a.getTmpMinima())};
            this.modeloTabla.addRow(datos);
        }

    }

    private void filtrarDatos() {

        try {

            int serial;
            List<AireAcondicionado> lista;
            if (this.txtBusqueda.getText().isEmpty()) {
                serial = 0;
                lista=this.modelo.leer();
            } else {
                serial = Integer.valueOf(this.txtBusqueda.getText());
                lista = this.modelo.filtrar(serial);
            }
            this.actualizarTabla(lista);

        } catch (ExcepcionArchivo e) {
        }

    }
    
    private void eliminarDato(){
           
        int row = this.tablaRegistros.getSelectedRow();
        String serialEliminado = (String)this.modeloTabla.getValueAt(row, 0);
        
       
        try{
            AireAcondicionado eliminado = this.modelo.eliminar(new AireAcondicionado(Integer.valueOf(serialEliminado)));
            Mensaje.mostrar(this, "Confirmacion", "Registro eliminadi", JOptionPane.INFORMATION_MESSAGE);
            this.filtrarDatos();
            
        }catch(ExcepcionArchivo e){
            
        
        } 
    
    }

    class eventoFiltroBusqueda implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            filtrarDatos();
        }

    }
    
    class eventoClickEliminar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarDato();
        }
    
    
    }
}
