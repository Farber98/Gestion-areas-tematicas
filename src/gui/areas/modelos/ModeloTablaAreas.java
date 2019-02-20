/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author juan
 */
public class ModeloTablaAreas extends AbstractTableModel
{
    // <editor-fold defaultstate="collapsed" desc="VARIABLES DE INSTANCIA">

    private List<Area> areas;
    private List<String> columnas = new ArrayList<>();

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public ModeloTablaAreas(String nombreArea) 
    {
        this.columnas.add("Areas: ");               //Nombre de la unica columna de nuestra tabla.
        GestorAreas ga = GestorAreas.instanciar();  //Llamamos al gestor areas.
        this.areas = ga.buscarAreas(nombreArea);    //Busca el area con el nombre asignado.
    }

// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="METODOS">
    @Override
    public int getRowCount() 
    {
        return this.areas.size();       //Devuelve el numero de filas (El tamaño de la lista).
    }
    
    @Override
    public int getColumnCount() 
    {
        return this.columnas.size();    //Devuelve el numero de columnas.
    }
    
    @Override
    public Object getValueAt(int fila, int columna) 
    {
        Area unArea = this.areas.get(fila);            //Buscamos la posicion de la fila.
        return unArea.verNombre().toUpperCase();              //Devolvemos el nombre del area ubicada en esa fila. Todo en mayusculas para omitir errores de tipeo.
    }
    
    public String getColumnName(int columna) 
    {
        return this.columnas.get(columna);      //Obtenemos el nombre de la columna especificada.
    }
    
    /**
     * Metodo utilizado para obtener el nombre del area que se encuentra en la fila especificada.
     * @param fila - int para indicar la posicion de la fila.
     * @return 
     */
    public Area obtenerArea(int fila) 
    {
        return this.areas.get(fila);          
    }

// </editor-fold>
    
    
}
