# Description : 
There are two part of this application that I can think of.

1. Console app to support few commands.
2. Parking lot to execute actions for the command.

Factors to think about.

Console app can be a simple application that takes the commands and execute those command effectively and securely.
In this application we can think about so many factors.
1. Should support any type of command for any application.
2. How frequently the command will be fired based on that we can enable chunk processing and delay processing.
3. Since the command will be provided by external application how the output will be consumed.
4. Preprocessing and postprocessing to support feature link notifications etc.

Parking app will be simple parking implementation, we will need to care about how the parking action will be handled.
1. It will be providing supported command.
2. parking action handling strategy, for now we have in memory but in future we can have database strategy.


# Design pattern used: 
1. Builder pattern.
2. Strategy design pattern.
3. Command design pattern.

# Implementation

This application is divided in three layer.
1. Console app : This is a generic framework to handle any type of command, This provided an interface ICommand for external application.
Right now we have 2 strategy to handle command but in future we can have many more strategy.
This provided ConsoleAppContext to build all parameters for execution, client application can provided their own setup.

2. Parking lot app : This is simple parking lot application with provided rule and commands to handle in memory parking, this can be extended
for any other parking strategy.

# Running the project

Interactive command-line mode 	./parking_lot
File comand mode				./parking_lot <filename.txt> <.txt extension is mandatory>

# Project Requirements
JDK version : 1.8.0_144
Maven version :  Latest version of maven

# Library used : Mockito and testNG for testing purpose
