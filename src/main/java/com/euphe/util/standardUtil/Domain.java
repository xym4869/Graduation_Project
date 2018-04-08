package com.euphe.util.standardUtil;

import org.junit.*;

/*
 * 该类用来存放一个MAC地址在某个类的使用时间记录情况
 * 例如：f4:29:81:a2:3b:43在社交类上的使用时间情况
 */
public class Domain {
	private int media = 0;
	private int socialContact = 0;
	private int knowledge = 0;
	private int life = 0;
	private int shopping = 0;
	private int finance = 0;
	private int phone = 0;
	private int tool = 0;
	private int infomation = 0;
	private int nullDuration = 0;
	private int[] categories = new int[9];
	private String type = "";

	public void put(String category, int duration) {
		switch (category) {
		case "媒体": {
			this.media += duration;
			break;
		}
		case "社交": {
			this.socialContact += duration;
			break;
		}
		case "知识": {
			this.knowledge += duration;
			break;
		}
		case "生活": {
			this.life += duration;
			break;
		}
		case "购物": {
			this.shopping += duration;
			break;
		}
		case "财经": {
			this.finance += duration;
			break;
		}
		case "手机": {
			this.phone += duration;
			break;
		}
		case "工具": {
			this.tool += duration;
			break;
		}
		case "信息": {
			this.infomation += duration;
			break;
		}
		default:
			this.nullDuration += duration;
			break;
		}
	}
	
	public String toString(){
		whichCategory();
		return this.media + "\t" + this.socialContact + "\t" + this.knowledge + "\t"
				+this.life + "\t" + this.shopping + "\t" + this.finance + "\t"
				+this.phone + "\t" + this.tool + "\t" + this.infomation + "\t" 
				+this.nullDuration + "\t" + this.type;
	}
	
	public String whichCategory(){
		getCategories();
		int index = findMaxIndex(categories);
		switch (index) {
		case 0: {
			this.type = "媒体";
			break;
		}
		case 1: {
			this.type = "社交";
			break;
		}
		case 2: {
			this.type = "知识";
			break;
		}
		case 3: {
			this.type = "生活";
			break;
		}
		case 4: {
			this.type = "购物";
			break;
		}
		case 5: {
			this.type = "财经";
			break;
		}
		case 6: {
			this.type = "手机";
			break;
		}
		case 7: {
			this.type = "工具";
			break;
		}
		case 8: {
			this.type = "信息";
			break;
		}
		default:
			this.type = null;
			break;
		}
		return type;
	}

	private int[] getCategories() {
		this.categories[0] = this.media;
		this.categories[1] = this.socialContact;
		this.categories[2] = this.knowledge;
		this.categories[3] = this.life;
		this.categories[4] = this.shopping;
		this.categories[5] = this.finance;
		this.categories[6] = this.phone;
		this.categories[7] = this.tool;
		this.categories[8] = this.infomation;
		return categories;
	}
	
	private int findMaxIndex(int[] numbers){
		int max = numbers[0];
		int index = 0;
		for(int i = 1;i<numbers.length;i++){
			if(max < numbers[i]){
				index = i;
				max = numbers[i];
			}
		}
		//如果最大值为0,则说明该类里，所有类型的使用时间均为0。返回-1
		if(max == 0){
			return -1;
		}
		else return index;
	}

	@Test
	public void max(){
		System.out.println(toString());
	}
	
}
