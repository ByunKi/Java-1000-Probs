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
        StringBuffer buffer = new StringBuffer(str);
        StringBuilder builder = new StringBuilder();
        int length = str.length();

        for (int i = 0; i < length; i++) {
            int idx = (int)(Math.random() * length);

            if(buffer.charAt(idx) == '0') {
                i--;
                continue;
            }

            builder.append(buffer.charAt(idx));
            buffer.replace(idx, idx+1, "0");
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

        // 2. count의 값이 2보다 클 때만 정답의 한 글자를 hint에 넣는다.
        if (count > 2) {
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
