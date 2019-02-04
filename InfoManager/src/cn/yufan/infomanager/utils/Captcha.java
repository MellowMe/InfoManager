package cn.yufan.infomanager.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {
	private int width = 100;
	private int height = 30;
	private int length = 4;
	private String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Random random = new Random();
	private static Captcha captcha;

	private Captcha() {
	}

	public static Captcha getInstance() {
		if (captcha == null)
			captcha = new Captcha();
		return captcha;
	}

	public void resize(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setDict(String dict) {
		this.dict = dict;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String generateCode(){
		StringBuilder code = new StringBuilder();
		char c;
		for(int i = 0;i < length;i++){
			c = dict.charAt(random.nextInt(dict.length()));
			code.append(c);
		}
		return code.toString();
	}

	public BufferedImage generateImg(String code){
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, width, height);
		Font font = new Font("segoe print", Font.BOLD+Font.ITALIC, 20);
		graphics.setFont(font);
		for(int i = 0;i < length;i++){
			graphics.setColor(new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256)));
			graphics.drawString(String.valueOf(code.charAt(i)), i * (width/length) + 4, (int)(height * 0.7));
		}
		for(int i = 0; i < width; i++){
			graphics.setColor(new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256)));
			graphics.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		for(int i = 0; i < random.nextInt(3) + 1; i++){
			graphics.setColor(new Color(random.nextInt(256), random.nextInt(256),random.nextInt(256)));
			graphics.drawLine(random.nextInt(width/5), random.nextInt(height), random.nextInt(width * 4/5), random.nextInt(height));
		}
		return image;
	}
}
