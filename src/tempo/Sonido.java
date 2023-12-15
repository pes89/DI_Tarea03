/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tempo;

/**
 *
 * @author Pablo Esparza
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {
    
    public Sonido(String audio) {
        reproducirSonido("src/audio/" + audio);
        
    }   
    
    private void reproducirSonido(String audioFilePath) {
        try {
            // Obtén la ruta del archivo de audio
            
            File audioFile = new File(audioFilePath);

            // Crea un objeto AudioInputStream
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Obtiene un objeto Clip y abre el flujo de audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Reproduce el sonido
            System.out.println("Reproduciendo");
            clip.start();

            // Espera hasta que el sonido termine de reproducirse
            while (!clip.isRunning()) {
                Thread.sleep(10);
            }
            while (clip.isRunning()) {
                Thread.sleep(10);
            }

            // Cierra el Clip y el flujo de audio
            clip.close();
            audioInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   

}
