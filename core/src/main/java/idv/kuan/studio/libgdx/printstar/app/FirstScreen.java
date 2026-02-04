package idv.kuan.studio.libgdx.printstar.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.canvas.CanvasBoard;
import idv.kuan.studio.libgdx.printstar.canvas.matrix.CanvasMatrix;
import idv.kuan.studio.libgdx.printstar.shape.Circle;
import idv.kuan.studio.libgdx.printstar.shape.Triangle;
import idv.kuan.studio.libgdx.printstar.transform.CanvasTransformer;


public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    private Canvas canvas;
    private CanvasBoard canvasBoard;


    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        canvas = new Canvas();
        canvas.getTransformer().setCellSize(2.0f);

        canvasBoard = new CanvasBoard();
        canvasBoard.add(new Triangle(), 0, 0, 7, Canvas.FLIP.FLIP_Y);
        canvasBoard.add(new Triangle(), 0, 0, 7);
        canvasBoard.add(new Circle(), 10, 0, 30);

        canvasBoard.drawOn(canvas);

        System.out.println("print star:\n" + canvas.exportAsString());

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        String gridString = canvas.exportAsString();

        bitmapFont.getData().setScale(2.0f);

        spriteBatch.begin();
        canvas.render(spriteBatch, 240.0f, 800.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
