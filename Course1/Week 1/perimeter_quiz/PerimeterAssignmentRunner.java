import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        // init variable to store the number of points
        int ptsCount = 0;
        // iter through all the points
        for (Point currPt : s.getPoints()) {
            ptsCount = ptsCount + 1;            
        }        
        return ptsCount;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // The averange length is equal to the perimeter divided by the number of points
        double totalPerim = getPerimeter(s);
        int ptsCount = getNumPoints(s);
        double avgLength = totalPerim / ptsCount;        
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // Init variables
        double maxLength = 0;
        Point prevPt = s.getLastPoint();
        // Iter through all the sides of the shape
        for (Point currPt : s.getPoints()) {
            // Compute the distance between this point and the previous one)
            double thisLength = prevPt.distance(currPt);
            // If this length is greater than the max, it is redefined as the max
            if (thisLength > maxLength) {
                maxLength = thisLength;
            }
            // Redifine this point as the previous
            prevPt = currPt;                                  
        }                        
        return maxLength;
    }

    public double getLargestX(Shape s) {
        // Put code here
        // Init variable at extremely low value
        double maxX = -999999;
        for (Point currPt : s.getPoints()) {
            double thisX = currPt.getX();
            // Check if it is the new max
            if (thisX > maxX) {
                // Redefine
                maxX = thisX;
            }            
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        // Init output varible
        double maxPerim = 0;
        // Create a directory with the selected files
        DirectoryResource dr = new DirectoryResource();
        // Iterate through all the files
        for (File f : dr.selectedFiles()) {
            // Create a new file resource
            FileResource fr = new FileResource(f);
            // Create a shape from this file
            Shape s = new Shape(fr);
            // Compute the perimeter of this shape
            double thisPerim = getPerimeter(s);
            // Replace maxPerim if thisPerim is larger
            if (thisPerim > maxPerim) {
                maxPerim = thisPerim;
            }                        
        }
        return maxPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        // Init output varible
        double maxPerim = 0;
        // Create a directory with the selected files
        DirectoryResource dr = new DirectoryResource();
        // Iterate through all the files
        for (File f : dr.selectedFiles()) {
            // Create a new file resource
            FileResource fr = new FileResource(f);
            // Create a shape from this file
            Shape s = new Shape(fr);
            // Compute the perimeter of this shape
            double thisPerim = getPerimeter(s);
            // Replace maxPerim if thisPerim is larger
            if (thisPerim > maxPerim) {
                maxPerim = thisPerim;
                temp = f;
            }                        
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // Compute and print points count
        int ptsCount = getNumPoints(s);
        System.out.println("ptsCount = " + ptsCount);
        // Compute and print avgLength
        double avgLength = getAverageLength(s);
        System.out.println("avgLength = " + avgLength);
        // Compute and print maxLength
        double maxLength = getLargestSide(s);
        System.out.println("Largest side = " + maxLength);
        // Compute and print maxX
        double maxX = getLargestX(s);
        System.out.println("Largest X = " + maxX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        // Directily call the method to compute max perimeter, as it takes no args
        double maxPerim = getLargestPerimeterMultipleFiles();
        System.out.println("perimeter = " + maxPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        // Call the method to print the file name with max perimeter
        String maxF = getFileWithLargestPerimeter();
        System.out.println("File with maxPerim = " + maxF);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
