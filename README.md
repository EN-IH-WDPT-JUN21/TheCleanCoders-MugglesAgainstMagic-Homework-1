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

![zipdownload.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/zipdownload.png)

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

### Team creation

When creating new characters you may choose character's type (Warrior/Wizard) and character's name. Stats are always generated randomly. There is also an option of generating random characters for both teams in which case it is no longer possible to choose their type and their names are random combinations of first names found in file names.txt and surnames found in file surnames.txt, however if there is aready a character with the same name in the teamt, the suffix "Jr" gets added to the new character's name.

#### Exporting teams to a file
After creating teams there is an option of saving them to CSV files. Names of the files are generated automatically and any spaces get replaces with an underscore e.g. team "Gryffindor Alumni" gets saved to the file "Gryffindor_Alumni.csv".

#### Importing teams from a file
There is an option of importing teams from CSV files. Names of the imported teams are generated automatically based on the filename-and any underscores get replaced with empty spaces e.g. from file "Gryffindor_Alumni.csv" a team "Gryffindor Alumni" gets imported.

### Menu navigation

To navigate through the menu enter a number set for the desired option and press *Enter*. For teams' and characters' names enter longer strings and press *Enter*.

#### Menu flow chart

![menuflowchart.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/menuflowchart.png)

## UML Class Diagram

![MugglesAgainstMagicClassDiagram.jpeg](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/MugglesAgainstMagicClassDiagram.jpeg)

## Screenshots

![screenshot1.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/screenshot1.png)
![screenshot2.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/screenshot2.png)
![screenshot3.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/screenshot3.png)
![screenshot4.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/screenshot4.png)
![screenshot5.png](https://github.com/EN-IH-WDPT-JUN21/TheCleanCoders-MugglesAgainstMagic-Homework-1/blob/main/img/screenshot5.png)


## Authors

**TheCleanCoders**: Vitaliano Costa, Mara Fernández, Joao Lopes, Natalia Norberciak, Katarzyna Wąsik.