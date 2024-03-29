package util.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Console {
	protected static BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));

	public static void println() {
		System.out.println();
	}

	public static void println(Object obj) {
		System.out.println(obj.toString());
	}

	public static void println(String string) {
		System.out.println(string);
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static void printf(String format, Object... args) {
		System.out.printf(format, args);
	}

	public static Integer readInt() {
		try {

			return Integer.parseInt(kbd.readLine());

		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static Long readLong() {
		try {

			return Long.parseLong(kbd.readLine());

		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static Double readDouble() {
		try {

			return Double.parseDouble(kbd.readLine());

		} catch (NumberFormatException nfe) {
			return null;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public static String readString() {
		try {
			return kbd.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readString(String msg) {
		String res = "";
		while (res.isEmpty()) {
			print(msg + ": ");
			res = readString();
		}
		return res;
	}

	public static Long readLong(String msg) {
		Long res = null;
		while (res == null) {
			print(msg + ": ");
			res = readLong();
		}
		return res;
	}

	public static Integer readInt(String msg) {
		Integer res = null;
		while (res == null) {
			print(msg + ": ");
			res = readInt();
		}
		return res;
	}

	public static Double readDouble(String msg) {
		Double res = null;
		while (res == null) {
			print(msg + ": ");
			res = readDouble();
		}
		return res;
	}

	public static LocalDate readDate(String msg) {
		String date = Console.readString(msg);
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/MM/uuuu");

			return LocalDate.parse(date, dateFormat);
		} catch (DateTimeParseException dte) {
			Console.println("Wrong date format. It must be dd/mm/yyyy");
			return readDate(msg);
		}
	}

	public static void waitForEnterKey() {
		print("Press Enter Key to continue...");
		readString();
	}

}
