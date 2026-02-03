package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;

public class Triangle extends Canvas.Shape {

    @Override
    protected void draw(Printer printer, int size) {
        printer.set(1 * size, 1, "*");
        printer.set(2 * size, 1, "*");
        printer.set(3 * size, 1, "*");
    }
}
