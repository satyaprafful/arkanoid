IReadMe: Arkanoid 
IMPT Note: HighScores.txt: Do not Edit!

1. The game is a rebuild of the 32-bit classic Arkanoid. Game Instructions are fairly straightforward and can be found in-game! Don't worry, the game will pause while you read the instructions. Use your Arrow Keys to control the paddle!

2. Remember to click "Save your Score" if you want to see if you hit the Top 5 leaderboard! Don't just click "Yes" because its blue and shiny and highlighted. Simply doing so will just start a new game! 
  
3. How to build a level
After opening LevelData.txt, in the first row, the first number represents No. of Columns. and the second number represents No. of Rows. Additionally, 1 signifies a location where a block exists, and 0, where the block doesnt. 

Important Note: When creating your own levels, make sure the No. of rows and columns match up, otherwise the game will not run properly!
Developer's Recommendation: Do not go for more than 11 Columns, and 8 Rows. Best played with 11 x 6. All creative designs are welcome!   

Cheers!
--------
To the Grader: 
1. You may ignore PowerUps.java. It is still in the works! Will be out for Ver 2.0 hopefully! But there's some pretty neat work and the framework has been laid for easily implementing PowerUps into the game. Each brick has a "ContainsPowerup" state. There is a method to creates a random PowerUp. All I need to do is set the graphics and draw the PowerUps!
   
2. The 4 design concepts that are applied in the game, 
a. 2D Arrays: I have created a class of Bricks which contains a 2D Array. This is used to store the instances of Brick. 

b. Dynamic Dispatch: In order to reduce code written, my game sticks to the following Inheritance Tree. CircularObjects and RectangularObjects extend GameObject. Paddle and Brick extend RectangularObjects. Ball and PowerUps extend Circular Objects. The specific details behind this can be further explained during the demo! 			  

c. Collections: Highscores are managed by Highscores.java. I have implemented a solution that only requires reading and writing from the textfile once. Parsed values are stored in a TreeMap<Integer(score), ListArray<String>(List of names with this score)>. 
The scores and entries are then managed internally using collections such as TreeMaps, ArrayLists and Sets. The Top 5 scores are found and passed onto GameCourt, ready to be printed (I'm particularly proud of this function). Once again, the specific details behind this can be further explained during the demo!
   
d. I/O: Another interesting aspect of the Game is the ability for users to design their own levels. Though it is fairly basic, it is enjoyable and users can input their initials. 
I use a FileScanner to get Integer values, first the number of rows and then columns, which is then passed on throughout the entire code. Next, the 1s and 0s are parsed, and the bricks are arranged in such a way that the User Interface is easy and intuitive. Where there is a 1, there is a brick! Binary Input -> Visual Output (Granted, the user designs according to the simple rules set). 
  
  