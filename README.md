## installation
   uses a makefile for ease of installation
   install 
      $ make 
   play 
      $ make test 
## instructions
   to shuffle the puzzle click the shuffle button

   the goal of the puzzle is to move all of the tiles around 
   in order to get the following picture.

   you can only move tiles into the empty space to shift them
   around

   to move the pieces into the adjacent spot, just click on them
   and they will slide into the empty space.

   the autosolver uses breadth-first-search to find the solution
   that requires the fewest moves(this was a design requirement 
   for the project and is admittedly incredibly slow). It can 
   solve puzzles that can be solved in under 13 moves optimally.

## previews:
### unsolved:
![java\_15\_puzzle](https://raw.githubusercontent.com/dixler/java_15_puzzle/master/preview2.png)
### solved:
![java\_15\_puzzle](https://raw.githubusercontent.com/dixler/java_15_puzzle/master/preview1.png)
