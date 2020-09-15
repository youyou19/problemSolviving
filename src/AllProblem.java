import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AllProblem {
    public static void main(String[] args) {

    }
    public static void numberVowel(String str) {
        int v=0,c=0;
        HashSet<Character> hash=new HashSet<Character>();
        hash.add('e');hash.add('a');hash.add('o');
        hash.add('i');hash.add('u');
        str=str.toLowerCase();
        for(Character ch:str.toCharArray()) {
            if(Character.isLetter(ch)) {
                if(hash.contains(ch))v++;
                else c++;
            }
        }
        System.out.println("Consonne: "+c+"\nVowel: "+v);
    }
    public static void subSet(String str) {
        for(int i=0;i<str.length();i++) {
            for(int j=i+1;j<=str.length();j++) {
                System.out.println(str.substring(i,j));
            }
        }
    }
    public static boolean allUnique(String str) {
        str=str.toLowerCase();
        HashSet<Character> hash=new HashSet<Character>();
        for(int i=0;i<str.length();i++) {
            if(!hash.add(str.charAt(i))) return false;
            //if(str.indexOf(str.charAt(i))!=str.lastIndexOf(str.charAt(i))) return false;
        }
        return true;
    }
    public static boolean anagram(String str1, String str2) {
        str1=str1.toLowerCase();
        str2=str2.toLowerCase();
        // str1=Arrays.asList(str1.split("")).stream().sorted(String.CASE_INSENSITIVE_ORDER)
        //		.collect(Collectors.joining());
        //str2=Arrays.asList(str2.split("")).stream().sorted(String.CASE_INSENSITIVE_ORDER)
        //		.collect(Collectors.joining());
        //if(str1.equalsIgnoreCase(str2)) return true;
        for(int i=0;i<str1.length();i++) {
            int index=str2.indexOf(str1.charAt(i));
            if(index!=-1)str2=str2.substring(0,index)+str2.substring(index+1,str2.length());
        }
        return str2.isEmpty();
    }
    //ABC   FRC      -ABFR /4
    static int makeAnagram(String a, String b) {
        StringBuilder sb1=new StringBuilder(a);
        StringBuilder sb2=new StringBuilder(b);
        for(int i=0;i<a.length();i++){
            int iSb1=sb1.indexOf(""+a.charAt(i));
            int iSb2=sb2.indexOf(""+a.charAt(i));
            if(iSb2!=-1 && iSb1!=-1){
                sb2.deleteCharAt(iSb2);
                sb1.deleteCharAt(iSb1);
            }
        }
        return sb1.length()+sb2.length();
    }
    // Complete the alternatingCharacters function below.
    // AAABBB                            -A=2, B=2  4deletions
    static int alternatingCharacters(String s) {
        int deletions=0;
        int i=0;
        while(i<s.length()){
            int count=0;
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j))count++;
                else break;
            }
            deletions+=count;

            i+=count+1;
        }
        return deletions;
    }


    public static String replaceWord(String str, String initialWord, String newWord) {
        StringBuilder sb=new StringBuilder();
        for(String s:str.split(" ")) {
            if(s.toLowerCase().contains(initialWord.toLowerCase()))sb.append(newWord+" ");
            else sb.append(s+" ");
        }
        return sb.toString();
        //	return Arrays.asList(str.split(" ")).stream().map(s->{
        //	if(s.toLowerCase().contains(initialWord.toLowerCase())) s=s.replace(s,newWord);
        //	return s;
        //	}).collect(Collectors.joining(" "));
    }
    public static String lastDigit(String str) {
        char ch=str.trim().charAt(str.length()-1);
        String temp="";
        int count=0;
        if(Character.isDigit(ch)) {
            count=Integer.parseInt(""+ch)+1;
            temp=str.substring(0,str.length()-1)+count;
        }else temp=str+count;

        return temp;
    }
    public static boolean validParenthesis(String str) {
        Stack<Character> stack=new Stack<Character>();
        for(Character c:str.toCharArray()) {
            if(c=='{' || c=='(' || c=='[') stack.push(c);
            else if(c=='}' || c==')' || c==']') {
                if(stack.isEmpty()) return false;
                char ch=stack.pop();
                if((c=='}' && ch!='{')||(c==']' && ch!='[')|| (c==')' && ch!='(')) return false;
            }
        }
        return stack.isEmpty();
    }
    public static void printFirstDuplicate(String str) {
        //	Map<String, Long> map=Arrays.asList(str.split("")).stream().map(s->s.toLowerCase()).
        //	collect(Collectors.groupingBy(String::new,LinkedHashMap::new,Collectors.counting()));
        //	System.out.println(map.entrySet().stream().filter(x->x.getValue()==1).findFirst().get());
        //		System.out.println(map.entrySet().stream().filter(x->x.getValue()>1).findFirst().get());
        LinkedHashMap<Character, Long> map=new LinkedHashMap<Character, Long>();
        for(Character ch:str.toLowerCase().toCharArray())
            map.put(ch, map.containsKey(ch) ? map.get(ch)+1:1);
        char first=' ',last=' ';
        for(Map.Entry<Character, Long> en:map.entrySet()) {
            if(en.getValue()==1) {
                first=en.getKey();
                break;
            }
        }
        for(Map.Entry<Character, Long> en:map.entrySet()) {
            if(en.getValue()>1) {
                last=en.getKey();
                break;
            }
        }
        System.out.println("First: "+first+"\t\tLast: "+last);
    }
    public static void printDuplicat(String str) {
        //	str=str.toLowerCase();
        //	HashMap<Character, Integer> hash=new HashMap<Character, Integer>();
        //	for(int i=0;i<str.length();i++)
        //	hash.put(str.charAt(i),hash.containsKey(str.charAt(i))?hash.get(str.charAt(i))+1:1);

        //	for(Map.Entry<Character, Integer> en:hash.entrySet()) {
        //	if(en.getValue()>1)System.out.println(en.getKey()+" : "+en.getValue());
        //	}
        Arrays.asList(str.toLowerCase().split("")).stream()
                .collect(Collectors.groupingBy(String::new,TreeMap::new,Collectors.counting()))
                .entrySet().stream().filter(s->s.getValue().intValue()>1)
                .forEach(x->System.out.printf("%s : %d\n",x.getKey(),x.getValue()));
    }
    public static String sortOccurence(String str[]) {
        return Arrays.asList(str).stream().map(s->s.toLowerCase())
                .sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.joining(" "));
    }
    //	public static String sortOccurence(String str) {
    //	return Arrays.asList(str.split("")).stream().map(s->s.toLowerCase())
    //			.sorted(Comparator.comparing(x->{
    //
    //			})).collect(Collectors.joining(" "));
    //	}

    public static long occurence(String str, String sub) {
		/*	int count=0,i=0;
			while(i<str.length()) {
				int taille=i+sub.length();
				if(taille>str.length())break;
				if(str.substring(i,taille).equalsIgnoreCase(sub)) {
					count++;
					i+=sub.length() -1;
				}

				i++;
			}
			return count;
		 */
        long id=Arrays.asList(str.split(" ")).stream().filter(s->s.equalsIgnoreCase(sub))
                .collect(Collectors.counting());

        return id;
    }
    //All permutation
    public static void allPermutation(String perm, String str) {
        if(str==null || str.isEmpty()) System.out.println(perm);
        for(int i=0;i<str.length();i++)
            allPermutation(perm+str.charAt(i),str.substring(0,i)+str.substring(i+1,str.length()));
    }

    public static Set permutationOfString(String str){
        Set permutationSet=new HashSet();
        if(str.length()==0){
            permutationSet.add("");
            return permutationSet;
        }
        char c=str.charAt(0);
        String rem=str.substring(1);
        Set<String> permutatedSetForRemainingString=permutationOfString(rem);
        for (String permutedString: permutatedSetForRemainingString) {
            for (int j = 0; j <= permutedString.length(); j++) {
                String permutation=insertFirstCharAtDiffPlaces(permutedString,c,j);
                permutationSet.add(permutation);
            }

        }
        return permutationSet;
    }
    public static String insertFirstCharAtDiffPlaces(String perm,char firstChar,int index){
        return perm.substring(0,index)+firstChar+perm.substring(index);
    }
    //Remove
    public static String removeWord(String str, String word) {
        String ch[]=str.toLowerCase().split(" ");
        StringBuilder sb=new StringBuilder();
        for(String s:ch) {
            if(!s.equalsIgnoreCase(word))sb.append(s+" ");
        }
        //return Arrays.asList(str.split(" ")).stream().filter(s->!s.equalsIgnoreCase(word)).collect(Collectors.joining(" "));
        return sb.toString();
    }

    public static String removeDuplicateSentence(String str) {
        String ch[]=str.toLowerCase().split(" ");
        StringBuilder sb=new StringBuilder();
        for(String s:ch) {
            if(sb.indexOf(s)==-1)sb.append(s+" ");
        }
        //return Arrays.asList(str.split(" ")).stream().map(s->s.toLowerCase()).distinct().collect(Collectors.joining(" "));
        return sb.toString();
    }

    public static String removeDuplic(String str) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.toLowerCase().length();i++) {
            if(sb.indexOf(""+str.charAt(i))==-1)sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    public static String removeDuplicate(String str) {
        LinkedHashSet<Character> hash=new LinkedHashSet<Character>();
        for(Character ch:str.toCharArray())
            hash.add(ch);

        StringBuilder sb=new StringBuilder();
        for(Character c:hash)
            sb.append(c);
        return sb.toString();
        //	str.chars().distinct().forEach(x->System.out.println((char)x));

        // or return Arrays.asList(str.split("")).stream().distinct().collect(Collectors.joining());
    }
    //ad#v    av
    public static String removeBack(String str) {
        StringBuilder s=new StringBuilder();

        for(Character ch:str.toCharArray()) {
            if(ch!='#')s.append(ch);
            else if(s!=null)
                s.deleteCharAt(s.length()-1);
        }
        return s.toString();
        //Stack<Character> stack=new Stack<Character>();
//		for(Character ch:str.toCharArray()) {
//			if(ch!='#')stack.push(ch);
//			else if(!stack.isEmpty())stack.pop();
//		}
//		StringBuilder sb=new StringBuilder();
//		while(!stack.isEmpty())sb.append(stack.pop());
//		return sb.reverse().toString();
    }
    //AAAABBBBCCC        -ABC
    static String removeDoublonDistinct(String s) {
        int i=0;
        String sb="";
        while(i<s.length()){
            int count=0;
            for(int j=i+1;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j))count++;
                else break;
            }
            sb+=s.charAt(i);
            i+=count+1;
        }
        return sb;
    }


    //reverse
    public static String reverseSpecial(String str) {
        char ch[]=str.toCharArray();
        int l=0,r=ch.length-1;
        while(l<r) {
            if(!Character.isAlphabetic(ch[l]))l++;
            else if(!Character.isAlphabetic(ch[r]))r--;
            else if(Character.isAlphabetic(ch[r]) && Character.isAlphabetic(ch[l])) {
                char temp=ch[l];
                ch[l]=ch[r];
                ch[r]=temp;
                l++;
                r--;
            }
        }
        StringBuilder sb=new StringBuilder();
        for(Character c:ch)sb.append(c);
        return sb.toString();
    }

    public static String reverseWord(String str) {
        String []ch=str.split(" ");
        int l=0,r=ch.length-1;
        while(l<r) {
            String temp=ch[l];
            ch[l]=ch[r];
            ch[r]=temp;
            l++;
            r--;
        }
        StringBuilder result=new StringBuilder();;
        for(String s:ch)result.append(" "+s);
        return result.toString();
    }


    public static boolean palindromeNumber(Integer s){
        if(s.intValue()<10) return true;
        int temp=s.intValue(),sum=0;
        while(temp>0) {
            int rest=temp%10;
            sum=sum*10+rest;
            temp=temp/10;
        }
        if(sum==s.intValue())return true;
        return false;
    }

    public static boolean palindrome(String str) {
        int l=0,r=str.length()-1;
        str=str.toLowerCase();
        while(l<r) {
            char chL=str.charAt(l);
            char chR=str.charAt(r);
            if(!Character.isAlphabetic(chL))l++;
            if(!Character.isAlphabetic(chR)) r--;
            else if(chL!=chR) return false;
            l++;
            r--;
        }

        return true;
    }
    public static boolean checkLetters(String str) {
        str=str.replaceAll("\\s+", "").toLowerCase();
        HashSet<Character> set=new HashSet<Character>();
        for(Character ch:str.toCharArray()) {
            if(Character.isAlphabetic(ch))set.add(ch);
        }
        if(set.size()==26) return true;
        return false;
    }

    public static int indexOf(String str, String subStr) {
        char ch[]=str.toCharArray();
        char sub[]=subStr.toCharArray();
        int index=0;

        for(int i=0;i<ch.length;i++) {
            if(index<sub.length && ch[i]==sub[index]) {
                index++;
                if(index==sub.length) return i-sub.length+1;
            }
            else index=0;

        }

        return -1;
    }
    //String str="aaabbcccaa"
    //a-> 3times b->2times  c-> 3times  a->2times
    public static void countSequence(String str) {
        int i=0;
        while(i<str.length()) {
            int count=0;
            char ch=str.charAt(i);
            for(int j=i;j<str.length();j++) {
                if(ch==str.charAt(j))
                    count++;
                else {
                    break;
                }

            }
            System.out.println(ch+" : "+count);
            i+=count;
        }

    }


}