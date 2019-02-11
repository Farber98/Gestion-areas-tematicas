/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.controladores;

import gui.areas.vistas.VentanaAreas;
import gui.areas.vistas.VistaAreas;
import gui.interfaces.IControladorAreas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

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
        
    }

    @Override
    public void btnBuscarClic(ActionEvent evt)
    {
      
    }

    @Override
    public void btnNuevaClic(ActionEvent evt) 
    {
    
    }

    @Override
    public void btnVolverClic(ActionEvent evt) 
    {
        this.vista.dispose();
    } 

    @Override
    public void ventanaGanaFoco(WindowEvent evt) 
    {
        
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) 
    {
        
    }
    
    
}
