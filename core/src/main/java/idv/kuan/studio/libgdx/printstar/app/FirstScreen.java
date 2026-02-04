package idv.kuan.studio.libgdx.printstar.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import idv.kuan.studio.libgdx.printstar.canvas.Canvas;
import idv.kuan.studio.libgdx.printstar.canvas.CanvasBoard;
import idv.kuan.studio.libgdx.printstar.shape.FlagSun;
import idv.kuan.studio.libgdx.printstar.shape.Rect;
import idv.kuan.studio.libgdx.printstar.shape.Shape;


public class FirstScreen extends ScreenAdapter {

    private SpriteBatch spriteBatch;

    private Canvas canvas;
    private CanvasBoard canvasBoard;


    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        canvas = new Canvas();
        canvas.getTransformer().setCellSize(0.6f);

        canvasBoard = new CanvasBoard();

        int flagHeight =160;
        int flagWidth = flagHeight * 3 / 2;

        // 1) 滿地紅（整面）
        canvasBoard.add(new Rect(Shape.Material.RED), 0, 0, flagHeight);

        // 2) 左上青天（高度一半、寬度一半）
        canvasBoard.add(new Rect(Shape.Material.BLUE), 0, 0, flagHeight / 2);

        // 3) 白日（置中於青天）
        int sunSize = flagHeight / 2 + 10;
        int sunX = (flagWidth / 4) - (sunSize / 2);
        int sunY = (flagHeight / 4) - (sunSize / 2);

        canvasBoard.add(new FlagSun(), sunX, sunY, sunSize);


        canvasBoard.drawOn(canvas);

        Gdx.app.log("Print Star", "\n" + canvas.exportAsString());
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        spriteBatch.begin();
        canvas.render(spriteBatch, 80.0f, 1040.0f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
