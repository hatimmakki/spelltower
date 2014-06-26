package com.me.spelltower.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.spelltower.dictionar.Trie;
import com.me.spelltower.model.BitmapFont_XY;
import com.me.spelltower.model.FontAccessor;
import com.me.spelltower.model.Litera;
import com.me.spelltower.model.LiteraAccessor;

public class Assets {

	//alfabetul
	public final static String[] alfabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
		"r","s","t","u","v","w","x","y","z"};

	/**
	 * Frecventa cumulativa precalculata a literelor din alfabetul Limbii Române, pentru a genera o matrice aleatoare a acestora
	 * cu frecvente balansate.
	 */
	public static int[] frecventaCumulativa = {84968,95895,130674,145541,207374,216602,228448,234831,309492,311836,
		312095,340568,361371,399432,442484,462317,462361,520474,550248,605395,630894,638369,638467,640001,640166,647897};
	
	//Lista de scoruri maxime
	ArrayList<Integer> scoresList;
	
	AssetManager manager = new AssetManager();

	private static final Assets instance = new Assets();
	private TextureAtlas atlas ;
	private Trie trie;

	private ArrayList<Integer> frecventaLiterelor;
	private ObjectMapper mapper;
	private HashSet<String> hashSet;
	private Litera matriceLitere[][];
	private ArrayList<String> alfabetGenerat;
	private BitmapFont fontShadow;
	private Sound eCuvantSound;
	
	//ui
	private TextButtonStyle buttonStyle;
	private Skin skin;
	private TextureAtlas atlasButoane;
	private BitmapFont font;
	private BitmapFont fontWhite;
	
	private Assets(){}
	public static Assets getInstance(){
		return instance;
	}

	//incarcam resursele in memorie
	public void load(){
		
		//lista de scoruri
		scoresList = new ArrayList<Integer>();
		scoresList.add(65);
		scoresList.add(654);
		scoresList.add(21);
		scoresList.add(86);
		scoresList.add(36);
		Collections.sort(scoresList);
		
		//load atlas
		atlas = new TextureAtlas(Gdx.files.internal("data/atlasLitere.atlas"));

		//load dictionary
		mapper = new ObjectMapper();

		//register tween accessor
		Tween.registerAccessor(Litera.class, new LiteraAccessor());
		Tween.registerAccessor(BitmapFont_XY.class, new FontAccessor());
		Tween.setWaypointsLimit(3);

		//genereaza matricea de litere
		matriceLitere = new Litera[11][7];
		genereazaAlfabet();
		genereazaMatricea();
		
		//butoane
		atlasButoane = new TextureAtlas(Gdx.files.internal("ui/buttons.pack"));
		skin = new Skin(atlasButoane);
		fontShadow = new BitmapFont(Gdx.files.internal("ui/fontShadow.fnt"));
		font = new BitmapFont(Gdx.files.internal("ui/font.fnt"));
		fontWhite = new BitmapFont(Gdx.files.internal("ui/fontWhite.fnt"));
		
		buttonStyle = new TextButtonStyle(skin.getDrawable("button1"),
												    skin.getDrawable("button2"),
													 skin.getDrawable("button1"), 
													 fontShadow);
		

		eCuvantSound = Gdx.audio.newSound(Gdx.files.internal("sounds/success.ogg"));
		
		InputStream stream = null;

		try{
			stream = Gdx.files.internal("data/hashset.json").read();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		try {
			hashSet = mapper.readValue(stream , HashSet.class);

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Integer> getFrecventaLiterelor () {
		return frecventaLiterelor;
	}

	public TextureRegion[] getRegions(String litera){
		return new TextureRegion[]{atlas.findRegion(litera+1), atlas.findRegion(litera+2), atlas.findRegion(litera+3)};
	}

	public TextureRegion getTextureRegion(String name){
		return atlas.findRegion(name);
	}

	public boolean eCuvant(String cuvant){
		return hashSet.contains(cuvant);
	}

	private void genereazaAlfabet(){
		alfabetGenerat = new ArrayList<String>();

		for(int i = 0; i<77;i++){
			int rand = new Random().nextInt(647897);

			int index = Arrays.binarySearch(frecventaCumulativa, rand);

			alfabetGenerat.add(alfabet[Math.abs(index)-1]);

			Collections.shuffle(alfabetGenerat);
		}
	}

	private void genereazaMatricea(){

		int indexAlfabet = 0;
		int pozCol = 2;

		for(int i = 10; i>= 0; i--){

			int pozLinie = 2;

			for(int j = 0; j <7; j++){
				Litera t = new Litera(alfabetGenerat.get(indexAlfabet), pozLinie, pozCol, i, j);
				matriceLitere[i][j] = t;
				pozLinie += 67;
				indexAlfabet++;
			}
			pozCol += 67;
		}
	}

	public Litera[][] getMatriceLitere () {
		return matriceLitere;
	}

	public BitmapFont_XY getFont(){
		BitmapFont_XY font =  new BitmapFont_XY(Gdx.files.internal("data/consolasWhite.fnt"), 0, 0);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		return font;
	}
	
	public Sound getECuvantSound(){
		return eCuvantSound;
	}
	public  TextButtonStyle getButtonStyle(){
		return buttonStyle;
	}
	public ArrayList<Integer> getListaScoruri(){
		return scoresList;
	}
	public BitmapFont getUIFontShadow(){
		return fontShadow;
	}
	public BitmapFont getUIFont(){
		return font;
	}
	public BitmapFont getUIFontWhite(){
		return fontWhite;
	}
}
