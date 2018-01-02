import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rohitha
 */
public class Music {

    //Player for mp3
    Player p;

    //File name
    String fileName;

    //Load the sound
    public void loadFile(String f) {
        //Store the file name
        String fileName = f;

        try {
            //Load mp3 file
            FileInputStream fis = new FileInputStream(new File(fileName));
            //Create the player to play this file
            p = new Player(fis);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Play the sound
    public void play() {
        try {
            //Play the sound file
            p.play();
        } catch (Exception e) {
            System.out.println(e);
        }
        //Load the file again
        loadFile("click.mp3");
    }
}
