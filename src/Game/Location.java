package Game;

public class Location {

    private int x;	// Horizontal coordinate of the tile
    private int y;	// Vertical coordinate of the tile
    private String name;
    /**
     * This class contains the llocations of each piece.
     * It has an x coordinate.
     * It has a y coordinate.
     * And a string name
     */
    public Location(int X, int Y) {
        x = X;
        y = Y;
        name = "" + (char)('a' + x) + y;
    }
    public void printLoc(){
        System.out.print("( "+getFile()+", "+getRank()+")");
    }

    public int getRank() {
        return this.y;
    }

    public int getFile() {
        return this.x;
    }
    public String get_location_name() {
        return this.name;
     }

    public boolean isOnBoard(){
        if(x < 0 ||  x > 7 || y < 0 || y > 8){
            return false;
        }
        return true;
    }
}