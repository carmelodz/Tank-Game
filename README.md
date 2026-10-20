<img width="1938" height="1404" alt="Tank Wars Cap" src="https://github.com/user-attachments/assets/023f4340-02f2-4919-bd5d-989f1c05fe10" />

# Tank Wars

A 2D tank game written in Java where 2 players battle each other's tanks. Built as a solo project at San Francisco State University (April 2022).

## Features

- **Player controls and tank movement**
- **Collision detection** between tanks, projectiles, and the environment
- **Game map with wall detection**
- **Sound, graphics, and animation** using open-source libraries

## Version of Java Used: 14.0.2

## IDE used: IntelliJ IDEA Ultimate Edition (2021.2.2)

## Steps to import game project into IDE:

1. Copy project link from the GitHub project page, either through HTTPS or SSH
2. Open MacOS Terminal and type the command "git clone [insert copied link here]
3. Verify project has been cloned by navigating to specified save location 
4. Open IntelliJ IDE and navigate to File -> Open, select the project folder and then click "Open" 
5. Check to make sure that the "resources" folder is marked as "Resources Root" directory and the "src" folder is marked as "Source root" directory
6. This can be done by right-clicking the resource and src folder, respectively, and navigating to "Mark Directory as..." or through File -> Project Structure
7. In the Project Structure window, navigate to "Artifacts" under "Project Settings"
8. Click on the "+" button at the top of the window, and select JAR -> From moddules with dependencies
9. If needed, make sure the tank game project folder is selected for the "Module" setting and the "GameDriver" is selected for the "Main Class" setting. Leave all other settings to their default values. Click OK -> Apply -> OK.

## Steps to build Game:

1. Once the project is imported to IntelliJ IDE, navigate to Build -> Build Artifacts in the Menu toolbar to build the JAR file
2. In the "Action" window, select "Build" and click on it
3. You will see an "out" folder created, where in that folder is a subfolder called "artifacts", in which the tankgame project JAR file is located
 
## Steps to run Game:

1. In the "jar" folder, navigate to the tankgame JAR file and right-click -> Run "tankgame.JAR"
2. You can also right-click the "GameDriver" class and select "Run GameDriver.main()"
3. A window will pop up showing the start menu screen, now you can play the game!

## How to Play:

|               | Player 1 | Player 2 |
|---------------|----------|----------|
|  Forward      |    W     |    UP    |
|  Backward     |    S     |   DOWN   |
|  Rotate left  |    A     |   LEFT   |
|  Rotate Right |    D     |   RIGHT  |
|  Shoot        |  SPACE   |   ENTER  |
|  Menu         |   ESC    |    ESC   |

## My Role

I built this game on my own, including player controls, tank movement, collision detection, and the game map with wall detection. I used open-source libraries for sound, graphics, and animation. Player feedback was positive.

## Tech Stack

- **Language:** Java
- **Libraries:** https://opengameart.org/ (Used for all game resources)

## Getting Started

```bash
git clone [repo URL]
cd [project folder]
[build command, e.g., javac or your build tool]
[run command, e.g., java Main]
```

[Adjust to match your environment IDE settings. Note the Java version the game requires.]

## Credits

https://opengameart.org/ (Used for all game resources)

## Status

Completed as a course project.
