package com.me.spelltower.dictionar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;



public class Trie {
	
	public TrieNode root;
	public List<Character> alfabet;
	
	public Trie(){
		root = new TrieNode('0');
		alfabet = Arrays.asList('a',	 'b',	'c',	'd',	'e',	'f',	'g',	'h', 'i','j',	'k',	'l',	'm',	'n',	'o',	'p',
			'q',	'r',	's',	't',	'u',	'v',	'w',	'x',	'y',	'z');
	}
	
	public void InsertWord(String word){
		int length = word.length();
		char[] letters = word.toCharArray();
		
		TrieNode nodCurent = root;
		
		for(int i = 0; i< length; i++){
			char literaCurenta = letters[i];
			
			//verificam daca litera apartine alfabetului
			if(!alfabet.contains(literaCurenta)){
				return;
			}
			
			/*if(nodCurent.links[ alfabet.indexOf(literaCurenta)] == null){
				nodCurent.links[ alfabet.indexOf(literaCurenta)] = new TrieNode(literaCurenta);
				}
				nodCurent = nodCurent.links[alfabet.indexOf(literaCurenta)];*/
			
			if(!nodCurent.links.containsKey(alfabet.indexOf(literaCurenta))){
				nodCurent.links.put(alfabet.indexOf(literaCurenta), new TrieNode(literaCurenta));
			}
			nodCurent = nodCurent.links.get(alfabet.indexOf(literaCurenta));
		}
		nodCurent.cuvantIntreg = true;
	}
	
	public boolean find(String cuvant){
		char[] litere = cuvant.toCharArray();
		int len = cuvant.length();
		TrieNode curent = root;
		
		int i;
		for(i = 0;i<len; i++){
			if(curent == null)
				return false;
			curent = curent.links.get(alfabet.indexOf(litere[i]));
		}
		
		if((curent == null) && i == len)
			return false;
		
		if((curent == null) && !curent.cuvantIntreg)
			return false;
		
		return true;
	}
	
	public void print(){
			
	}
	
	public static void main (String[] args) throws IOException {
		Trie trie = new Trie();
		Trie trie2 = new Trie();
		
		Scanner sc = null;
		ObjectMapper mapper = new ObjectMapper();
		
		Writer writer = null, writer2 = null;
		HashSet<String> cuvinte = new HashSet<String>();
		HashSet<String> set = new HashSet<String>();
		String hashset = null;
		String json = null;
		
		try {
			sc = new Scanner(new File("C:\\Users\\me\\Desktop\\dictionar2.txt"));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\me\\Desktop\\json.txt"), "utf-8"));
			writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\me\\Desktop\\hashset.txt"), "utf-8"));
			
			int i = 0;
			while(sc.hasNextLine()){
				String temp = sc.next();
				//System.out.println(temp);
				cuvinte.add(temp);
				trie.InsertWord(temp);
					i++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			sc.close();
		}
		catch (UnsupportedEncodingException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			System.out.println("got to finally");
			
			
			mapper.writeValue(new File("C:\\Users\\me\\Desktop\\jackson.json"), trie);
			mapper.writeValue(new File("C:\\Users\\me\\Desktop\\hashset.json"), hashset);
			//System.out.println(json);

			writer.write(json);
			writer2.write(hashset);
			System.out.println("wrote to file");
		}
		
		
		set = mapper.readValue(new File("C:\\Users\\me\\Desktop\\hashset.json"), HashSet.class);
		trie2 = mapper.readValue(new File("C:\\Users\\me\\Desktop\\jackson.json"), Trie.class);
		
		
		
		//test gson
		long startTime = System.nanoTime();
		//System.out.println(trie1.find("ca"));
		long endTime = System.nanoTime();
		
		//test simple array
		long startTime2 = System.nanoTime();
		System.out.println(set.contains("a"));
		long endTime2 = System.nanoTime();
		
		//test jackson
		long startTime3 = System.nanoTime();
		trie2.find("ca");
		long endTime3 = System.nanoTime();
		
		long duration =  endTime - startTime;
		long duration2 = endTime2 - startTime2;
		long duration3 = endTime3 - startTime3;
		
		System.out.println(new DecimalFormat("#.##########").format(duration2/1000000000.0)+" array ");
		System.out.println(new DecimalFormat("#.##########").format(duration3/1000000000.0)+" jackson");
	}
}
