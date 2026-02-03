package idv.kuan.studio.libgdx.printstar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {

    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    @Override
    public void show() {
        // Prepare your screen here.
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 0.1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, "Hello World", 20.0f, 740.0f);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        if (bitmapFont != null) {
            bitmapFont.dispose();
        }
        if (spriteBatch != null) {
            spriteBatch.dispose();
        }
    }
}