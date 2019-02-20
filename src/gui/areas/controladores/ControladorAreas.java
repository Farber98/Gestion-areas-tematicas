package gui.areas.controladores;
import gui.areas.modelos.Area;
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
    // <editor-fold defaultstate="collapsed" desc="VARIABLES DE INSTANCIA">

    private VentanaAreas vista;
    private int dameAreaSelec;

// </editor-fold>   
    
    // <editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public ControladorAreas(JFrame v) 
    {
        this.vista = new VentanaAreas(this, v);        
        this.vista.setLocationRelativeTo(null);     //Ubicada al medio de la pantalla.
        this.vista.setVisible(true);                //La hacemos visible
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="BOTONES, TEXTFIELD, GAINED FOCUS">
    @Override
    public void btnBorrarClic(ActionEvent evt) 
    {
        GestorAreas gestorAreas = GestorAreas.instanciar();
        Area unArea = this.dameAreaSeleccionada();
        if (unArea == null)             //No hay ningun area seleccionada.
        {
            JOptionPane.showMessageDialog(vista,"Por favor seleccione un area","Borrar area", JOptionPane.ERROR_MESSAGE);
            gestorAreas.cancelar();     
        } 
        else                          //Hay un area seleccionada.
        {
            int opcion = JOptionPane.showConfirmDialog(vista, "¿Desea borrar el area?", "Borrar area", JOptionPane.YES_NO_OPTION);      //Ventana emergente para confirmar si se desea borrar un area.
            if (opcion == JOptionPane.YES_OPTION) 
            {
                String resultadoOperacion = gestorAreas.borrarArea(unArea);     //Devolvemos el resultado del metodo borrarArea. Cadena de texto con el resultado de la operacion.
                if (!resultadoOperacion.equals(IGestorAreas.EXITO_BORRAR_AREA)) //Si el resultado no es exitoso, cancelamos la operacion.
                {
                    gestorAreas.cancelar();
                    JOptionPane.showMessageDialog(vista, "No se pudo borrar el area", "Error al borrar area", JOptionPane.ERROR_MESSAGE);   //Ventana emergente para informar que hubo un error al borrar el area.
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
        
    }
    
    @Override
    public void btnVolverClic(ActionEvent evt)
    {
        this.vista.dispose();   //Cierra la ventana actual. Vuelve al menu principal.
    }    
    
    @Override
    public void ventanaGanaFoco(WindowEvent evt) 
    {
        JTable tabla = this.vista.getTablaAreas();
        this.configurarTabla(tabla);            //Instanciamos la tabla y la ajustamos mediante el metodo configurarTabla.
    }
    
    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) 
    {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) //Lo que hace getKeyCode() es devolver un numero entero. Si este numero entero coincide con el numero entero asignado a "Enter", entrara a la condicion. En otras palabras, al presionar enter se ejecuta el metodo buscar.
        {
            this.buscar();
        }
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="METODOS">
    
    /**
     * Metodo utilizado para buscar areas en nuestra tabla
     */
    private void buscar() 
    {
        String nombreArea;
        if (!this.vista.getTxtBuscar().getText().trim().isEmpty())      //Si el TextField no esta vacio.
        {
            nombreArea = this.vista.getTxtBuscar().getText().trim();    //La variable nombreArea almacena lo que se introdujo en el TextField.
            ModeloTablaAreas modeloTabla = new ModeloTablaAreas(nombreArea);
            JTable tabla = this.vista.getTablaAreas();
            tabla.setModel(modeloTabla);            
        } 
        else 
        {
            nombreArea = null;
        }
        ModeloTablaAreas modeloTabla = new ModeloTablaAreas(nombreArea);
        JTable tabla = this.vista.getTablaAreas();
        tabla.setModel(modeloTabla);
    }
    
    /**
     * Metodo utilizado para inicializar nuestra tabla.
     * @param tablaAreas 
     */
    private void configurarTabla(JTable tablaAreas) 
    {
        ModeloTablaAreas modeloTabla = new ModeloTablaAreas(null);      //Usamos el metodo para configurar la tabla.  
        tablaAreas.setModel(modeloTabla);
    }
    
    /**
     * Metodo utilizado para seleccionar el area que se desea borrar de la tabla.
     * @return Area - 
     */
    private Area dameAreaSeleccionada()         
    {
        JTable tabla = this.vista.getTablaAreas();
        if (tabla.getSelectedRow() == -1)       //No hay ninguna fila seleccionada.
        {
            this.dameAreaSelec = -1;
            return null;
        } else              //Hay alguna fila seleccionada.
        {
            ModeloTablaAreas modeloTabla = (ModeloTablaAreas) tabla.getModel();
            this.dameAreaSelec = tabla.getSelectedRow();
            return modeloTabla.obtenerArea(tabla.getSelectedRow());
        }
    }

// </editor-fold>
    
    
}
