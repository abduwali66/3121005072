package PaperPass;

import java.io.*;
import java.util.Scanner;

public class Testing {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int maxNumber = 0;
        int minNumber = 999999999;
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(new File(in.nextLine())));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                switch (JudgeType(tempchar)) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                        if (tempchar > maxNumber) maxNumber = tempchar;
                        if (tempchar < minNumber) minNumber = tempchar;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(maxNumber);
        System.out.println(minNumber);
    }

    private static int JudgeType(int tempchar) {
        if ((char) tempchar == ' ' || (char) tempchar == '，' || (char) tempchar == '\r' || (char) tempchar == '\t' ||
                (char) tempchar == '、' || (char) tempchar == '《' || (char) tempchar == '.' || (char) tempchar == '-'
                || (char) tempchar == '”' || (char) tempchar == '“' || (char) tempchar == '》' || (char) tempchar == '：'
                || (char) tempchar == '—' || (char) tempchar == '；')
            return 0;   //忽略
        else if ((char) tempchar == '。' || (char) tempchar == '!' || (char) tempchar == '？' || (char) tempchar == '\n'
                || (char) tempchar == ';')
            return 1;   //判定为句子
        else return 2;
    }
} 
