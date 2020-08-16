# JetBrains_TicTacToe

# About

It is a project which I made during JetBrains Academy Java Developer Bootcamp. It have three levels of difficulty and simple command line interface. 

# Instruction

At the begining you have two options: start a game or exit. To start you need to write start and choose one of four available modes for the first and fr the second player. You can choose from:

- user (you need to type from keyboard coordinates for moves)
- easy (bot making move totaly random)
- medium (bo which one choose winning move if he can, otherwise he block winning move of opponent if other player can do this move in next round or play like easy      bot)
- hard (undefeated champion; he makes move using minmax algorithm)

e.g. "start user user" will run game for two person, so you can play with your friend :D 

When you need to insert coordinates there will be message to do that, so you can't miss it.
e.g. Enter the coordinates:

Then you just need to write two coordinates where you want to put your sing.
e.g. Enter the coordinates: 2 3


!!! Program have simple mechanism against incorrect data entry !!!

# This is the presentation of the board

Board is divided for 9 fields, each can be described using two parameters: x and y

--------- \
|7  8  9| \
|4  5  6| \
|1  2  3| 
---------

- f -> (x, y)
- 1 -> (1, 1)
- 2 -> (2, 1)
- 3 -> (3, 1)
- 4 -> (1, 2)
- 5 -> (2, 2) ...

