package utilities;


import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils
{
    public final static String DATE_FORMAT = "dd/MM/yyyy";
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public final static Scanner INPUT = new Scanner(System.in);
}
