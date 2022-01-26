# Dijkstra's Cartesian Coordinate Plane Implementation
## Briana Grant @ https://github.com/grantbriana 

## Project Prompt:

- We have built a nice robot to move around the CPSC’s CCT 408 room. However, the robot lacks a control algorithm to plan its movements. To this end, the School of Computer Science has asked the CSPC 4115 Algorithmics students to develop a planning algorithm that can avoid obstacles and help to find the shortest path between two points in the CCT room. 

- To simplify the development of the algorithm, the School has decided to assign discrete coordinates in the room (divide the room in a grid) in such a way that the robot can move in the room. **The maximum size of this grid is 1000 points (width) by 500 points (height). The position (0,0) is the lowest left of the room, and the position (1000, 500) is the rightmost corner of the room.** 
**The robot can move in an x or y grid point at a time or move in diagonal.** For example, if the robot is a position (10, 11), it can move to positions eight possible positions (9, 10) , (10, 10), (11, 10), (9, 11), (9, 12), (10, 12), (11, 12), (10, 12) (see figure below).
  
## Task:
- Given the two positions **A = (XSTART, YSTART) and B = (XEND, YEND)**, and **a set of k positions that the robot cannot move to (obstacles) O = {o1, o2, o3, o4, …., ok} received in a file, where each Oi  obstacle has an (X,Y) grid coordinate**, 

**- The task is to find a shortest path between A and B points in the grid, such that the robot avoids the obstacles. Note that the robot cannot jump above an obstacle. Please see an example below of a possible planning.** 
 
**Input:** One file, called obstacles.dat with the obstacle inputs to the program, and two coordinates A = (Xstart, Ystart) and B = (XEnd, YEnd)

**Output:**  the total distance of the shortest path and the sequence of points, one point per line, starting from the source point A which indicates the sequence grid points of the path taken by the robot. 


#Implementation Details 
