package com.me.spelltower.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.spelltower.dictionar.Trie;

public class Assets {

	private static final Assets instance = new Assets();
	private TextureAtlas atlas ;
	private Trie trie;

	private ArrayList<Integer> frecventaLiterelor;
	private ObjectMapper mapper;
	private HashSet<String> hashSet;

	private Assets(){}
	public static Assets getInstance(){
		return instance;
	}

	public void load(){
		//load atlas
		atlas = new TextureAtlas(Gdx.files.internal("data/atlasLitere.atlas"));

		//load dictionary
		mapper = new ObjectMapper();


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
}
