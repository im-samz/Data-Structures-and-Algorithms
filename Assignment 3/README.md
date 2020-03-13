# Project Description
The project was to create a program that finds a path between two points on a roap map, which is specified by custom input files. The road map contains three types of roads: toll roads, normal roads, and reward roards. As such, an additional constrainst is added to force the program to accumulate enough rewards to traverse through necessary toll roads.

## Learning Objectives
Students were to implement the **Undirected Graph** data structure and perform a custom depth first search traversal to find a satisfiable path.

## Student Contribution
Students were to implement the following classes: **Node, Edge, Graph, GraphException, MapException,** and **RoadMap**. The remaining classes were provided by the instructor.

## How to Run
To run, place all image and text files outside the src directory containing the java files. Each text file is used an input to create the structure of the roadmap. 

*Note:* the first eight lines of the text file represent the scale, start location, end location, width of road, length of road, initial rewards, toll cost, and reward gain. The following lines represent the road map: + is an intersection; X is a house; T is a toll road; C is a reward road; and F is a free road.

The main method is found in Path.java, and the program can be executed by typing the following in the command window:

**java Path input_file**

You may also append an additional parameter, **delay**, where delay is the number of milliseconds the program waits before drawing the next edge.

```
Grade Achieved: 100%
```
