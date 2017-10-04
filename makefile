all: test


test: Board.java Direction.java Game.java Gui_button.java Gui_tile.java Main.java Node.java Node_pool.java Renderer.java Solver.java Tile.java Gui.java
	javac Main.java
	java Main

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
