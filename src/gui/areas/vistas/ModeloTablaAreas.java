/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.vistas;

import gui.areas.modelos.Areas;
import gui.areas.modelos.GestorAreas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author juan
 */
public class ModeloTablaAreas extends AbstractTableModel 
{
    private GestorAreas ga = GestorAreas.instanciar();
    private List<String> columna = new ArrayList<>();
    
    public ModeloTablaAreas() 
    {
        this.columna.add("Area: ");
    }

    @Override
    public Object getValueAt(int fila, int columna) 
    {
        Areas unArea = this.ga.obtenerArea(fila);
        return unArea.getNombre();
    }
    
    

    
     @Override
    public int getRowCount() 
    {
        return this.ga.cantidadAreas();
    }

    @Override
    public int getColumnCount() 
    {
        return this.columna.size();         //NO ENTIENDO MUY BIEN. VER.
    }
    
    @Override
    public String getColumnName(int columna) 
    {
        return this.columna.get(columna);
    }  
    
    
}


