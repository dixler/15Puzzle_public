all: Engine.class


Engine.class: Engine.java Board.java Tile.java
	javac Engine.java

test: Engine.class
	java Engine

clean:
	rm *.class

kdixle2proj2.zip: 
	zip -r kdixle2proj2.zip ../prog2
	
