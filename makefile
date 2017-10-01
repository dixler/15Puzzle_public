all: Main.class

Main.class: Main.java Board.java Direction.java Game.java Gui_tile.java Node.java Node_pool.java Solver.java Tile.java Renderer.java
	javac Main.java

test: Main.class
	java Main

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
