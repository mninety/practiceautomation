package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Randomgenerator {
	
	public String generateRandomNumber(int length) {
		
		String chars = "0123456789";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		return str;
		}
	
	public String generateRandomNID(int length) {
		String chars = "0123456789";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		return str;
	}
	
	public String generateRandomemailID() {
		int length=10;
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		return str+"@automation.com";
		
	}
	
	public String generateRandomPassword() {
		
		int count = 8;
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*chars.length());
		builder.append(chars.charAt(character));
		}
		return builder.toString();
		/*
		int length = 8;
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		
		return str;*/
	
	}
		
    public String getDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateobject = new SimpleDateFormat("yyyy-MM-dd");//'2018-04-04
        return dateobject.format(date);
    }

	
	public String generateRandomName() {
		int length = 8;
		String chars = "abcdefghijklmnopqrstuvwxyz";
		String str = new Random().ints(length, 0, chars.length())
		                         .mapToObj(i -> "" + chars.charAt(i))
		                         .collect(Collectors.joining());
		
		return str;
	}
	
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		
		return new Random().nextInt((max - min) + 1) + min;
	}
	
	public double getRandomDoubleNumberInRange(double min, double max) {
		
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		
		return (min + new Random().nextDouble()*(max-min));
	}

}
