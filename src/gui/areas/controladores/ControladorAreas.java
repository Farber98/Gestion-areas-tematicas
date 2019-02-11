/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.controladores;

import gui.areas.modelos.ModeloTablaAreas;
import gui.areas.vistas.VentanaAreas;
import gui.interfaces.IControladorAMArea;
import gui.interfaces.IControladorAreas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author juan
 */
public class ControladorAreas implements IControladorAreas 
{
    private VentanaAreas vista;
    
    
    public ControladorAreas(JFrame v) 
    {
        this.vista = new VentanaAreas(this, v); 
        this.vista.setLocationRelativeTo(null);     //Ubicada al medio de la pantalla.
        this.vista.setVisible(true);        //La hacemos visible
    }
    
    

    @Override
    public void btnBorrarClic(ActionEvent evt) 
    {
        //Como implementar metodo
    }

    @Override
    public void btnBuscarClic(ActionEvent evt)
    {
        //Como implementar metodo
    }

    @Override
    public void btnNuevaClic(ActionEvent evt) 
    {
        IControladorAMArea controlador = new ControladorAMArea(this.vista); //Cuando se presione crear, se abrira la VentanaCrearArea.
        //Solo esto?
    }

    @Override
    public void btnVolverClic(ActionEvent evt) 
    {
        this.vista.dispose();
        //Solo esto?
    } 

    @Override
    public void ventanaGanaFoco(WindowEvent evt) 
    {
        //Como implementar metodo
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) 
    {
        if(evt.getKeyChar() == KeyEvent.VK_ENTER)   //Cuando se presione se busca.
            this.buscar();
    }
    
    private void buscar()
    {
        String nombreArea;
        //Como implementar metodo
    }
    
    private void configurarTabla(JTable tablaAreas)
    {
//        ModeloTablaAreas modeloTabla = new ModeloTablaAreas(); //Que va en el parentesis?
//        tablaAreas.setModel(modeloTabla);
        //Chequear implementacion
    }
    
    
}
