import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class SpanProblem {
    public static void main(String [] args) throws IOException {
        try {
            // Here we have to replace current path to desired path
            RandomAccessFile file = new RandomAccessFile("/Users/distillery/untitled/src/text", "r");
            String str;
            // create the list of competitor and results list
            List<String> competitions = new ArrayList<String>();
            List<Integer> results = new ArrayList<Integer>();
            //loop to read the input of file
            while ((str = file.readLine()) != null) {
                //split in array using comma as delimiter
                String [] out = str.split(", ");
                for(String s : out)
                {
                    //split to remove decimal form string
                    String[] r = s.split(" \\d");
                    // saving the value of the score in int
                    int res = Character.digit(s.charAt(s.length()-1),10);
                    results.add(res);
                    // checking null values to avoid exception
                    if(r[0] != null)
                    {
                        competitions.add(r[0]);
                    }
                }
            }
            //calling the main method to evaluate who won the points of the match
            HashMap<String, Integer> map = competitorEvaluator(competitions,results);
               //printing the results of evaluation of teams
               map.forEach((k,v)->
               { if(v>1 || v==0)
               {
                   System.out.println(k + ", " + v + " pts");
               }
               else
                   System.out.println(k + ", " + v + " pt");
               });
               //closing the file
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static  HashMap<String, Integer> competitorEvaluator(List<String> competitions, List<Integer> results)
    {
        HashMap<String, Integer> winner = new HashMap<>();
        //loop to verify the results of each match
        for(int i = 1; i < results.size(); i = i+2)
        {
            //index 1 is for local team, index 2 is for visitor
            int index1 = 0, index2 =0;
            //here validates the points depends on results
            if(results.get(i-1) == results.get(i))
            {
                index1 = 1;
                index2 =index1;
            }
            if(results.get(i-1) > results.get(i))
            {
                index1 = 3;
            }
            if(results.get(i-1) < results.get(i))
            {
                index2 = 3;
            }
            //Having two keys to retrieve each team information
            String key1 = competitions.get(i-1);
            String key2 = competitions.get(i);
            // in case we already add before the name of team we will update only score
            if(winner.containsKey(key1))
            {
                winner.put(key1,  winner.get(key1) +index1);
            }
            // if doents exists the Key(Team name) we will create register in the map
            if (!winner.containsKey(key1)){
                winner.put(key1, index1);
            }

            if(winner.containsKey(key2))
            {
                winner.put(key2,  winner.get(key2) +index2);
            }if(!winner.containsKey(key2))
        {
            winner.put(key2, index2);
        }
        }
        // we will make a sort in order to validate the points,
        // only if we have the same points we will evaluate team name order by alphabet.
        return (winner.entrySet()
                .stream()
                .sorted((i1, i2)
                        ->
                {
                    if(i1.getValue() > i2.getValue())
                        return -1;
                    if(i1.getValue() < i2.getValue())
                        return 1;
                    if(i1.getValue()== i2.getValue()){
                        return i1.getKey().compareTo(i2.getKey());
                    }
                    return 0;

                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)));
    }
}
