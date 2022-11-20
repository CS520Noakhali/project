## Project details

Term project–Design and Implementation-Part 2 
Team Noakhali

## Description:

Drone control software which allows users to control the drone to visit a specific area and scan the entirety of the farm.
Users can also add items and item containers in the dashboard and send a command to the drone for visiting and scanning.

## How to run:

run in terminal:

       `java --module-path "path/to/JavaFX-sdk/lib" --add-modules javafx.controls,javafx.fxml App`

!!!Necessary to replace "path/to/JavaFX-sdk/lib" with the correct path to JavaFX libraries


## Usage:

The actual drone should be launched after the simulated drone in the dashboard has finished the animation sequence. 
This is done by clicking an action for the simulated drone to perform, and after allowing the animation to complete, clicking the "Launch Drone" button.
Before doing any new operations with the drone, it is necessary to wait until the drone finishes its current flight.


"Visit item" button: on click, the simulated drone will visit the selected item/item container and then return to the starting position, the command center.

"Scan Farm" button: the simulated drone will scan over the entire farm and return to the starting position, the command center.

"Launch Drone" button: The physical drone will launch and follow the action sequence of the simulated drone after it has finished the animation sequence.


## Dimensions:

800x600 pixels for the farm pane on a dashboard
0.8x0.6 meters for the physical model of the farm

## Authors:

1.Qinglu Ren
2.Katarina Akhmetova
3.Debbie Fu
4.Rainfield Mak
5.Zhiying Lu
6.Ian Toy
7.Mikhail Surin
8.Cheng-Hao Yu
