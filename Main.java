import java.util.Random;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main{
   public static void main(String[] args){
      // create a new window and play it
      Gui window = new Gui(new Game(4));
      return;
   }
}
