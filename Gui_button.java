import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Graphics;

@SuppressWarnings("serial")
class Gui_button extends JButton{
   public Gui_button(int width, int height){
      this.setVisible(true);
      this.setSize(width, height);
   }
   public Gui_button(Dimension size){
      this.setVisible(true);
      this.setSize(size);
      this.setOpaque(false);
      this.setContentAreaFilled(false);
      this.setBorderPainted(false);
      this.setRolloverEnabled(false);
   }
   @Override
   public void paint(Graphics g){
   }
}
