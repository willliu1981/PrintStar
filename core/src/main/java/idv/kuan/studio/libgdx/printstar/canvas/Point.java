package idv.kuan.studio.libgdx.printstar.canvas;

import java.util.Objects;

public class Point {
    private int gridX;
    private int gridY;

    public Point() {
        gridX = 0;
        gridY = 0;
    }

    public Point(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public Point(Point point) {
        if (point != null) {
            this.gridX = point.gridX;
            this.gridY = point.gridY;
        } else {
            throw new NullPointerException("Point is null");
        }
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (!(otherObject instanceof Point)) {
            return false;
        }
        Point otherPoint = (Point) otherObject;
        return gridX == otherPoint.gridX && gridY == otherPoint.gridY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gridX, gridY);
    }
}