// =======================
// 說明：用來畫滿地紅、青天
// size = 高度；寬度用 3:2 比例（可自行調整）
// =======================
package idv.kuan.studio.libgdx.printstar.shape;


import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

public class Rect extends Shape {

    private final Material material;

    public Rect(Material material) {
        this.material = material;
    }

    @Override
    protected Point draw(CellWriter writer, int size) {
        int height = Math.max(1, size);
        int width = Math.max(1, height * 3 / 2);

        for (int gridY = 0; gridY < height; gridY++) {
            for (int gridX = 0; gridX < width; gridX++) {
                writer.set(gridX, gridY, material.token());
            }
        }

        return new Point(width / 2, height / 2);
    }
}