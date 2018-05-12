package com.imdb.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Application file reader class absolute file path method
 * @author syedh
 *
 */
public class Utilities {

	static String path = getPath();

	/**
	 * Read application file
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String readApplicationFile(String key) throws Exception {
		String value = "";
		try {
			Properties prop = new Properties();
			File src = new File(path + "/resources/TestData/test.properties");
			if (src.exists()) {
				prop.load(new FileInputStream(src));
				value = prop.getProperty(key);
			} else {
				throw new Exception("File not found");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (value == null) {
			throw new Exception("Key not found in properties file");

		}
		return value;

	}

	/**
	 * Get absolute path
	 * 
	 * @return
	 */
	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}
	
	/**
	 * input min and max value for random number generation
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int generateRandomNumber(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max);
		return randomNum;
	}

	
	/**
	 * Generate random numbers
	 * 
	 * @param lettersNum
	 * @return
	 */
	public static String generateRandomNumber(int lettersNum) {
		String finalString = "9";
		int letter;
		for (int i = 0; i < lettersNum - 1; i++) {
			letter = generateRandomNumber(0, 9);
			finalString += String.valueOf(letter);
		}
		return finalString;
	}

	/**
	 * 
	 * @param lettersNum
	 * @return
	 */
	public static String generateRandomString(int lettersNum) {
		String finalString = "";

		int numberOfLetters = 25;
		long randomNumber;
		for (int i = 0; i < lettersNum; i++) {
			char letter = 97;
			randomNumber = Math.round(Math.random() * numberOfLetters);
			letter += randomNumber;
			finalString += String.valueOf(letter);
		}
		return finalString;
	}

}
