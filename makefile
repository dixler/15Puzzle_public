all: Main.class

Main.class: Board.java Main.java Game.java Node.java Solver.java Tile.java Node_pool.java Direction.java
	javac Main.java

test: Main.class
	java Main

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
