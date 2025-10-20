# Simple Java Lottery Game (Stack & Queue)

## Project Overview

[cite_start]This project is a two-player lottery game written in Java[cite: 118, 120]. [cite_start]It demonstrates the fundamental principles of data structures by requiring the `Stack` and `Queue` classes to be built from scratch, without using Java's built-in collections like `ArrayList`, `List`, or the standard `Stack`/`Queue` classes[cite: 199, 212].

The game involves players matching randomly drawn balls to their individual game cards. [cite_start]The system also reads from and writes to a persistent `highscoretable.txt` file to maintain a top 12 scoreboard[cite: 127, 175].

## Core Files

The project is built from several key Java files:

* [cite_start]`main.java` / `Game.java`: Contains the main game loop and all game logic[cite: 85, 94, 104, 108].
* [cite_start]`Stack.java`: A custom-built Stack class used for player cards[cite: 102, 112, 147].
* [cite_start]`Queue.java`: A custom-built Queue class used for the lottery ball "bags" and high score table[cite: 100, 111, 127, 152].
* [cite_start]`HighScoreTable.java`: A helper class for managing the high score logic[cite: 91, 106].

## Features & Game Rules

### 1. Custom Data Structures

The core constraint of this project is the manual implementation of data structures:

* [cite_start]**Custom `Stack`:** Used to represent each player's card (S1, S2)[cite: 147].
    * [cite_start]Must only contain the methods: `push`, `pop`, `peek`, `isFull`, `isEmpty`, and `size`[cite: 201].
    * [cite_start]All other logic (like searching or displaying) must be handled in the main program, not in the `Stack` class[cite: 203, 204].
* **Custom `Queue`:** Used for multiple purposes:
    * [cite_start]`Q1` (Names) & `Q2` (Scores): Holds the high score table, read from a file[cite: 127].
    * [cite_start]`Q3` (bag1): The main bag, initially holding all 13 possible lottery balls (A, 2-10, J, Q, K)[cite: 125, 152].
    * [cite_start]`Q4` (bag2): The discard bag, holding balls that have already been drawn[cite: 154].
    * [cite_start]Must only contain the methods: `enqueue`, `dequeue`, `peek`, `isFull`, `isEmpty`, and `size`[cite: 206].

### 2. Game Setup

1.  [cite_start]**Read High Scores:** The game reads `highscoretable.txt` and populates two sorted Queues (Q1 for names, Q2 for scores)[cite: 127]. [cite_start]The table holds a maximum of 12 items[cite: 131].
2.  [cite_start]**Get Card Size:** The user is prompted to enter `n` (a value between 7 and 10) for the number of items on each player's card[cite: 124, 146].
3.  **Create Cards:** Two Stacks (S1, S2) are created for the players. [cite_start]Each stack is filled with `n` random, distinct, and sorted values (e.g., A, 3, 7, 8, 10, J, K)[cite: 147, 148, 150].

### 3. Gameplay Loop

1.  [cite_start]**Draw Ball:** A lottery ball is randomly selected from `bag1` (Q3)[cite: 153].
2.  [cite_start]**Move Ball:** The drawn ball is removed (dequeued) from `bag1` and added (enqueued) to `bag2`[cite: 157].
3.  **Check Cards:** Both players' Stacks (S1, S2) are checked for the drawn ball.
4.  **Update Score:**
    * [cite_start]**Match:** If a player has the ball, it is `pop`ped from their stack, and they get **+10 points**[cite: 160].
    * [cite_start]**No Match:** If the player's card does not contain the ball, they lose **-5 points**[cite: 161].
5.  [cite_start]**Display Status:** After every round, the program prints the current state of both player cards (S1, S2), both bags (Q3, Q4), and the current scores[cite: 164, 165].

### 4. Bonus Scoring

* [cite_start]**"Birinci Çinko" (First Tournament):** The first player to successfully delete 4 elements from their stack gets an immediate **+30 bonus points**[cite: 162].
* [cite_start]**Game Win Bonus:** The first player to delete all elements from their stack (empty the card) gets an immediate **+50 bonus points**[cite: 162]. [cite_start]If both empty their stack on the same turn, they share the bonus[cite: 163].

### 5. End of Game

1.  [cite_start]**Game Over:** The game ends as soon as one player's card (Stack) becomes empty[cite: 167].
2.  [cite_start]**Declare Winner:** The winner is the player with the higher score[cite: 168]. [cite_start]If scores are equal, it's a tie[cite: 169].
3.  [cite_start]**Update High Score Table:** The winner's name and score are added to the high score Queues (Q1, Q2)[cite: 171]. [cite_start]The queues are re-sorted, and if they now exceed 12 items, the last record is deleted[cite: 174].
4.  [cite_start]**Save Scores:** The updated high score table is written back to the `highscoretable.txt` file[cite: 175].
5.  [cite_start]**Play Again:** The user is asked if they want to play another round[cite: 177].

## How to Use

1.  Compile all `.java` files:
    ```bash
    javac *.java
    ```
2.  [cite_start]Create a `highscoretable.txt` file in the same directory (or at the path specified in the code, e.g., `D:\\highscoretable.txt` [cite: 175]). The format should be one entry per line (e.g., `Berk 160`).
3.  Run the main Java class (the one containing the `main` method, likely `main.java` or `Game.java`):
    ```bash
    java main
    ```
