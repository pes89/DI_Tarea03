/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tempo;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Pablo Esparza
 */
public class MensajePropertyEditorSupport extends PropertyEditorSupport{

    private MensajePanel mensajePanel = new MensajePanel();
    
    @Override
    public boolean supportsCustomEditor() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Component getCustomEditor() {
        return mensajePanel; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getJavaInitializationString() {
        Mensaje mensaje = mensajePanel.getValue();
        return "new tempo.Mensaje("+ mensaje.getAudio()+",\""+mensaje.getMensaje()+"\")"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValue() {
        return mensajePanel.getValue(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
