
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class Main {
//public static int [][] myarray=new int[14][38];
  public static List<String> readWordList(BufferedReader input) throws IOException {
    LinkedList<String> list = new LinkedList<String>();
    while (true) {
      String s = input.readLine();
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }

  public static void main(String args[]) throws IOException {
    //long t1 = System.currentTimeMillis();
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
    BufferedWriter stdout=new BufferedWriter(new OutputStreamWriter(System.out));
    //add BufferedWriter
    List<String> wordList = readWordList(stdin);
    String word;
    StringBuilder sb= new StringBuilder();
    while ((word = stdin.readLine()) != null) {
        ClosestWords closestWords = new ClosestWords(word, wordList);
        sb.setLength(0);
        //sb.append(word + " (" + closestWords.getMinDistance() + ")");
        sb.append(word);
        sb.append(" (");
        sb.append(closestWords.getMinDistance());
        sb.append(")");
        //System.out.print(word + " (" + closestWords.getMinDistance() + ")");
        //int size= closestWords.getClosestWords().size();
        //for(int i=0; i<size; i++){
        //  System.out.println("a dje si");
          //String w= closestWords.getClosestWords().get(i);
          for (String w : closestWords.getClosestWords()){
          sb.append(" ");
          sb.append(w);
        }
          //System.out.print(" " + w);
          //System.out.println();

          //System.out.println(sb.toString());
          stdout.write(sb.toString());
          stdout.newLine();
          stdout.flush();
        //  System.out.println();

}
      //long tottime = (System.currentTimeMillis() - t1);
      //System.out.println("CPU time: " + tottime + " ms");

  }

}
