//import java.util.regex.Pattern;

public class Regex {
	
	
	public static boolean isNum(String token) {
        String regex = "\\d+";
        return token.matches(regex);

        //Pattern pattern = Pattern.compile("\\d+");
        //return pattern.matcher(token).matches();
    }
	
	public static boolean isOP(String token) {
        String regex = "^[-+*/]$";
        return token.matches(regex);

        //Pattern pattern = Pattern.compile("^[-+*/]$");
        //return pattern.matcher(token).matches();
    }

    public static boolean isPlus(String token) {
        String regex = "^[+]$";
        return token.matches(regex);
    }

    public static boolean isMinus(String token) {
        String regex = "^[-]$";
        return token.matches(regex);
    }

    public static boolean isStar(String token) {
        String regex = "^[*]$";
        return token.matches(regex);
    }
    public static boolean isSlash(String token) {
        String regex = "^[/]$";
        return token.matches(regex);
    }


    public static void main(String[] args) {

      
        
        

        System.out.println(isNum("156516"));
        System.out.println(isNum("1 6546 651"));
        System.out.println(isNum(" 55555"));
        System.out.println(isOP("*"));
        System.out.println(isOP("/"));
        System.out.println(isOP("/ "));

    }

}