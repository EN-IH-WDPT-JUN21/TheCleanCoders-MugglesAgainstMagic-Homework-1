# Muggles against Magic

## Introduction

*Muggles against Magic* is a simple console-based RPG battle simulator with additional theme elements inspired by Harry Potter franchise. It was created as a homework assignment for Ironhack's Web Development Full Stack Bootcamp.

## Getting started

### Clone, compile and run the game in your terminal

1. Clone the repository
```
git clone https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1.git
```
2. Go to the src directory
```
cd your_path_to_project/theCleanCoders-MugglesAgainstMagic-Homework-1
```
3. Compile the source files
```
javac src/*.java -d .
```
4. Run the program
```
java Main
```

### Run the game through an IDE

1. Download ZIP file of the project

![zipdownload.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/katBranch/img/zipdownload.png)

2. Extract the ZIP file and open the directory as a project on an IDE such as IntelliJ

3. Run the Main.java file

## How the battle simulator works

### Battles

The objective of the game is to create a party of warriors and wizards to fight other parties. Warriors and wizards have different attributes and combat styles. Every battle consists of 1 vs. 1 duels. Duels are by rounds, in each round both combatants attack at the same time. Duels end when at least one combatant dies. The surviving combatant returns to the party and the loser gets removed and the player chooses combatants for the next duel. When a party lose all their members a winner party is declared.

### Characters

**Warriors (Muggles)** are strong well armored characters that focus on the attribute strength. Every round a warrior will try to do a “Heavy attack”. The damage of a heavy attack is equal to their strength and every hit will decrease their stamina by 5 points. If they can’t make a heavy attack they will do a “Weak attack”. The damage of a weak attack is the half of the strength. Every weak attack will recover their stamina by 1.

**Wizards** are the masters of the arcane their main attribute is intelligence. Every round a wizard will try to cast a “Fireball”. The damage of a fireball is equal to their intelligence and every fireball will decrease their mana by 5 points. If they can’t cast a fireball they will do a “Staff hit”. The damage of a staff hit is equals to 2. Every staff hit will recover his mana by 1.

**Stats** are always randomized for the characters:
- **hp**: random between 100-200 to warriors, 50-100 for wizards
- **stamina**: random between 10-50
- **strength**: random between 1-10
- **mana**: random between 10-50
- **intelligence**: random between 1-50

## Menu navigation

To navigate through the menu enter a number set for the desired option and press *Enter*. For teams' and characters' names enter longer strings and press *Enter*.

### Menu flow chart

![menuflowchart.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/katBranch/img/menuflowchart.png)

## Authors

**TheCleanCoders**: Vitaliano Costa, Mara Fernández, Joao Lopes, Natalia Norberciak, Katarzyna Wąsik.