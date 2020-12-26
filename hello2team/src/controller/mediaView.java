package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class mediaView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img;

    @FXML
    private MediaView media;

   

    @FXML
    void play(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	Media media1 = new Media(getClass().getResource("../img/media.mp4").toString());
        
        MediaPlayer mediaPlayer = new MediaPlayer(media1);
        media.setMediaPlayer(mediaPlayer);
        
        mediaPlayer.setOnReady(new Runnable() {
          
          @Override
          public void run() {
             
            

             mediaPlayer.setAutoPlay(true);
          }
       });
     
            
          
         
        
       
       
    }
}
