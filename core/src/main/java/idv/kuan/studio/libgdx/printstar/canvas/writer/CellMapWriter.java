package idv.kuan.studio.libgdx.printstar.canvas.writer;

import java.util.Map;
import java.util.Objects;

import idv.kuan.studio.libgdx.printstar.canvas.Point;

public class CellMapWriter implements CellWriter {
    private Map<Point, Character> cells;

    public CellMapWriter(Map<Point, Character> cells) {
        this.cells = Objects.requireNonNull(cells);
    }

    public void set(int x, int y, char signal) {
        cells.put(new Point(x, y), signal);
    }

    public void set(int x, int y, String signal) {
        cells.put(new Point(x, y), signal.trim().charAt(0));
    }

    public void clear(int x, int y) {
        cells.remove(new Point(x, y));
    }
}