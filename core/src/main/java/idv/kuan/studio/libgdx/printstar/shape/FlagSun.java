package idv.kuan.studio.libgdx.printstar.shape;

import java.util.Map;

import idv.kuan.studio.libgdx.printstar.canvas.Point;
import idv.kuan.studio.libgdx.printstar.canvas.writer.CellWriter;

/**
 * 白日（組裝器）：圓心(W) + 圓環(B) + 12光芒(W)
 * <p>
 * 規則：
 * - size = 青天高度（cantonHeight）
 * - 圓心半徑 = max(2, size/8)  （你指定）
 * - 光芒長度縮短：尖端半徑 = size*3/8
 */
public class FlagSun extends Shape {

    private static final int RAY_COUNT = 12;

    @Override
    protected Point draw(CellWriter writer, int size) {
        int cantonHeight = Math.max(24, size);

        int centerX = cantonHeight / 2;
        int centerY = cantonHeight / 2;

        // 1) 你指定：圓心半徑 size/8
        int diskRadius = Math.max(2, cantonHeight / 8);

        // 2) 圓環：用來隔開圓心與光芒
        int ringThickness = Math.max(1, cantonHeight / 50);
        int ringInnerRadius = diskRadius;
        int ringOuterRadius = diskRadius + ringThickness;

        // 3) 光芒長度縮短：尖端半徑改成 3/8 * size（比 1/2 短）
        int rayBaseRadius = ringOuterRadius + 1;
        int rayTipRadius = Math.max(rayBaseRadius + 2, (cantonHeight * 3) / 8);

        // 4) 光芒底寬：越小越尖（可微調）
        int rayBaseWidth = Math.max(3, cantonHeight / 14);

        // === 模組化：各自 Shape 負責幾何 ===
        FilledCircle disk = new FilledCircle(Material.WHITE, diskRadius);
        Ring ring = new Ring(Material.BLUE, ringInnerRadius, ringOuterRadius);
        SunRayTriangle rayTriangle = new SunRayTriangle(Material.WHITE, rayBaseWidth, rayBaseRadius, rayTipRadius);

        // 先畫圓心（W）
        disk.draw(cantonHeight);
        blit(writer, disk, 0, 0);

        // 再畫圓環（B）
        ring.draw(cantonHeight);
        blit(writer, ring, 0, 0);

        // 最後畫 12 光芒（W）
        // RayTriangle 的 draw(size) 會以「圓心 = size/2」為基準建立一個角度=0 的光芒，
        // 這裡只需要旋轉貼上。
        rayTriangle.draw(cantonHeight);

        for (int rayIndex = 0; rayIndex < RAY_COUNT; rayIndex++) {
            double angleDeg = rayIndex * (360.0 / RAY_COUNT);
            rotateBlit(writer, rayTriangle, centerX, centerY, angleDeg);
        }

        return new Point(centerX, centerY);
    }

    private void blit(CellWriter writer, Shape shape, int offsetX, int offsetY) {
        for (Map.Entry<Point, Character> entry : shape.getCells().entrySet()) {
            Point point = entry.getKey();
            Character value = entry.getValue();

            int gridX = point.getGridX() + offsetX;
            int gridY = point.getGridY() + offsetY;

            writer.set(gridX, gridY, value);
        }
    }

    /**
     * 以 shape.getPivot() 為旋轉中心，把 shape 的 cells 旋轉後寫入 writer。
     * 這讓 FlagSun 不需要知道 ray 的細節，只負責「擺放」。
     */
    private void rotateBlit(CellWriter writer, Shape shape, int targetCenterX, int targetCenterY, double angleDeg) {
        Point pivot = shape.getPivot();
        double rad = Math.toRadians(angleDeg);

        for (Map.Entry<Point, Character> entry : shape.getCells().entrySet()) {
            Point localPoint = entry.getKey();
            Character value = entry.getValue();

            int dx = localPoint.getGridX() - pivot.getGridX();
            int dy = localPoint.getGridY() - pivot.getGridY();

            double rotatedX = dx * Math.cos(rad) - dy * Math.sin(rad);
            double rotatedY = dx * Math.sin(rad) + dy * Math.cos(rad);

            int finalX = targetCenterX + (int) Math.round(rotatedX);
            int finalY = targetCenterY + (int) Math.round(rotatedY);

            writer.set(finalX, finalY, value);
        }
    }

}
