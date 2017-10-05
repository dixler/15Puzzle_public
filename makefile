all: Main.class


Main.class: Board.java Direction.java Game.java Gui_button.java Gui.java Gui_tile.java Main.java Node.java Node_pool.java Renderer.java Solver.java Tile.java
	javac Main.java

test:
	java Main

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
