package idv.kuan.studio.libgdx.printstar.canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import idv.kuan.studio.libgdx.printstar.shape.Shape;

public class CanvasBoard {
    private final List<DrawCommand> drawCommandList;

    public CanvasBoard() {
        this.drawCommandList = new ArrayList<>();
    }

    public void add(Shape shape, int gridX, int gridY, int size) {
        add(shape, gridX, gridY, size, Canvas.FLIP.NONE);
    }

    public void add(Shape shape, int gridX, int gridY, int size, Canvas.FLIP flip) {
        Objects.requireNonNull(shape, "shape must not be null");
        Objects.requireNonNull(flip, "flip must not be null");

        drawCommandList.add(new DrawCommand(shape, gridX, gridY, size, flip));
    }

    public void drawOn(Canvas canvas) {
        Objects.requireNonNull(canvas, "canvas must not be null");

        drawCommandList.forEach(c -> canvas.paint(c.shape, c.gridX, c.gridY, c.size, c.flip));
    }

    private static final class DrawCommand {
        private final Shape shape;
        private final int gridX;
        private final int gridY;
        private final int size;
        private final Canvas.FLIP flip;

        public DrawCommand(Shape shape, int gridX, int gridY, int size, Canvas.FLIP flip) {
            this.shape = shape;
            this.gridX = gridX;
            this.gridY = gridY;
            this.size = size;
            this.flip = flip;
        }
    }
}
