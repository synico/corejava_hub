package com.util.encryption;

import java.util.ArrayDeque;
import java.util.Base64;
import java.util.Queue;

public class Base64Tool {
	
	private static String encode(String input) {
//		byte[] items = Base64.getEncoder().encode(input.getBytes());
//		String rst = new String(items);
		
		String rst = Base64.getEncoder().encodeToString(input.getBytes());
		
		System.out.println(rst);
		String head4chars = rst.substring(0, 4);
		System.out.println("head4chars : " + head4chars);
		int endIdx = rst.length() - 4;
		String tail4chars = rst.substring(endIdx);
		System.out.println("tail4chars : " + tail4chars);
		String str2mix = rst.substring(4, endIdx);
		System.out.println(str2mix);
		
		//start to mix
		Queue<Character> odds = new ArrayDeque<>();
		Queue<Character> evens = new ArrayDeque<>();
		for(int i = 0;i < str2mix.length();i++) {
			if(i % 2 == 0) {
				evens.add(str2mix.charAt(i));
			} else {
				odds.add(str2mix.charAt(i));
			}
		}
		StringBuilder mixedStr = new StringBuilder();
		mixedStr.append(head4chars);
		while(!evens.isEmpty() || !odds.isEmpty()) {
			mixedStr.append(odds.poll());
			mixedStr.append(evens.poll());
		}
		mixedStr.append(tail4chars);
		System.out.println(mixedStr.toString());
		return mixedStr.toString();
	}
	
	private static void decode(String input) {
		String head4chars = input.substring(0, 4);
		System.out.println("head4chars : " + head4chars);
		int endIdx = input.length() - 4;
		String tail4chars = input.substring(endIdx);
		System.out.println("tail4chars : " + tail4chars);
		String str2mix = input.substring(4, endIdx);
		System.out.println(str2mix);
		
		//start to mix
		Queue<Character> odds = new ArrayDeque<>();
		Queue<Character> evens = new ArrayDeque<>();
		for(int i = 0;i < str2mix.length();i++) {
			if(i % 2 == 0) {
				evens.add(str2mix.charAt(i));
			} else {
				odds.add(str2mix.charAt(i));
			}
		}
		StringBuilder mixedStr = new StringBuilder();
		mixedStr.append(head4chars);
		while(!evens.isEmpty() || !odds.isEmpty()) {
			mixedStr.append(odds.poll());
			mixedStr.append(evens.poll());
		}
		mixedStr.append(tail4chars);
		byte[] rst = Base64.getDecoder().decode(mixedStr.toString());
		System.out.println(new String(rst));
	}

	public static void main(String[] args) {
		String encodedStr = Base64Tool.encode("login=AAA&password=BBB");
		System.out.println("---------------------------------------------");
		Base64Tool.decode(encodedStr);
	}

}
