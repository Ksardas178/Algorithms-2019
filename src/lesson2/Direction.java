package lesson2;

public enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    public int offsetX;
    public int offsetY;

    //private
    Direction(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Direction reverse(){
        if (offsetX==1 && offsetY==0) return UP;
        else if (offsetX==-1 && offsetY==0) return DOWN;
        else if (offsetX==0 && offsetY==1) return LEFT;
        return RIGHT;
    }

}
