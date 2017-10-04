import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import javax.swing.JPanel;

/*
 * PURPOSE: Renderer holds all of the Gui_tiles and renders them
 *          it requires game status updates to stay fully up to 
 *          date
 */

@SuppressWarnings("serial")
public class Renderer extends JPanel {
	
   // array of gui tiles
   private Gui_tile[] tile_arr;
   // tile dimensions
   private Dimension size;
   // empty_index is the linear index of the empty tile
   private int empty_index;
   private int width;
   private int height;
   private int buffer_size;
   private int complexity;
   private int num_moves;

   private boolean using_image;
   private BufferedImage[] img_arr;


   //Images
	
   /*           _     _ _      
    _ __  _   _| |__ | (_) ___ 
   | '_ \| | | | '_ \| | |/ __|
   | |_) | |_| | |_) | | | (__ 
   | .__/ \__,_|_.__/|_|_|\___|
   |_|                         */

	public Renderer(Game game, Dimension size, int buffer_size){
      if(game.get_width() == 4 && game.get_height() == 4){
         // we're using an image
         using_image = true;
         this.img_arr = new BufferedImage[16];
         // load the buffered images into the arr
         for(int i = 0; i < 16; i++){
                 try{
                    this.img_arr[i] = ImageIO.read(new FileInputStream(new File("assets/img" + i + ".jpg")));
                 }catch(IOException e){
                    System.out.printf("failed\n");

                 }

         }
      }
      else{
         using_image = false;
      }

      this.size = size;
      this.buffer_size = buffer_size;
      this.tile_arr = new Gui_tile[game.get_board().num_tiles()];
      this.width = game.get_width();
      this.height = game.get_height();
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], this.size, this.buffer_size);
         if(tiles[i].index() == (this.width*this.height-1)){
            // empty tile
            this.empty_index = i;
         }
      }
      this.complexity = game.get_complexity();
      this.num_moves = game.get_num_moves();
	}
   public void update_game_state(Game game){
      this.tile_arr = new Gui_tile[game.get_board().num_tiles()];
      Tile[] tiles = game.get_tiles();
      for(int i = 0; i < tiles.length; i++){
         this.tile_arr[i] = new Gui_tile(tiles[i], this.size, this.buffer_size);
         if(tiles[i].index() == (this.width*this.height-1)){
            // track empty tile
            this.empty_index = i;
         }
      }
      this.complexity = game.get_complexity();
      this.num_moves = game.get_num_moves();
   }
   public void move_tile(Direction.dir dir){
      Gui_tile tile = this.get_adjacent_tile(Direction.invert(dir));
      if(tile == null) return;
      tile.move(dir);
      return;
   }

   /*
     ___                      _     _      
    / _ \__   _____ _ __ _ __(_) __| | ___ 
   | | | \ \ / / _ \ '__| '__| |/ _` |/ _ \
   | |_| |\ V /  __/ |  | |  | | (_| |  __/
    \___/  \_/ \___|_|  |_|  |_|\__,_|\___|*/
                                        
	@Override
   public void paintComponent(Graphics g) {  
      Graphics2D drawing_context = (Graphics2D) g;
      drawing_context.setFont(new Font("TimesRoman", Font.PLAIN, 36)); 
      // handle all tiles
      for(int i = 0; i < this.tile_arr.length; i++){
         if(i == this.empty_index){
            drawing_context.setColor(new Color(0,0,0));
         }
         else{
            drawing_context.setColor(new Color(0,155,0));
            if(using_image)drawing_context.setPaint(new TexturePaint(this.img_arr[this.tile_arr[i].get_index()], this.tile_arr[i].get_icon()));
            drawing_context.fill(this.tile_arr[i].get_icon());
            drawing_context.setColor(new Color(255,255,255));
            if(!using_image)drawing_context.drawString(this.tile_arr[i].get_label(), 
                                       this.tile_arr[i].get_x_center(), 
                                       this.tile_arr[i].get_y_center());
         }
      }
      drawing_context.setColor(new Color(0,0,0));
      drawing_context.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
      drawing_context.drawString("Move Count: " + this.num_moves,
                                 this.tile_arr[0].get_width()/2, 
                                 (this.tile_arr[0].get_height()+this.buffer_size)*(1+this.height));
      drawing_context.drawString("Complexity: " + this.complexity,
                                 this.tile_arr[0].get_width()/2, 
                                 (this.tile_arr[0].get_height()+this.buffer_size)*(1+this.height)-24);
      return;
   }

   /*          _            _       
    _ __  _ __(_)_   ____ _| |_ ___ 
   | '_ \| '__| \ \ / / _` | __/ _ \
   | |_) | |  | |\ V / (_| | ||  __/
   | .__/|_|  |_| \_/ \__,_|\__\___|
   |_|                              */

   // get the linear index of the empty space
   private int get_empty_index(){
      // the swapped will be somewhere relative to the empty
      return this.empty_index;
   }
   // get the linear index of the tile adjacent to the empty space
   private Gui_tile get_adjacent_tile(Direction.dir dir){
      // the swapped will be somewhere relative to the empty
      int index = this.empty_index;
      // handles right edge
      if(dir == Direction.dir.RIGHT && (index % width == (width - 1))) return null;

      // get the linear tile index of the tile to move
      switch(dir){
         case UP:
            index += -this.width;
            break;
         case DOWN:
            index += this.width;
            break;
         case RIGHT:
            index += 1;
            break;
         case LEFT:
            index += -1;
            break;
      }
      // bottom edge
      if(index >= this.tile_arr.length) return null;
      return this.tile_arr[index];
   }
}

