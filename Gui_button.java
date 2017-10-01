import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;

@SuppressWarnings("serial")
class Gui_button extends JButton{
   public Gui_button(Dimension size){
      this.setOpaque(false);
      this.setContentAreaFilled(false);
      this.setBorderPainted(false);
      this.setSize(size);
   }
}
