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
    private List <Areas> areas;
    private List<String> columnas = new ArrayList<>();

    public ModeloTablaAreas(String nombreArea) 
    {
        this.columnas.add("Areas: ");               //Nombre de la unica columna de nuestra tabla.
        GestorAreas ga = GestorAreas.instanciar();  //Llamamos al gestor areas.
        this.areas = ga.buscarAreas(nombreArea);    //Busca el area con el nombre asignado.
    }

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
        Areas unArea = this.areas.get(fila);    //Buscamos la posicion de la fila.
        return unArea.getNombre();              //Devolvemos el nombre del area ubicada en esa fila.
    }
    
    public String getColumnName(int columna)
    {
        return this.columnas.get(columna);      //Obtenemos el nombre de la columna especificada.
    }
    
    public Areas obtenerArea(int fila)
    {
        return this.areas.get(fila);            //Obtenemos el nombre del area de la fila especificada.
    }
    
    
    
    
    
}
