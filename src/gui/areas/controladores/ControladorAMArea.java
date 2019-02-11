/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.controladores;

import gui.interfaces.IControladorAMArea;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author juan
 */
public class ControladorAMArea implements IControladorAMArea 
{

    @Override
    public void btnCancelarClic(ActionEvent evt) 
    {
        dispose();
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) 
    {
        
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) 
    {
        
    }

    public ControladorAMArea() 
    {
        
    }

    
    
 
    
}
