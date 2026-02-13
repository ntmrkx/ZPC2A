# 🧩 Last Mistake – ZPC2A

## 📖 Project Description
This project is a console-based text adventure game written in Java.
The player moves between locations, collects items, interacts with NPCs, and completes a quest to win the game.

## 🎮 Gameplay
The player can:

move between locations

collect items

view inventory

talk to NPCs

unlock new locations using items

solve a riddle to finish the game

## 🏆 Winning the Game
To win, the player must:

Collect required items

Reach the location Luba

Talk to Luba

Solve Luba’s riddle

## 🧠 Design Patterns
The project uses the Command Pattern:

Each command (go, take, talk, etc.) is implemented as a separate class.

All commands implement the Command interface.

The Console class registers and executes commands dynamically.

This allows easy extension of the game by adding new commands without modifying existing logic.
