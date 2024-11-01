# Floppy Fish by Raegan Van Raamsdonk

Floppy Fish is a high school project inspired by the classic Flappy Bird game, developed in Java. Set in an underwater world, players control a fish navigating through obstacles like seaweed. With a scrolling background and aquatic-themed elements, Floppy Fish offers a simple but addicting gameplay experience.

<div align="center">
    <img width="400" alt="image" src="https://github.com/user-attachments/assets/8db1ec52-1c98-438b-86d5-278b5a621af8">
</div>

## How It's Made
**Tech used:** Java

Floppy Fish is built entirely in Java, utilizing simple 2D graphics to create an engaging underwater experience. The game features a scrolling background, which is implemented to simulate the fish’s forward movement through the water. This background scrolls continuously, giving the illusion of an expansive underwater environment.

To add variety to the gameplay, the game also includes seaweed obstacles with varying gap sizes. Each seaweed obstacle has a randomly generated gap, requiring players to adjust their timing and movements. This adds an element of challenge and keeps the gameplay fresh, as players must navigate through unpredictable obstacles. The code is organized with each game element, such as the fish and seaweed, represented by its own class, making it modular and easy to extend in the future.

Additionally, the game loop is carefully structured to handle updates and rendering in sync, ensuring smooth and responsive gameplay. The fish's movements are animated based on player input, allowing for simple but satisfying mechanics reminiscent of classic arcade games.

## Features
- Underwater-themed gameplay with fish and seaweed obstacles.
- Scrolling background to create an immersive oceanic environment.
- Easy-to-play mechanics inspired by Flappy Bird.

## Lessons Learned:

Building Floppy Fish taught me a lot about the fundamentals of game development and reinforced the importance of structured, object-oriented programming. One of those “whoa, this is awesome” moments came when I successfully implemented the scrolling background—it was incredible to see the underwater world come to life. Another proud moment was getting the varying seaweed gaps to work, adding a new level of challenge to the game and revealing the possibilities of how I could add additional features. 

This project deepened my understanding of Java and helped me appreciate the nuances of game logic and player interaction. It reminded me that each new project, no matter how simple it might seem, is an opportunity to learn, grow, and refine my skills. I’m excited to take these lessons forward into more advanced projects.

## Getting Started

### Prerequisites
To run Floppy Fish, you need:
- Java Runtime Environment (JRE) installed on your computer.
  - [Download JRE](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) if it's not installed.

### Running the Game

1. **Download the `.jar` file** from this repository.
2. **Run the `.jar` file**:
   - **Option 1**: Double-click on the `.jar` file.
   - **Option 2**: Right-click on the `.jar` file, select **"Open With"** -> **"Java(TM) Platform SE Binary"**.

### Compiling from Source (Optional)
If you prefer to run the game from source code:
1. Clone the repository:
   ```bash
   git clone https://github.com/raeganvr/FloppyFish.git
2. Navigate to the project folder and compile all .java files:
    ```bash
    javac *.java
4. Run the main game file with java Game
    ```bash
    java Game
