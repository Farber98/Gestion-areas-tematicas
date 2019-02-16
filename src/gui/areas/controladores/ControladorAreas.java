/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.controladores;

import gui.areas.modelos.Areas;
import gui.areas.modelos.GestorAreas;
import gui.areas.modelos.ModeloTablaAreas;
import gui.areas.vistas.VentanaAreas;
import gui.interfaces.IControladorAMArea;
import gui.interfaces.IControladorAreas;
import gui.interfaces.IGestorAreas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author juan
 */
public class ControladorAreas implements IControladorAreas 
{
    private VentanaAreas vista;
    private int dameAreaSelec;
    
    
    public ControladorAreas(JFrame v) 
    {
        this.vista = new VentanaAreas(this, v); 
        this.vista.setLocationRelativeTo(null);     //Ubicada al medio de la pantalla.
        this.vista.setVisible(true);        //La hacemos visible
    }
    
    

    @Override
    public void btnBorrarClic(ActionEvent evt) 
    {
        GestorAreas gestorAreas = GestorAreas.instanciar();
        Areas unArea = this.dameAreaSeleccionada();
        if(unArea == null)          //No hay ningun area seleccionada.
            gestorAreas.cancelar();
        else                        //Hay un area seleccionada.
        {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea borrar el area?","Borrar area", JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION)
            {
                String resultadoOperacion = gestorAreas.borrarArea(unArea);     //Devolvemos el resultado del metodo borrarArea.
                if(!resultadoOperacion.equals(IGestorAreas.EXITO_BORRAR_AREA))  //Si el resultado no es exitoso, cancelamos la operacion.
                {
                    gestorAreas.cancelar();
                    JOptionPane.showMessageDialog(vista, "No se pudo borrar el area", "Error al borrar area", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
    }

    @Override
    public void btnBuscarClic(ActionEvent evt)
    {
        this.buscar();
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
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)   //Lo que hace getKeyCode() es devolver un numero entero. Si este numero entero coincide con el numero entero asignado a "Enter", entrara a la condicion. En otras palabras, al presionar enter se ejecuta el metodo buscar.
            this.buscar();
    }
    
    private void buscar()
    {
        String nombreArea;
        if(!this.vista.getTxtBuscar().getText().isEmpty())
        {
            nombreArea = this.vista.getTxtBuscar().getText();
            ModeloTablaAreas modeloTabla = new ModeloTablaAreas(nombreArea);
            JTable tabla = this.vista.getTablaAreas();
            tabla.setModel(modeloTabla);
        }
    }
    
    private void configurarTabla(JTable tablaAreas)
    {
        ModeloTablaAreas modeloTabla = new ModeloTablaAreas(null); 
        tablaAreas.setModel(modeloTabla);
    }
    
    private Areas dameAreaSeleccionada()
    {
        JTable tabla = this.vista.getTablaAreas();
        if(tabla.getSelectedRow() == -1)        //No hay ninguna fila seleccionada.
        {
            this.dameAreaSelec = -1;
            return null;
        }
        else        //Hay alguna fila seleccionada.
        {
            ModeloTablaAreas modeloTabla = (ModeloTablaAreas) tabla.getModel();
            this.dameAreaSelec = tabla.getSelectedRow();
            return modeloTabla.obtenerArea(tabla.getSelectedRow());
        }
    }
    
    
}
