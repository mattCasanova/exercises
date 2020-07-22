/*areAnagrams(string, string) -> boolean 

 areAnagrams("thing", "night") -> true 
 areAnagrams("listen", "silent") -> true 
 areAnagrams("think", "night") -> false


 getAnagramGroups([string]) -> [[string]] 
 getAnagramGroups(["thing", "listen", "think", "silent", "night"]) -> [["thing", "night"], ["listen", "silent"], ["think"]]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(areAnagrams("thing", "night"));
        System.out.println(areAnagrams("listen", "silent"));
        System.out.println(areAnagrams("think", "night") );
        System.out.println(areAnagrams("", ""));
        System.out.println(areAnagrams("", "think"));
        System.out.println(areAnagrams("night", ""));
        System.out.println(areAnagrams("night", "nnight"));
        System.out.println(areAnagrams("nnight", "niight"));

        String[] strings = new String[]{ "thing", "listen", "think", "silent", "night", "nghti" };

        System.out.println(getAnagramGroups(strings));
    }

    public static ArrayList<ArrayList<String >> getAnagramGroups(String[] strings) {
        ArrayList<ArrayList<String>> groups = new ArrayList<>();

        HashMap<Integer, ArrayList<String>> sizeMap = new HashMap<>();

        for (String string : strings) {
            int length = string.length();

            ArrayList<String> current = sizeMap.containsKey(length) ? sizeMap.get(length) : new ArrayList<>();

            current.add(string);
            sizeMap.put(length, current);
        }


        for (Map.Entry<Integer, ArrayList<String>> set: sizeMap.entrySet()) {
            HashSet<String> anagramGroup = new HashSet<>();
            ArrayList<String> list = set.getValue();
            int length = list.size();

            for (int i = 0; i < length; ++i) {
                String first = list.get(i);
                boolean hasAnagrams = false;

                for (int j = i + 1; j < length; ++j) {
                    String second = list.get(j);

                    if (areAnagrams(first, second)) {
                        hasAnagrams = true;
                        anagramGroup.add(first);
                        anagramGroup.add(second);
                    }
                }

                if  (!hasAnagrams && !anagramGroup.contains(first)) {
                    ArrayList<String> noGroup = new ArrayList<>();
                    noGroup.add(first);
                    groups.add(noGroup);
                }
            }

            groups.add(new ArrayList<>(anagramGroup));
        }

        return groups;
    }

    public static Boolean areAnagrams(String first, String second) {
        HashMap<Character, Integer> charCount = new HashMap<>();

        int firstLength = first.length();
        int secondLength = second.length();

        if (firstLength != secondLength) {
            return false;
        }

        for (int i = 0; i < firstLength; ++i) {
            Character c = first.charAt(i);
            Integer count = charCount.get(c);

            count = count == null ? 1 : ++count;
            charCount.put(c, count);
        }


        for (int i = 0; i < secondLength; ++i) {
            Character c = second.charAt(i);
            Integer count = charCount.get(c);

            if (count == null || --count < 0)
                return false;

            charCount.put(c, count);
        }

        return true;
    }

}
