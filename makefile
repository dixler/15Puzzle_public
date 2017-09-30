all: Engine.class



Engine.class: Board.java Engine.java Move.java Node.java Solver.java Tile.java Node_pool.java
	javac Engine.java

test: Engine.class
	java Engine

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
