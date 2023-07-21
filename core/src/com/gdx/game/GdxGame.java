package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.gdx.game.consoleGame.main.Main;
import com.gdx.game.consoleGame.units.*;

import java.util.Random;

public class GdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background, crossBowMan, mage, monk, peasant, rouge, sniper, spearMan;
	Music music;
	Main game;
	boolean play;
	
	@Override
	public void create () {
		play = true;
		game = new Main();
		game.main();

		batch = new SpriteBatch();
		background = new Texture("backgrounds/" +
				Backgrounds.values()[new Random().nextInt(Backgrounds.values().length)] + ".png");
		music = Gdx.audio.newMusic(Gdx.files.internal("music/" +
				"paul-romero-rob-king-combat-theme-0" + (new Random().nextInt(4) + 1) +
				".mp3"));
		music.setLooping(true);
		music.setVolume(0.20F);
		music.play();

		crossBowMan = new Texture("units/CrossBowMan.png");
		mage  = new Texture("units/Mage.png");
		monk = new Texture("units/Monk.png");
		peasant = new Texture("units/Peasant.png");
		rouge = new Texture("units/Rouge.png");
		sniper = new Texture("units/Sniper.png");
		spearMan = new Texture("units/SpearMan.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		int kx = Gdx.graphics.getWidth()/11;
		int ky = Gdx.graphics.getHeight()/12;
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (BaseCharacter hero : game.teamWhite) {
			if (hero instanceof Monk) batch.draw(monk, hero.coordinates.x * kx, hero.coordinates.y * ky);
			if (hero instanceof Former) batch.draw(peasant, hero.coordinates.x * kx, hero.coordinates.y * ky);
			if (hero instanceof Sniper) batch.draw(sniper, hero.coordinates.x * kx, hero.coordinates.y * ky);
			if (hero instanceof Lanceman) batch.draw(spearMan, hero.coordinates.x * kx, hero.coordinates.y * ky);
		}

		for (BaseCharacter hero : game.teamBlack) {
			if (hero.getHealth() == 0) continue;
			if (hero instanceof Crossbowman) batch.draw(crossBowMan, hero.coordinates.x * kx, hero.coordinates.y * ky, -crossBowMan.getWidth(), crossBowMan.getHeight());
			if (hero instanceof Wizard) batch.draw(mage, hero.coordinates.x * kx, hero.coordinates.y * ky, -mage.getWidth(), mage.getHeight());
			if (hero instanceof Peasant) batch.draw(peasant, hero.coordinates.x * kx, hero.coordinates.y * ky, -peasant.getWidth(), peasant.getHeight());
			if (hero instanceof Robber) batch.draw(rouge, hero.coordinates.x * kx, hero.coordinates.y * ky, -rouge.getWidth(), rouge.getHeight());
		}

//		for (BaseCharacter hero : game.fightingTeams) {
//			if (hero instanceof Crossbowman) batch.draw(crossBowMan, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Wizard) batch.draw(mage, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Monk) batch.draw(monk, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Peasant) batch.draw(peasant, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Robber) batch.draw(rouge, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Sniper) batch.draw(sniper, hero.coordinates.x * kx, hero.coordinates.y * ky);
//			if (hero instanceof Lanceman) batch.draw(spearMan, hero.coordinates.x * kx, hero.coordinates.y * ky);
//		}
		
		//		batch.draw(crossBowMan, 0, 0);
		batch.end();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) & play){
			if (!game.run()){
				play = false;
				Gdx.graphics.setTitle("Game over!");
				music.stop();
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		music.dispose();
		crossBowMan.dispose();
		mage.dispose();
		monk.dispose();
		peasant.dispose();
		rouge.dispose();
		sniper.dispose();
		spearMan.dispose();
	}
}
