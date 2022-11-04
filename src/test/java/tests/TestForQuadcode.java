package tests;

import org.testng.annotations.Test;


public class TestForQuadcode {

    String t = "aabcccccbaa";
    String r = "a2b1c5b1a2";

    @Test
    public void test() {
        testString(t);
    }

    public String testString(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] ch = text.toCharArray();
        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                count++;
            } else {
                if (ch[i] == ch[i - 1]) {
                    count++;
                } else {
                    stringBuilder.append(count);
                    count = 0;
                    count++;
                }
                if (!stringBuilder.toString().endsWith(String.valueOf(ch[i]))) {
                    stringBuilder.append(ch[i]);
                }
            }
            if (i == ch.length - 1) {
                stringBuilder.append(count);
            }
        }
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }
}
