import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class SpanProblem {
    public static void main(String [] args) throws IOException {
        try {
            RandomAccessFile file = new RandomAccessFile("/Users/distillery/untitled/src/text", "r");
            String str;
            List<String> competitions = new ArrayList<>();
            ArrayList<Integer> results = new ArrayList<Integer>();
            while ((str = file.readLine()) != null) {

                String [] out = str.split(", ");
                for(String s : out)
                {
                    String[] r = s.split(" \\d");
                    int res = Character.digit(s.charAt(s.length()-1),10);
                    results.add(res);

                    if(r[0] != null)
                    {
                        competitions.add(r[0]);
                    }
                }
            }
            HashMap<String, Integer> map = competitorEvaluator(competitions,results);

               map.forEach((k,v)->
               { if(v>1 || v==0)
               {
                   System.out.println(k + ", " + v + " pts");
               }
               else
                   System.out.println(k + ", " + v + " pt");
               });
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static  HashMap<String, Integer> competitorEvaluator(List<String> competitions, ArrayList<Integer> results)
    {
        HashMap<String, Integer> winner = new HashMap<>();

        for(int i = 1; i < results.size(); i = i+2)
        {
            int index1 = 0, index2 =0;
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

            String key1 = competitions.get(i-1);
            String key2 = competitions.get(i);

            if(winner.containsKey(key1))
            {
                winner.put(key1,  winner.get(key1) +index1);
            }
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
        return (winner.entrySet()
                .stream()
                .sorted((i1, i2)
                        ->
                { if(i1.getValue() > i2.getValue())
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
