
public class Edge {

    double distance;
    Vertex start;
    Vertex destination;
    //Vertex goal;


    //designated constructor
    public Edge(Vertex start, Vertex destination) {
        this.start = start;
        this.destination = destination;
        setDistance(start, destination);
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDistance(Vertex start, Vertex destination) {
        //Source
        double startX = start.getX();
        double startY = start.getY();

        //destination
        double destinationX = destination.getX();
        double destinationY = destination.getY();
        double dX = destinationX - startX ;  //X2-X1
        double dY = destinationY - startY ;  //Y2-X1

        distance = Math.hypot(dY, dX);   //distance = sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1))
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return getStart() + ": " + getDestination();
    }


}