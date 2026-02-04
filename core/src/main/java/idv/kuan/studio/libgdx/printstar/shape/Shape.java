package idv.kuan.studio.libgdx.printstar.shape;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellMapWriter;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public abstract class Shape {
    private Map<Point, Character> cells;
    private Point pivot;

    public Shape() {
        cells = new HashMap<>();
    }

    public void draw(int size) {
        CellWriter printer = new CellMapWriter(cells);
        pivot = draw(printer, size);
        if (pivot == null) {
            pivot = new Point();
        }
    }


    /**
     * @param cellMapWriter
     * @param size
     * @return flip pivot
     */
    protected abstract Point draw(CellWriter cellMapWriter, int size);

    //getter and setter


    public Map<Point, Character> getCells() {
        return cells;
    }

    public Point getPivot() {
        return pivot;
    }


    //classes
    // ===== 材質設計：青 / 白 / 紅 =====

    public enum Material {
        RED("-"),
        BLUE("."),
        WHITE("+");

        private final String token;

        Material(String token) {
            this.token = token;
        }

        public String token() {
            return token;
        }
    }
}
