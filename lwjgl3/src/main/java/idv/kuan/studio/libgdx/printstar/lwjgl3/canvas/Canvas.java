package idv.kuan.studio.libgdx.printstar.lwjgl3.canvas;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.lwjgl3.position.Point;

public class Canvas {
    private Map<Point, Character> position;
    private int width;
    private int height;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        position = new HashMap<>();
    }

    public Canvas() {
        this(32, 32);
    }


    public void draw(Shape shape, int x, int y, int size) {
        shape.position.forEach((position, signal) -> {
            int cx = position.getX() + x;
            int cy = position.getY() + y;
            this.position.put(new Point(cx, cy), signal);
        });
    }

    public static abstract class Shape {
        private Map<Point, Character> position;

        public Shape() {
            position = new HashMap<>();
        }

        protected abstract void draw(Printer printer);


        public class Printer {
            public void set(int x, int y, char signal) {
                position.put(new Point(x, y), signal);
            }

            public void set(int x, int y, String signal) {
                position.put(new Point(x, y), signal.trim().charAt(0));
            }

            public void clear(int x, int y) {
                position.remove(new Point(x, y));
            }
        }
    }


}
