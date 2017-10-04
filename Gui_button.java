import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
/*
 * PURPOSE: Make a button overriden class
 *          to make the Gui class not need
 *          repetitive button declarations
 */

@SuppressWarnings("serial")
class Gui_button extends JButton{
   // make it hidden.
   public Gui_button(Dimension size){
      this.setVisible(true);
      this.setSize(size);
      this.setOpaque(false);
      this.setContentAreaFilled(false);
      this.setBorderPainted(false);
      this.setRolloverEnabled(false);
   }
   @Override
   // make it very hidden.
   public void paintComponent(Graphics g){
   }
}
