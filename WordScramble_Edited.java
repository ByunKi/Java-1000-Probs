package Java;

import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};

        System.out.println("소문자로 \'quit\'를 누르시면 종료됩니다.");
        
        Loop:
        while (true) {
            String answer = getAnswer(strArr);
            String question = getScrambledWord(answer);
            String userInput = "";
            
            char[] hint = new char[answer.length()];
            for(int i = 0; i < hint.length; i++) {
                hint[i] = '_';
            }

            while (true) {
                System.out.println("Question: " + question);
                System.out.print("Your answer is: ");

                userInput = reader.readLine();
                if (userInput.toUpperCase().equals(answer)) {
                    System.out.println("정답입니다.");
                    break;
                } else if (userInput.equals("quit")) {
                    System.out.println("프로그램이 종료됩니다.");
                    break Loop;
                }

                System.out.println(userInput + "은/는 정답이 아닙니다. 다시 시도해보세요.");
                System.out.println("Hint: " + getHint(answer, hint));
            }
        }

        reader.close();
        writer.flush();
        writer.close();
    }

    public static String getAnswer(String[] strArr) { 
        return strArr[(int)(Math.random() * strArr.length)];
    }
    
    public static String getScrambledWord(String str) { 
        int range = str.length();
        char[] temp = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            int idx = (int)(Math.random() * range);
            
            builder.append(temp[idx]);
            temp[idx] = temp[--range];
        }

        return builder.toString();
    }

    public static String getHint(String answer, char[] hint) {
        int count = 0; // 힌트에 포함된 '_'의 개수

        // 1. 반복문을 이용해서 hint에 포함된 '_'의 개수를 센다.
        for (char c : hint) {
            if (c == '_') {
                count++;
            }
        }

        // 2. count의 값이 answer의 전체 크기의 절반보다 크다면 힌트를 제공함.
        if (count > (int)(answer.length() / 2)) {
            while (true) {
                int idx = (int)(Math.random() * answer.length());

                if(hint[idx] == '_') {
                    hint[idx] = answer.charAt(idx);
                    break;
                }
            }
        }

        return new String(hint);
    }
} 
