package vista;

import Excepciones.ExcepcionArchivo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.AireAcondicionado;
import modelo.ListaAiresCrud;

/**
 *
 * @author Jairo F
 */
public class GuiRegistro extends JDialog implements ActionListener {
    private JPanel panelDatos, panelOpciones, panelPrincipal, panelTmp;
    private JLabel lbSerial, lbMarca, lbCaballos, lbTmp;
    private JButton btnNuevo, btnGuardar, btnCancelar;
    private JTextField txtSerial;
    private JComboBox cmbMarca;
    private JRadioButton rb1, rb2;
    private ButtonGroup grupoOpciones;
    private JFormattedTextField txtTmp;
    private Container contenedor;
    private ListaAiresCrud modelo;

    public GuiRegistro(Frame frame, boolean bln) {
        super(frame, bln);
        this.modelo = new ListaAiresCrud();
        this.setTitle("Catalogo de aires - Registro - Version 1.0");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.iniciarComponentes();
        this.setSize(500, 300);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
       
    }
    
    
    
    private void iniciarComponentes(){
        
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new FlowLayout());
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BorderLayout());
        
        this.iniciarPanelDatos();
        this.iniciarPanelOpciones();
        
        this.panelPrincipal.setBorder(BorderFactory.createTitledBorder("Informacion del aire"));
        
        this.contenedor.add(this.panelPrincipal);
        
    
    }
    
    private void iniciarPanelDatos(){
        
        this.panelDatos = new JPanel();
        this.panelDatos.setLayout(new GridLayout(4,2,5,5));
        
        this.lbSerial= new JLabel("No Serial: ");
        this.lbMarca= new JLabel("Marca: ");
        this.lbCaballos= new JLabel("No caballos: ");
        this.lbTmp = new JLabel("Tmp Minima: ");
        
        this.txtSerial = new JTextField(15);
        
        String items[]={"LG","Hinday", "Olimpo"};
        this.cmbMarca= new JComboBox(items);
        this.cmbMarca.addItem("Samsung");
        
        this.txtTmp = new JFormattedTextField(new Integer(16));
        
        this.rb1=new JRadioButton("900 btu");
        this.rb1.setSelected(true);
        this.rb2=new JRadioButton("1200 btu");
        
        this.grupoOpciones = new ButtonGroup();
        this.grupoOpciones.add(rb1);
        this.grupoOpciones.add(rb2);
        
        this.panelDatos.add(this.lbSerial);
        this.panelDatos.add(this.txtSerial);
        
        this.panelDatos.add(this.lbMarca);
        this.panelDatos.add(this.cmbMarca);
        
        this.panelDatos.add(this.lbCaballos);
        this.panelTmp=new JPanel();
        this.panelTmp.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.panelTmp.add(this.rb1);
        this.panelTmp.add(this.rb2);
        this.panelDatos.add(this.panelTmp);
        
        this.panelDatos.add(this.lbTmp);
        this.panelDatos.add(this.txtTmp);
        
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrincipal.add(this.panelDatos, BorderLayout.CENTER);
    
    }
    
    private void iniciarPanelOpciones(){
        
        this.panelOpciones = new JPanel();
        this.panelOpciones.setLayout(new GridLayout(3,1,5,5));
        
        this.btnNuevo = new JButton("Nuevo");
        this.btnNuevo.addActionListener(this);
        
        this.btnGuardar = new JButton("Guardar");
        this.btnGuardar.addActionListener(this);
        
        this.btnCancelar= new JButton("Cancelar");
        this.btnCancelar.addActionListener(this);
        
        this.panelOpciones.add(this.btnNuevo);
        this.panelOpciones.add(this.btnGuardar);
        this.panelOpciones.add(this.btnCancelar);
        
        this.panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrincipal.add(this.panelOpciones, BorderLayout.EAST);
    
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.btnNuevo){
            //JOptionPane.showMessageDialog(this, "Click en boton nuevo", "Informe ActionListener", JOptionPane.INFORMATION_MESSAGE);
            Mensaje.mostrar(this, "Informe ActionListener", "Click en boton nuevo", ABORT);
        }
        
        if(e.getSource()==this.btnGuardar){
            //JOptionPane.showMessageDialog(this, "Click en boton Guardar", "Informe ActionListener", JOptionPane.ERROR_MESSAGE);
            this.guardarDatos();
        }
        
        if(e.getSource()==this.btnCancelar){
            this.limpiarCampos();
            JOptionPane.showMessageDialog(this, "Click en boton Cancelar", "Informe ActionListener", JOptionPane.QUESTION_MESSAGE);
        }
        
        
    }
    
    private void guardarDatos(){
        try{
            AireAcondicionado aire = this.leerDatos();
            this.modelo.registrarAire(aire);
            Mensaje.mostrar(this, "Exito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(ExcepcionArchivo e){
            Mensaje.mostrar(this, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }    
        
    }
    
    
    private AireAcondicionado leerDatos(){
        
        int serie = Integer.valueOf(this.txtSerial.getText());
        String marca = this.cmbMarca.getSelectedItem().toString();
        int caballos;
        if(this.rb1.isSelected()){
            caballos = 600;
        }
        else{
            caballos = 1200;
        }
        double tmp = (int)this.txtTmp.getValue();
                
        return new AireAcondicionado(serie, marca, caballos, tmp);
    
    }
    
    private void limpiarCampos(){
        this.txtSerial.setText(null);
    }
    
}
