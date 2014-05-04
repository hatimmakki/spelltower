package com.me.spelltower.dictionar;

import java.util.HashMap;

public class TrieNode {

	public char litera;
	public HashMap<Integer, TrieNode> links;
	public boolean cuvantIntreg;
	
	public TrieNode(){
		
	}
	
	public TrieNode(char litera){
		this.litera = litera;
		links = new HashMap<Integer, TrieNode>();
	}
}
