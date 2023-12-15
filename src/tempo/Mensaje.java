/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tempo;

import java.io.Serializable;

/**
 *
 * @author Pablo Esparza
 */
public class Mensaje implements Serializable {
    
    private Boolean audio;
    private String mensaje;

    public Mensaje(Boolean audio, String mensaje) {
        this.audio = audio;
        this.mensaje = mensaje;
    }

    public Boolean getAudio() {
        return audio;
    }

    public void setAudio(Boolean audio) {
        this.audio = audio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
