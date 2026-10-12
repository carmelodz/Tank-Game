# csc413-tankgame


| Student Information |                             |
|:-------------------:|-----------------------------|
|  Student Name       |   Carmelo De Guzman         |
|  Student Email      |   cdeguzman@mail.sfsu.edu   |

## src Folder Purpose 
src folder is to be used to store source code only.

## resources Folder Purpose 
resources folder is to be used to store the resources for your project only. This includes images, sounds, map text files, etc.

`The src and resources folders can be deleted if you want a different file structure`

## jar Folder Purpose 
The jar folder is to be used to store the built jar of your term-project.

`NO SOURCE CODE SHOULD BE IN THIS FOLDER. DOING SO WILL CAUSE POINTS TO BE DEDUCTED`

`THIS FOLDER CAN NOT BE DELETED OR MOVED`

# Required Information when Submitting Tank Game

## Version of Java Used: 14.0.2

## IDE used: IntelliJ IDEA Ultimate Edition (2021.2.2)

## Steps to Import project into IDE:

1. Copy project link from the GitHub project page, either through HTTPS or SSH
2. Open MacOS Terminal and type the command "git clone [insert copied link here]
3. Verify project has been cloned by navigating to specified save location 
4. Open IntelliJ IDE and navigate to File -> Open, select the project folder and then click "Open" 
5. Check to make sure that the "resources" folder is marked as "Resources Root" directory and the "src" folder is marked as "Source root" directory
6. This can be done by right-clicking the resource and src folder, respectively, and navigating to "Mark Directory as..." or through File -> Project Structure
7. In the Project Structure window, navigate to "Artifacts" under "Project Settings"
8. Click on the "+" button at the top of the window, and select JAR -> From moddules with dependencies
9. If needed, make sure the tank game project folder is selected for the "Module" setting and the "GameDriver" is selected for the "Main Class" setting. Leave all other settings to their default values. Click OK -> Apply -> OK.

## Steps to Build your Project:

1. Once the project is imported to IntelliJ IDE, navigate to Build -> Build Artifacts in the Menu toolbar to build the JAR file
2. In the "Action" window, select "Build" and click on it
3. You will see an "out" folder created, where in that folder is a subfolder called "artifacts", in which the tankgame project JAR file is located
 
## Steps to run your Project:

1. In the "jar" folder, navigate to the tankgame JAR file and right-click -> Run "tankgame.JAR"
2. You can also right-click the "GameDriver" class and select "Run GameDriver.main()"
3. A window will pop up showing the start menu screen, now you can play the game!

## Controls to play your Game:

|               | Player 1 | Player 2 |
|---------------|----------|----------|
|  Forward      |    W     |    UP    |
|  Backward     |    S     |   DOWN   |
|  Rotate left  |    A     |   LEFT   |
|  Rotate Right |    D     |   RIGHT  |
|  Shoot        |  SPACE   |   ENTER  |
|  Menu         |   ESC    |    ESC   |

<!-- you may add more controls if you need to. -->
