package idv.kuan.studio.libgdx.printstar.canvas;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

import idv.kuan.studio.libgdx.printstar.position.Point;

public class Canvas {
    private Map<Point, Character> position;
    private float unitSize;
    private float gap;

    public Canvas() {
        position = new HashMap<>();
        gap = 10.0f;
        unitSize = 2.0f;
    }


    public void draw(Shape shape, int x, int y, int size) {
        shape.draw(size);

        shape.position.forEach((position, signal) -> {
            int cx = position.getX() + x;
            int cy = position.getY() + y;
            this.position.put(new Point(cx, cy), signal);
        });
    }

    public void draw(SpriteBatch spriteBatch, float x, float y) {
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(unitSize);

        this.position.forEach((position, signal) -> {
            bitmapFont.draw(spriteBatch, signal.toString(), position.getX() * gap + x, position.getY() * gap + y);
        });
    }

    public static abstract class Shape {
        private Map<Point, Character> position;
        private Printer printer;

        public Shape() {
            position = new HashMap<>();
            printer = new Printer();
        }

        private void draw(int size) {
            draw(printer, size);
        }


        protected abstract void draw(Printer printer, int size);


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
