import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    int col = 1000;
    int rows = 500;
    HashMap<Integer, Vertex> output = new HashMap<>();
    List<List<String>> fileContent = new ArrayList<>();
    HashMap<String, Vertex> obstacles = new HashMap<>();
    int startX; int endX;
    int startY; int endY;


    public Graph(String filePath) throws FileNotFoundException {
        addNodes();

        //File input required with start on first line and end on second line. Obstacles on the rest of lines

        readFile(filePath);
        Vertex start = getNode("(" + startX + "," + startY + ")");
        Vertex end = getNode("(" + endX + "," + endY + ")");

        System.out.println("Exploring Nodes. Calculating Shortest Path... Please Wait");
        dijkstraAlgorithm(start.getNumber(),end.getNumber());

        //Dijkstra Algorithm implement using heuristics:
        //dijkstraAlgorithmHeuristics(start.getNumber(),end.getNumber());
    }

    //Dijkstra's algorithm implementing heuristics
    public void dijkstraAlgorithmHeuristics(int source, int dest) {
        HashMap<Vertex, Vertex> parents = new HashMap<>();
        Set<Vertex> processed = new HashSet<>();
        Queue<Vertex> Q = new PriorityQueue<>();

        for (int i = 0; i < output.size(); i++) {
            output.get(i).setH(output.get(dest));
        }

        //Initialize source vertex to 0.0
        Vertex sourceVertex = output.get(source);
        sourceVertex.setDist(0.0);

        //Initializes source at front of queue
        Q.add(sourceVertex);
        processed.add(output.get(source));

        while(!Q.isEmpty()){
            Vertex u = Q.remove();
            System.out.println(u + " " + u.getH());
            if(u == output.get(dest)){
                break;
            }

            processed.add(u);

            for (Vertex v: getNeighbors(u.getNumber())){
                //Creates edge between current Vertex and adjacency
                if(obstacles.containsValue(v)) {
                    break;
                }

                Edge e = new Edge(u,v);

                //distance of currentVertex and edge weight (0 + 1.0)
                double cost = u.getDist() + e.getDistance();

                if(!processed.contains(v) && cost < v.getDist()){
                    v.setDist(cost);
                    Q.add(v);
                    parents.put(u,v);
                }
            }
        }System.out.println(output.get(dest).getDist());
    }

    public void dijkstraAlgorithm(int source, int dest) {
        HashMap<Vertex, Vertex> parents = new HashMap<>();

        Set<Vertex> processed = new HashSet<>();

        //Initialize all vertex nodes to infinity (See Vertex class: dist)

        //Initialize source vertex to 0.0
        Vertex sourceVertex = output.get(source);
        sourceVertex.setDist(0.0);

        Queue<Vertex> Q = new PriorityQueue<>();

        //Initializes source at front of queue
        Q.add(sourceVertex);
        processed.add(output.get(source));


        while(!Q.isEmpty()){
           Vertex u = Q.poll();//Q.peek();
            //System.out.println(u);
            if(u == output.get(dest) || processed.contains(output.get(dest))){
                break;
            }

            processed.add(u);

            for (Vertex v: getNeighbors(u.getNumber())){

                //Makes sure obstacle vertex is not processed
                if(obstacles.containsValue(v)) {
                    break;
                }

                Edge e = new Edge(u,v);

                //distance of currentVertex and edge weight
                double dist = u.getDist() + e.getDistance();

                if(dist < v.getDist() || !processed.contains(v)){
                    v.setDist(dist);
                    Q.add(v);
                    processed.add(v);
                    parents.put(v,u);
                }
            }
        } System.out.println("Shortest Distance: " + output.get(dest).getDist()); System.out.println( "Path: "); getPath(parents, output.get(dest));
    }

    public void getPath(HashMap<Vertex,Vertex> parents, Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex temp = target;

        path.add(temp);
        while (parents.get(temp) != null) {
            temp = parents.get(temp);
            path.add(temp);
        }

        Collections.reverse(path);

        //Iterate through path list and print each vertex
        for(Vertex v: path){
            System.out.println(v);
        }
    }


    //Traverses immediate 8 neighbors (left,right,up,down,etc.) of current node
    public List<Vertex> getNeighbors(int source) {
        List<Vertex> list = new ArrayList<>();
        if (right(output.get(source).getX(), output.get(source).getY()) != null && !obstacles.containsValue(right(output.get(source).getX(), output.get(source).getY()))) {
            list.add(right(output.get(source).getX(), output.get(source).getY()));
        }
        if (up(output.get(source).getX(), output.get(source).getY()) != null && !obstacles.containsValue(up(output.get(source).getX(), output.get(source).getY()))) {
            list.add(up(output.get(source).getX(), output.get(source).getY()));
        }
        if (down(output.get(source).getX(), output.get(source).getY()) != null && !obstacles.containsValue(down(output.get(source).getX(), output.get(source).getY()))) {
            list.add(down(output.get(source).getX(), output.get(source).getY()));
        }
        if (left(output.get(source).getX(), output.get(source).getY()) != null  && !obstacles.containsValue(left(output.get(source).getX(), output.get(source).getY()))) {
            list.add(left(output.get(source).getX(), output.get(source).getY()));
        }
        if (diagonalDownLeft(output.get(source).getX(), output.get(source).getY()) != null && !obstacles.containsValue(diagonalDownLeft(output.get(source).getX(), output.get(source).getY()))) {
            list.add(diagonalDownLeft(output.get(source).getX(), output.get(source).getY()));
        }
        if (diagonalUpLeft(output.get(source).getX(), output.get(source).getY()) != null  && !obstacles.containsValue(diagonalUpLeft(output.get(source).getX(), output.get(source).getY()))) {
            list.add(diagonalUpLeft(output.get(source).getX(), output.get(source).getY()));
        }
        if (diagonalUpRight(output.get(source).getX(), output.get(source).getY()) != null  && !obstacles.containsValue(diagonalUpRight(output.get(source).getX(), output.get(source).getY()))) {
            list.add(diagonalUpRight(output.get(source).getX(), output.get(source).getY()));
        }
        if (diagonalDownRight(output.get(source).getX(), output.get(source).getY()) != null  && !obstacles.containsValue(diagonalDownRight(output.get(source).getX(), output.get(source).getY()))) {
            list.add(diagonalDownRight(output.get(source).getX(), output.get(source).getY()));
        }
        return list;
    }

    public void addNodes() {
        int coordNumber = 0;
        for (int x = 0; x <= col; x++) {
            for (int y = 0; y <= rows; y++) {
                String coord = "(" + x + "," + y + ")";  //2,0

                    Vertex v = new Vertex(coord, x, y);
                    v.setNumber(coordNumber);
                    output.put(coordNumber, v);
                    coordNumber++;
            }
        }
    }

    public Vertex getNode(String v) {
        Vertex foundNode = new Vertex();
        //for(Vertex node: output){
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(v)) {
                foundNode = output.get(i);
            }
        }
        return foundNode;
    }

    //Helper Methods for adjacency nodes
    public Vertex right(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x + 1 > rows ) {
            coord = null;

        } else {
            coord = ("(" + (x + 1) + "," + (y) + ")");
        }
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }

        return vertex;
    }

    public Vertex left(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x - 1 < 0) {
            coord = null;
        } else {
            coord = ("(" + (x - 1) + "," + (y) + ")");
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex up(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (y + 1 > rows) {
            coord = null;
        } else {
            coord = ("(" + (x) + "," + (y + 1) + ")");
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex down(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (y - 1 < 0) {
            coord = null;
        } else {
            coord = ("(" + (x) + "," + (y - 1) + ")");
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex diagonalUpRight(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x + 1 > col || y + 1 > rows) {
            coord = null;
        } else {
            coord = ("(" + (x + 1) + "," + (y + 1) + ")");
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex diagonalDownRight(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x + 1 > col || y - 1 < 0) {
            coord = null;
        } else {
            coord = (("(" + (x + 1) + "," + (y - 1) + ")"));
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex diagonalUpLeft(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x - 1 < 0 || y + 1 > rows) {   //   5,5     4,6
            coord = null;
        } else {
            coord = (("(" + (x - 1) + "," + (y + 1) + ")"));
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public Vertex diagonalDownLeft(int x, int y) {
        String coord;
        Vertex vertex = null;
        if (x - 1 < 0 || y - 1 < 0) {
            coord = null;
        } else {
            coord = ("(" + (x - 1) + "," + (y - 1) + ")");
        }

        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).getCoordinate().equals(coord)) {
                vertex = output.get(i);
            }
        }
        return vertex;
    }

    public void readFile(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
            fileContent.add(Arrays.asList(sc.nextLine().split("  ")));
        }

        //Populate start coordinates and end coordinates into data structures
            startX = Integer.parseInt(fileContent.get(0).get(0));
            startY = Integer.parseInt(fileContent.get(0).get(1));
            endX = Integer.parseInt(fileContent.get(1).get(0));
            endY = Integer.parseInt(fileContent.get(1).get(1));

            for(int j = 2; j  < fileContent.size(); j++) {
                int x = Integer.parseInt(fileContent.get(j).get(0));
                int y = Integer.parseInt(fileContent.get(j).get(1));

                obstacles.put("(" + x + "," + y + ")", getNode("(" + x + "," + y + ")"));
            }
    }
}