package com.me.spelltower.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.spelltower.dictionar.Trie;
import com.me.spelltower.model.Litera;
import com.me.spelltower.model.SpellTowerGame;

public class Assets {

	private static final Assets instance = new Assets();
	private TextureAtlas atlas ;
	private Trie trie;

	private ArrayList<Integer> frecventaLiterelor;
	private ObjectMapper mapper;
	private HashSet<String> hashSet;
	private Litera matriceLitere[][];
	private ArrayList<String> alfabetGenerat;

	private Assets(){}
	public static Assets getInstance(){
		return instance;
	}

	public void load(){
		//load atlas
		atlas = new TextureAtlas(Gdx.files.internal("data/atlasLitere.atlas"));

		//load dictionary
		mapper = new ObjectMapper();

		//genereaza matricea de litere
		

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
		int[] frecventaCumulativa = SpellTowerGame.frecventaCumulativa;


		for(int i = 0; i<77;i++){
			int rand = new Random().nextInt(647897);

			int index = Arrays.binarySearch(frecventaCumulativa, rand);

			System.out.println(index);
			alfabetGenerat.add(SpellTowerGame.alfabet[Math.abs(index)-1]);

			Collections.shuffle(alfabetGenerat);
		}
	}

	private void genereazaMatricea(){

		int indexAlfabet = 0;
		int pozCol = 2;

		for(int i = 0; i<11; i++){

			int pozLinie = 2;

			for(int j = 0; j<7; j++){
				Litera t = new Litera(alfabetGenerat.get(indexAlfabet), pozLinie, pozCol);
				matriceLitere[i][j] = t;
				pozLinie += 67;
				indexAlfabet++;
			}
			pozCol += 67;
		}
	}
}
