package idv.kuan.studio.libgdx.printstar.shape;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellMapWriter;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public abstract class Shape {
    private Map<Point, Character> cells;

    public Shape() {
        cells = new HashMap<>();
    }

    public void draw(int size) {
        CellWriter printer = new CellMapWriter(cells);
        draw(printer, size);
    }


    protected abstract void draw(CellWriter cellMapWriter, int size);

    //getter and setter


    public Map<Point, Character> getCells() {
        return cells;
    }


}
