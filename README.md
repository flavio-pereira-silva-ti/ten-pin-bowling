#Ten-Pin Bowling App
##Overview
This is a CLI app that calculates and prints the scores for a bowling game.
It takes as input the path to a text file containing the results.
The text file must be formatted as a tab-separated two columns document.
First column is player's name, second the score obtained on that roll.
Find sample files under `src/test/resources/`.
##Design & Architecture Considerations
###Creation
I create this project using Spring Initializr built in IntelliJ choosing Maven as its dependency management tool.

After completed, I noticed that Spring Boot wasn't needed, but since project size isn't an issue, and
it provides a nice integration with unit and integration tests, I decided to leave it as is.
###Organization
There are three packages each acting as a grouper for classes that work closely together to deliver a some result.
In `io` there are classes for reading a file and printing in the terminal the results.
In `scoreboard` there are classes that deal with scoreboard creation and validation.
In `frame` there are classes that represents all possible states for a frame and how each one behave.
###Design patterns
####Strategy Pattern
I used the Strategy Pattern in `ScoreboardBuilder`. The idea to use it came after translating the logic behind filling
a bowling game scoreboard into a state machine. I noticed that there are two possible states from frame 1 to 9 and four 
possible states in frame 10.
####Method Template Pattern
`Frame` is a high level abstract class acting as an interface. There are 5 concrete sub-classes of `Frame` each one 
encapsulating rules for score calculation and printing.
##How-To
###System Requirements
* Java 8+
* Maven 3+ (developed and tested with 3.6.3)
###Compile
Run `mvn compile` on this project's root.
###Test
Run `mvn test` on this project's root. Coverage is at 86% of code lines.
###Creating a runnable JAR
* Run `mvn package` on this project's root;
* Jar will be generated under `target`, it should be named like `ten-pin-bowling-x.x.x.jar`;
###Running this app's JAR
* Run `java -jar /path/to/ten-pin-bowling-x.x.x.jar /path/to/input-file.txt`;
* Make sure to use the correct file path separator according to your OS;