package PaperPass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PaperPassCountTest {

    String[] a = {"你好吗", "我很好"};
    String[] b = {"你过的好吗", "我过的很好"};

    @Test
    public void main() {
        PaperPass(a,b,"E:\\桌面\\ans.txt");
    }

    private static void PaperPass(String[] originalArray, String[] addArray, String ansPath) {
        double similarityPercentage = 0;
        double sentencePercentage;
        double wordNum = 0;
        for (String doc1 : originalArray
        ) {
            sentencePercentage = 0;
            if (doc1 == null) break;
            wordNum += doc1.length();
            for (String doc2 : addArray
            ) {
                if (doc2 == null) break;
                Map<Character, int[]> algMap = new HashMap<>();
                for (int i = 0; i < doc1.length(); i++) {
                    char d1 = doc1.charAt(i);
                    int[] fq = algMap.get(d1);
                    if (fq != null && fq.length == 2) {
                        fq[0]++;
                    } else {
                        fq = new int[2];
                        fq[0] = 1;
                        fq[1] = 0;
                        algMap.put(d1, fq);
                    }
                }
                for (int i = 0; i < doc2.length(); i++) {
                    char d2 = doc2.charAt(i);
                    int[] fq = algMap.get(d2);
                    if (fq != null && fq.length == 2) {
                        fq[1]++;
                    } else {
                        fq = new int[2];
                        fq[0] = 0;
                        fq[1] = 1;
                        algMap.put(d2, fq);
                    }
                }
                double sqdoc1 = 0;
                double sqdoc2 = 0;
                double denuminator = 0;
                for (Map.Entry entry : algMap.entrySet()) {
                    int[] c = (int[]) entry.getValue();
                    denuminator += c[0] * c[1];
                    sqdoc1 += c[0] * c[0];
                    sqdoc2 += c[1] * c[1];
                }
                double similarPercentage = denuminator / Math.sqrt(sqdoc1 * sqdoc2);
                if (similarPercentage > sentencePercentage)
                    sentencePercentage = similarPercentage;
            }
            similarityPercentage += (sentencePercentage * doc1.length());
        }
        similarityPercentage = similarityPercentage / wordNum * 100;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        df.format(similarityPercentage);
        System.out.println("论文重复率为" + similarityPercentage + "%");
        File file = new File(ansPath);
        try {
            Writer writer = new FileWriter(file,false);
            writer.write("论文重复率为" + similarityPercentage + "%");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}