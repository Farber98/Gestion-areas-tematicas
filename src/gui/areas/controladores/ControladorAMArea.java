/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.controladores;

import gui.areas.modelos.GestorAreas;
import gui.areas.vistas.VentanaCrearArea;
import gui.interfaces.IControladorAMArea;
import gui.interfaces.IGestorAreas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

/**
 *
 * @author juan
 */
public class ControladorAMArea implements IControladorAMArea 
{
    private VentanaCrearArea vista;

    @Override
    public void btnCancelarClic(ActionEvent evt) 
    {
        IGestorAreas miGestorAreas = GestorAreas.instanciar();  //Llamamos al GestorAreas.
        miGestorAreas.cancelar();                               //La variable posicionUltimaArea toma el valor -1 al ser cancelada la operacion.
        this.vista.dispose();                                   //Se cierra la ventana
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) 
    {
        this.guardar();
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) 
    {
        char caracter = evt.getKeyChar();
        if(caracter == KeyEvent.VK_ENTER)       //Cuando se presione enter se guarda.
        {
            this.guardar();
        }
        
        //Chequear implementacion
    }

    public ControladorAMArea(JDialog parent) 
    {
        this.vista = new VentanaCrearArea(this, vista); //Se abre la ventana crear areas recibiendo parametros de la VentanaAreas
        this.vista.setLocationRelativeTo(null);         //La ubicamos al medio de la pantalla
        this.vista.setVisible(true);                    //La hacemos visible
            
    }
    
    private void guardar()
    {
        //Como implementamos este metodo
    }
    
    

    
    
 
    
}
