import static java.lang.Double.min;
import static java.lang.Math.abs;

public class Vertex implements Comparable<Vertex> {
    private String coordinate;
    private int number;
    private int x;
    private int y;
    private double h;

    //Initialize all vertex nodes to infinity
    private Double dist = Double.MAX_VALUE;

    //designated constructor
    public Vertex(String coordinate, int x, int y) {
        this.coordinate = coordinate;
        this.x = x;
        this.y = y;
    }

    public Vertex() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDist(double cost) {
        dist = cost;
    }

    public double getDist() {
        return dist;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public double getH(){
        return h;
    }

    public void setH(Vertex destination) {
        //Source
        double startX = this.getX();
        double startY = this.getY();

        //Destination
        double destinationX = destination.getX();
        double destinationY = destination.getY();

        double dX = abs(startX-destinationX);
        double dY = abs(startY-destinationY);


       h = 1*(dX + dY) + (Math.sqrt(2) - 2) * min(dX,dY);
    }

    @Override
    public String toString() {
        return getCoordinate();
    }


   @Override
    public int compareTo(Vertex o) {
        return Double.compare(getDist(),o.getDist());
    }

    //With Heuristics
    /*@Override
    public int compareTo(Vertex o) {
        return Double.compare(getH(),o.getH());
    }*/
}