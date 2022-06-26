import java.util.*;

public class TextReverse {

	public String reversString(String text) {
		String revers = new StringBuffer(text).reverse().toString();
		System.out.println(revers);
		return revers;
	}

	public String reversStringTest(String str) {
		String words = str.replaceAll("[^(А-Яа-яA-Za-z0-9)\\s+[?!:;,.]]", "");
		String[] text = words.split("\\s");
		String result = "";
		for (int i = text.length-1; i >=0; i--) {
			result = result + text[i] + " ";

		}
		System.out.println(result);
		return result;
	}

	public String reversText(String str) {
		String[] textArr = str.split("\\s");
		String reversText = "";
		for (int i = textArr.length - 1; i >= 0; i--) {
			reversText = reversText + textArr[i] + " ";
		}
		System.out.println(reversText);
		return reversText;
	}
	public String reversTextTest(String str) {
String[]strings = str.split("");
Collection<String> stringCollection = null;
stringCollection.add(String.valueOf(strings));



		return str;
	}
	public static void main(String[] args) {
		TextReverse textReverse = new TextReverse();
		textReverse.reversString("Do or do not, there is no try");
		textReverse.reversStringTest("В бытность мою в С—м уезде мне часто приходилось бывать "  +
				"\t\t\t\t\"на Дубовских огородах у огородника Саввы Стукача, или попросту Савки");
		System.out.println("------------");
		textReverse.reversText("В бытность мою в С—м уезде мне часто приходилось бывать " +
				"на Дубовских огородах у огородника Саввы Стукача, или попросту Савки");



	}


}
