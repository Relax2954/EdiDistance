import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;// SET UP
  int w1len, w2len, myi, counter, dist, w2oldlen;
  int [][] myarray;//=new int[14][38];
  String w2old="a";
  int partDist(String w1, String w2, int w1len, int w2len, int samCharC) {
    //new




    for(int j=samCharC+1; j<=w2len; j++){
        for(int i=1; i<=w1len;i++){
            if(w1.charAt(i-1)==w2.charAt(j-1))
                myarray[i][j]=myarray[i-1][j-1];
            else{
              //  myarray[i][j]=Math.min(Math.min(myarray[i-1][j]+1, myarray[i][j-1]+1),myarray[i-1][j-1]+1);
              int replace = myarray[i-1][j-1] + 1;
              int insert = myarray[i-1][j] + 1;
              int delete = myarray[i][j-1] + 1;

              int min = replace > insert ? insert : replace;
              min = delete > min ? min : delete;
              myarray[i][j] = min;
            }

        }
    }
    return myarray[w1len][w2len];


}


// int partDist2(String w1, String w2, int w1len, int w2len,int[][] myarray, int counter) {
//   for(int j=counter+1; j<=w2len; j++){
//     //System.out.println("COunter je: "+counter);
//     int i;
//       for(i=1; i<=w1len;i++){
//           if(w1.charAt(i-1)==w2.charAt(j-1))
//               myarray[i][j]=myarray[i-1][j-1];
//           else{
//               myarray[i][j]=Math.min(Math.min(myarray[i-1][j]+1, myarray[i][j-1]+1),myarray[i-1][j-1]+1);
//           }
//
//       }
//   }
//   return myarray[w1len][w2len];
//
//
// }

int Distance(String w1, String w2, int samCharC) {
   w1len=w1.length();
   w2len=w2.length();
   counter=0;
   // if(w2old !=null && w2old.length()==w2len){
   //   //System.out.println("w2old je: "+w2old+", a w2new je: "+w2);
   //   counter=0;
   //   for(myi=0; myi<w2len; myi++){
   //     if(w2.charAt(myi) == w2old.charAt(myi))
   //      counter++;
   //     else
   //      break;
   //   }
   //   w2old=w2;
   //  //System.out.println("I am smart :). I= "+counter);
   //   return partDist2(w1, w2, w1len, w2len, myarray, counter);
   // }

   // 2nd attempt
   w2oldlen=w2old.length();
   // if(w2oldlen==w2len){
   //   if(w2.charAt(w2len-2) == w2old.charAt(w2oldlen-2)){
   //     w2old=w2;
   //     return partDist2(w1, w2, w1len, w2len, myarray, w2len-2);
   //   }
   //   if(w2.charAt(w2len-3) == w2old.charAt(w2oldlen-3)){
   //     w2old=w2;
   //     return partDist2(w1, w2, w1len, w2len, myarray, w2len-3);
   //   }
   // }
   w2old=w2;
   return partDist(w1, w2, w1len, w2len, samCharC);
}

  public ClosestWords(String w, List<String> wordList) {
    myarray=new int[w.length()+1][38];
    //myarray =Main.myarray;
    for(int i=0; i<=w.length(); i++)
      myarray[i][0]=i;
    for(int j=0; j<38;j++)
      myarray[0][j]=j;
    w2oldlen=0;
    for (String s : wordList) {
      //System.out.println(dist);
      w2len=s.length();
      if(closestDistance>=Math.abs(s.length()-w.length()) || closestDistance==-1){
        int samCharC=charCount(s, w2old, w2len, w2oldlen);

        dist = Distance(w, s, samCharC);
        //System.out.println("hejo" +dist);
        // System.out.println("d(" + w + "," + s + ")=" + dist);
        if(dist == closestDistance) //ORDER CHANGED
          closestWords.add(s);
          else if (dist < closestDistance || closestDistance ==-1) {
            closestDistance = dist;
            closestWords = new LinkedList<String>();
            closestWords.add(s);
          }
          // else if (dist == closestDistance)
          //   closestWords.add(s);

          w2old=s;
          w2oldlen=w2len;
        }

    }
  }

  int charCount(String w2, String w2old, int w2len, int w2oldlen){
    int max=0;
    if(w2len>w2oldlen)
      max=w2oldlen;
    else
      max=w2len;

    for (int i=0;i<max;i++){
  		 if (w2.charAt(i) != w2old.charAt(i))
        return i;
  	}
    return max;
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
