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
import javax.swing.JOptionPane;

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

    if(!(Character.isAlphabetic(caracter) ||  (caracter == KeyEvent.VK_BACK_SPACE)||  caracter == KeyEvent.VK_DELETE ))
        evt.consume();      //Estos eventos seran consumidos y no seran recibidos por el listener.
        
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
        String estado;
        String nombreArea;
        IGestorAreas miGestorAreas = GestorAreas.instanciar();
        nombreArea = this.vista.getTxtNombreArea().getText();   //Tomamos el texto almacenado en el TextField de nuestra VentanaCrearArea.
        estado = miGestorAreas.nuevaArea(nombreArea);            //Como nuevaArea devuelve una cadena de texto informando el estado de nuestra operacion, lo agarramos en la variable estado.
        if(estado.equals(IGestorAreas.EXITO_NUEVA_AREA))         
        {
            this.vista.dispose();           //En caso de ser exitosa la operacion, la ventana se cierra porque el area fue creada.
        }
        else
        {
            miGestorAreas.cancelar();       //En caso de que haya un error en el guardado, la operacion se cancela.
            JOptionPane.showMessageDialog(null,"Error: No se pudo crear el area.","Crear nueva area" , JOptionPane.ERROR_MESSAGE);      //Mostramos un mensaje de error con el icono de error en caso de que no se pueda crear el area nueva.
        
        }           
        //Chequear metodo.
    }
    
    

    
    
 
    
}
