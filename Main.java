//Assignmenr 3: Implement Dijkstra's Algorithm
//Briana Grant

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws FileNotFoundException
    {
        //File input required with start on first line & end on second line. X AND Y VALUES MUST BE SEPARATED BY TWO SPACES "  "
        System.out.println("Please enter file path: ");
        Scanner sc = new Scanner(System.in);

        String filePath = sc.nextLine();
        Graph graph = new Graph(filePath);
    }
}
