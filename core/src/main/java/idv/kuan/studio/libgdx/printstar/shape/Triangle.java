package idv.kuan.studio.libgdx.printstar.shape;

import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public class Triangle extends  Shape {

    @Override
    protected void draw(CellWriter printer, int size) {
        String character = "*";

        int start = size / 2;
        int end = size / 2;
        for (int y = 0; ; y++) {
            for (int x = 0; x < size; x++) {
                if (x >= start && x <= end) {
                    printer.set(x, y, character);
                }
            }
            start -= 1;
            end += 1;
            if(start<0){
                break;
            }
        }
    }
}
