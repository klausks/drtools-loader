package drtools.loader.utils;

public class ParsingUtils {
    // Accepts a string in the format dd,d...% (eg. 81,72%) and returns its double representation
    public static Double parsePercentValue(String s) {
        return Double.parseDouble("0." + s.replaceAll("[,%]", ""));
    }
}
