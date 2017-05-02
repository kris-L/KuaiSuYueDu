package com.kris.kuaisuyuedu.util;

import java.util.Random;

public class RandomNumUtil {


	/**
	 * 生成不重复的随机数
	 * @param total
	 * @return
	 */
	public static int[] GetRandomSequence(int total) {
		int[] sequence = new int[total];
		int[] output = new int[total];

		for (int i = 0; i < total; i++) {
			sequence[i] = i;
		}
		Random random = new Random();
		int end = total - 1;
		for (int i = 0; i < total; i++) {
			int num = random.nextInt(end + 1);
			output[i] = sequence[num];
			sequence[num] = sequence[end];
			end--;
		}
		return output;
	}
	
	/**
	 * 生成不重复的随机数
	 * @param total:随机数数量 ;   maxNum：随机数最大值 0-maxNum-1 
	 * @return
	 */
	public static int[] GetRandomSequence(int total,int maxNum) {
		if (total > maxNum) {
			System.out.println("参数有误");
			return null; 
		}
		
		
		int[] sequence = new int[maxNum];
		int[] output = new int[total];

		for (int i = 0; i < maxNum; i++) {
			sequence[i] = i;
		}
		Random random = new Random();
		int end = maxNum - 1;
		for (int i = 0; i < total; i++) {
			if (end + 1 >0) {
				int num = random.nextInt(end + 1);
				output[i] = sequence[num];
				sequence[num] = sequence[end];
			}else{
				output[i] = sequence[0];
			}
			end--;
		}
		return output;
	}
	
}
