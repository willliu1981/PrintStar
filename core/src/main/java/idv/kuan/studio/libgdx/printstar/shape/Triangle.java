package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public class Triangle extends Shape {

    @Override
    protected Point draw(CellWriter printer, int size) {
        String character = "*";

        int start = size / 2;
        int end = size / 2;
        int countY = 0;
        for (int y = 0; ; y++) {
            countY++;
            for (int x = 0; x < size; x++) {
                if (x >= start && x <= end) {
                    printer.set(x, y, character);
                }
            }
            start -= 1;
            end += 1;
            if (start < 0) {
                break;
            }
        }

        return new Point(size / 2, countY / 2);
    }
}
