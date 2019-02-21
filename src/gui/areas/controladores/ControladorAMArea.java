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
    // <editor-fold defaultstate="collapsed" desc="VARIABLES DE INSTANCIA">

    private VentanaCrearArea vista;
    String tituloVentana = "Crear nueva area";

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="BOTONES Y TEXTFIELD">
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
        char validar = evt.getKeyChar();
        if(!Character.isLetter(validar))    
        {
            switch(validar)
            {
                case KeyEvent.VK_ENTER:
                    this.guardar();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                case KeyEvent.VK_DELETE:
                case KeyEvent.VK_SPACE:
                    break;
                default:
                    evt.consume();
            }
        }
    }
            
//            if( !(caracter == KeyEvent.VK_BACK_SPACE) && !(caracter == KeyEvent.VK_SPACE) && !(caracter == KeyEvent.VK_ENTER))
//            {
//                evt.consume();
//            }//Como hacer para que solo agarre letras, enter y backspace
//            if(caracter == KeyEvent.VK_ENTER)
//            {
//                this.guardar();
//            }
//            
//            if(Character.isDigit(caracter))
//            {//Por que sigue agarrando los numeros
//                evt.consume();
//            }
//        }
//        if(!( (Character.isAlphabetic(caracter) || caracter == KeyEvent.VK_BACK_SPACE || caracter == KeyEvent.VK_DELETE || caracter == KeyEvent.VK_SPACE)))        //Solo se aceptan caracteres alfabeticos.
//        {
//            evt.consume();
//            JOptionPane.showMessageDialog(vista, "Ingrese solo letras","Advertencia", JOptionPane.WARNING_MESSAGE);
//        }
//        if(caracter == KeyEvent.VK_ENTER)
//        {
//            this.guardar();
//        }
      
//        char caracter = evt.getKeyChar();
//        if(Character.isAlphabetic(caracter) || caracter == KeyEvent.VK_SPACE || caracter == KeyEvent.VK_BACK_SPACE || caracter == KeyEvent.VK_CLEAR || caracter == KeyEvent.VK_ENTER)        //Solo se aceptan caracteres alfabeticos.
//        {
//            
//            if (caracter == KeyEvent.VK_ENTER) //Cuando se presione enter se guarda.
//            {
//            this.guardar();
//            }
//        }
//        else        //En caso de no ser alfabetico, limpiamos el caracter y mostramos una advertencia.
//        {
//            evt.setKeyChar((char)KeyEvent.VK_CLEAR);       //Limpiamos el caracter
//            JOptionPane.showMessageDialog(vista, "Ingrese solo letras","Advertencia", JOptionPane.WARNING_MESSAGE);
//        }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public ControladorAMArea(JDialog parent) 
    {
        this.vista = new VentanaCrearArea(this, vista); //Se abre la VentanaCrearAreas recibiendo parametros de la VentanaAreas.
        this.vista.setLocationRelativeTo(null);         //La ubicamos al medio de la pantalla
        this.vista.setVisible(true);                    //La hacemos visible
        
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="METODOS">
    /**
     * Metodo utilizado para guardar una nueva area creada.
     */
    private void guardar() 
    {
        String opcion;
        String estado;
        String nombreArea;
        IGestorAreas miGestorAreas = GestorAreas.instanciar();
        nombreArea = this.vista.getTxtNombreArea().getText().trim();   //Tomamos el texto almacenado en el TextField de nuestra VentanaCrearArea. Eliminamos espacios en blanco al principio y al final con trim().
        estado = miGestorAreas.nuevaArea(nombreArea);            //Como nuevaArea devuelve una cadena de texto informando el estado de nuestra operacion, la guardamos en la variable estado.
        if (estado.equals(IGestorAreas.EXITO_NUEVA_AREA)) 
        {
            JOptionPane.showMessageDialog(vista, IGestorAreas.EXITO_NUEVA_AREA,tituloVentana, JOptionPane.INFORMATION_MESSAGE);
            this.vista.dispose();           //En caso de ser exitosa la operacion, la ventana se cierra porque el area fue creada.
        }
        else
            
        {
                
            if(estado.equals(IGestorAreas.ERROR_NUEVA_AREA_DUPLICADA)) 
            {
                miGestorAreas.cancelar();       //En caso de que haya un error en el guardado, la operacion se cancela. La variable posicionUltimaArea toma el valor -1.
                JOptionPane.showMessageDialog(vista, IGestorAreas.ERROR_NUEVA_AREA_DUPLICADA, tituloVentana, JOptionPane.ERROR_MESSAGE);      //En caso de que no se pueda crear un area porque este duplicada, lo especificamos.
            }
            
            if(estado.equals(IGestorAreas.ERROR_NUEVA_AREA_VACIA))
            {
                miGestorAreas.cancelar();
                JOptionPane.showMessageDialog(vista,IGestorAreas.ERROR_NUEVA_AREA_VACIA, tituloVentana,JOptionPane.ERROR_MESSAGE);          //En caso de que no se pueda crear un area porque el campo de texto este vacio, lo especificamos.
            }
            
            if( ! (estado.equals(IGestorAreas.ERROR_NUEVA_AREA_DUPLICADA) || estado.equals(IGestorAreas.ERROR_NUEVA_AREA_VACIA) ) )
            {
                miGestorAreas.cancelar();
                JOptionPane.showMessageDialog(vista,IGestorAreas.ERROR_NUEVA_AREA, tituloVentana,JOptionPane.ERROR_MESSAGE);                //En caso de que no se pueda crear un area y no se deba a campo vacio o a duplicada, mensaje por defecto.
            }
            
        }
    }
    

// </editor-fold>
    
}
