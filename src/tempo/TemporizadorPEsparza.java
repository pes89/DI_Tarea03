/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package tempo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.EventListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;


/**
 *
 * @author Pablo Esparza
 */
public class TemporizadorPEsparza extends JPanel implements Serializable, ActionListener {
    
    //creo segundos, minutos y pausa temporales para reiniciarlos con las repeticiones
    private int segundos, tmpseg;
    private int minutos, tmpmin;
    private int pausa, tmppausa;
    private int repeticiones;
    //es una propiedad extendida con el mensaje final y si hay sonido o no
    private Mensaje mensaje;
          
    private Timer tiempo;
    private InicioCuentaAtrasListener inicioReceptor;
    private FinCuentaAtrasListener receptor;
    private Sonido sonido;
    
    //Los creo aqui para crear getter y setter y que el usuario pueda reeditarlos.
    private JLabel repeticionesLabel;
    private JLabel contadorLabel;
    private JPanel miPanel = new javax.swing.JPanel();
    
    
     
    
    /**
 *
 * @author Pablo Esparza
 * 
 * He creado eventos de inicio y de fin. 
 */
public class InicioCuentaAtrasEvent extends java.util.EventObject {
    // constructor
    public InicioCuentaAtrasEvent(Object source) {
        super(source);
        if(mensaje.getAudio()){
        // Lógica específica para el evento de inicio de cuenta atrás
        sonido = new Sonido("start.wav");
        }
        JOptionPane.showMessageDialog(null, "La cuenta atrás ha comenzado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}  

// Define la interfaz para el nuevo tipo de evento
public interface InicioCuentaAtrasListener extends EventListener {
    void capturarInicioCuentaAtras(InicioCuentaAtrasEvent ev);
}
    
public class FinCuentaAtrasEvent extends java.util.EventObject
{
    // constructor
    public FinCuentaAtrasEvent(Object source)
    {
        super(source);
        if(mensaje.getAudio()){
        sonido = new Sonido("fin.wav");
        }
        JOptionPane.showMessageDialog(null, mensaje.getMensaje(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
}

//Define la interfaz para el nuevo tipo de evento
public interface FinCuentaAtrasListener extends EventListener
{
    void capturarFinCuentaAtras(FinCuentaAtrasEvent ev);
}
    
   
    /**
     * Constructor sin argumentos. 
     * Por defecto 5 segundos 0 pausa y 1 repeticiones
     * iniciamos el mensaje. Se puede cambiar en propiedades
     */
    public TemporizadorPEsparza() {
  
        minutos=tmpmin=0;
        segundos=tmpseg=5;
        repeticiones=1;
        pausa=tmppausa=0;
        repeticionesLabel = new JLabel("Queda 1 repeticion");
        contadorLabel = new JLabel("");
        setMensaje(new Mensaje(true,"Mensaje fin"));       
        actualizarDiseño();
                
        tiempo = new Timer(1000,this);
    }
    
  
    
    //acciones que se ejecutan al iniciar el tiempo
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        //si hay una primera ejecución o más repeticiones
        if(repeticiones>=1){
            //si hay programada una pausa la hacemos primero
            if(pausa>0){                
                repeticionesLabel.setText("Quedan " + repeticiones + " repeticiones.");
                contadorLabel.setForeground(Color.red);
                contadorLabel.setText(Integer.toString(pausa) + " segundos de PAUSA");
                
                pausa--;
            } else{                
                //comienza cuenta atras
                segundos--;
                if (segundos < 0 && minutos > 0) {
                    segundos = 59;
                    minutos--;                
                }        
                if (segundos == 0 && minutos == 0 && repeticiones >= 0){
                    //reestablecer contadores y restar repeticiones
                    minutos = tmpmin;
                    segundos = tmpseg;
                    pausa = tmppausa;
                    repeticiones--; 
                }
                 contadorLabel.setForeground(Color.black);
                 contadorLabel.setText(Integer.toString(minutos) + " minutos "+ Integer.toString(segundos) + " segundos");
            }
        }
        //si ya no quedan repeticiones significa que ya hemos terminado
        else {
            contadorLabel.setText(getMensaje().getMensaje());
            setActivo(false);//FIN
            // Crea una instancia de FinCuentaAtrasListener para evitar NullPointerException
            FinCuentaAtrasListener finListener = new FinCuentaAtrasListener() {
            @Override
            public void capturarFinCuentaAtras(FinCuentaAtrasEvent ev) {                
            }
            };

            // Configura el receptor
            addFinCuentaAtrasListener(finListener);

            // Notifica el evento de fin
            receptor.capturarFinCuentaAtras(new FinCuentaAtrasEvent(this));
            
            
        }
        
        
    }
        
    //Gestiona si el temporizador está funcionado o no.
    public final void setActivo(boolean valor) {
        if (valor == true){
            tiempo.start();
            if (inicioReceptor != null) {
                inicioReceptor.capturarInicioCuentaAtras(new InicioCuentaAtrasEvent(this));
            }            
        }
        else            
            tiempo.stop();
    }
    public boolean getActivo() {
        return tiempo.isRunning();
    }
    
    public void addInicioCuentaAtrasListener(InicioCuentaAtrasListener inicioReceptor) {
        this.inicioReceptor = inicioReceptor;
    }

    public void removeInicioCuentaAtrasListener(InicioCuentaAtrasListener inicioReceptor) {
        this.inicioReceptor = null;
    }
    
    public void addFinCuentaAtrasListener(FinCuentaAtrasListener receptor)
    {
        this.receptor = receptor;
    }

    public void removeFinCuentaAtrasListener(FinCuentaAtrasListener receptor)
    {
        this.receptor=null;
    }
    
    //he creado un primer diseño con color, distribución y las etiquetas de repeticiones y el contador. El jPanel tiene getter y setter para poder reeditar    
     private void actualizarDiseño(){
        
               
               
        JSeparator jSeparator1 = new javax.swing.JSeparator();
        

        miPanel.setBackground(new java.awt.Color(255, 204, 153));
        miPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        miPanel.setForeground(new java.awt.Color(255, 204, 153));

        repeticionesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        repeticionesLabel.setForeground(new java.awt.Color(51, 51, 51));
        repeticionesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        

        jSeparator1.setForeground(new java.awt.Color(255, 102, 0));
        

        contadorLabel.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        contadorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(miPanel);
        miPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)                          
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(contadorLabel)
                                .addGap(10, 10, 10))//174                             
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(repeticionesLabel)
                                .addGap(10, 10, 10))))))//160
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)//26
                .addComponent(repeticionesLabel)
                .addGap(10, 10, 10)//18
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)                
                .addGap(10, 10, 10)//46
                .addComponent(contadorLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(miPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(miPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        
        
    
    }
    
     
    //getters y setters
     public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
        tmpseg=segundos;
    }

    public int getMinutos() {
        return minutos;        
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
        tmpmin=minutos;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public int getPausa() {
        return pausa;
    }

    public void setPausa(int pausa) {
        this.pausa = pausa;
        tmppausa=pausa;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public JLabel getRepeticionesLabel() {
        return repeticionesLabel;
    }

    public void setRepeticionesLabel(JLabel repeticionesLabel) {
        this.repeticionesLabel = repeticionesLabel;
    }

    public JLabel getContadorLabel() {
        return contadorLabel;
    }

    public void setContadorLabel(JLabel contadorLabel) {
        this.contadorLabel = contadorLabel;
    }

    public JPanel getMiPanel() {
        return miPanel;
    }

    public void setMiPanel(JPanel miPanel) {
        this.miPanel = miPanel;
    }  
}
