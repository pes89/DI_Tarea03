/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import tempo.Mensaje;
import tempo.TemporizadorPEsparza;

/**
 *
 * @author Pablo Esparza
 * 
 * Gestiona el funcionamiento de la vista (Prueba.java)
 */
public class Controlador implements ActionListener{
    Prueba ventana;
    private static JButton iniciarBtn, pararBtn;
    TemporizadorPEsparza miTemporizador = new tempo.TemporizadorPEsparza();
    JSpinner repeticionesTexto, pausaTexto, minutosTexto, segundosTexto;
    
    
    public static void main(String args[]) {
        new Controlador();        
    }  
    
    //constructor
    public Controlador(){
        ventana = new Prueba();
        ventana.setVisible(true);           
        iniciarBtn = ventana.getjButton1();
        iniciarBtn.addActionListener(this);  
        pararBtn = ventana.getjButton2();
        pararBtn.addActionListener(this);
        controlDatos();      
    }
     
     private void controlDatos(){
         //configuro los modelos de los spinner para limitar su minimo y máximo
        SpinnerNumberModel spinnerRepeticiones = new SpinnerNumberModel(1, 1, 10, 1);
        SpinnerNumberModel spinnerPausa = new SpinnerNumberModel(0, 0, 10, 1);
        SpinnerNumberModel spinnerMinutos = new SpinnerNumberModel(0, 0, 100, 1);
        SpinnerNumberModel spinnerSegundos = new SpinnerNumberModel(1, 1, 59, 1);
                 
        repeticionesTexto = ventana.getjSpinner1();        
        repeticionesTexto.setModel(spinnerRepeticiones);
        pausaTexto = ventana.getjSpinner2();
        pausaTexto.setModel(spinnerPausa);
        minutosTexto = ventana.getjSpinner3();
        minutosTexto.setModel(spinnerMinutos);
        segundosTexto = ventana.getjSpinner4();
        segundosTexto.setModel(spinnerSegundos);        
     }
     
    //control de los botones de inicio o parada 
   @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == iniciarBtn) {
            
            configurarTemporizador();
         
        }
        if (e.getSource() == pararBtn){
            miTemporizador = ventana.getTemporizadorPEsparza2();
            miTemporizador.setActivo(false);

        }
    }
    
    //recogemos los datos de la vista para iniciar el temporizador
    private void configurarTemporizador(){
        miTemporizador = ventana.getTemporizadorPEsparza2();
        // Crea una instancia de InicioCuentaAtrasListener para evitar NullPointerException
        TemporizadorPEsparza.InicioCuentaAtrasListener inicioListener = new TemporizadorPEsparza.InicioCuentaAtrasListener(){
            @Override
            public void capturarInicioCuentaAtras(TemporizadorPEsparza.InicioCuentaAtrasEvent ev) {
            }
            };
        miTemporizador.addInicioCuentaAtrasListener(inicioListener);
        
        miTemporizador.setRepeticiones(Integer.parseInt(repeticionesTexto.getValue().toString()));
        miTemporizador.setPausa(Integer.parseInt(pausaTexto.getValue().toString()));
        miTemporizador.setMinutos(Integer.parseInt(minutosTexto.getValue().toString()));
        miTemporizador.setSegundos(Integer.parseInt(segundosTexto.getValue().toString()));
        Mensaje mensaje = new Mensaje(ventana.getjToggleButton1().isSelected(),ventana.getjTextField5().getText());
        miTemporizador.setMensaje(mensaje);
        miTemporizador.setActivo(true);
    }

    
    
}
