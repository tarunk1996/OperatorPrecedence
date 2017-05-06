import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by TA on 06-05-2017.
 */
public class OperatorPrecedence {
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();
    private static HashMap< Integer,String> rmap = new HashMap<Integer, String>();
    private static String[] tab={">><<<<<>>",">><<<<<>>",">>>><<<>>",">>>><<<>>",">>>><<<>>",">>>>>ee>>","<<<<<<<=e",">>>>>ee>>","<<<<<<<< "};
    private static String[] t={"E+E","E-E","E*E","E/E","(E)","i","E^E"};

    public static void main(String[] args) {

        initializeOpTable();
    }


    private static void initializeOpTable()
    {
        map.put("+",0);
        map.put("-",1);
        map.put("*",2);
        map.put("/",3);
        map.put("^",4);
        map.put("i",5);
        map.put("(",6);
        map.put(")",7);
        map.put("$",8);

        rmap.put(0,"+");
        rmap.put(1,"-");
        rmap.put(2,"*");
        rmap.put(3,"/");
        rmap.put(4,"^");
        rmap.put(5,"i");
        rmap.put(6,"(");
        rmap.put(7,")");
        rmap.put(8,"$");

        System.out.println("Parse Table of Precedence Parser");
        System.out.println("   | \t+ \t- \t* \t/ \t^ \ti \t( \t) \t$");
        System.out.println("--------------------------------------------");
        for (int i = 0; i <9 ; i++)
        {
            System.out.print(rmap.get(i)+"  |"+"\t");
            for (int j = 0; j <9 ; j++) {
                System.out.print(tab[i].substring(j,j+1)+'\t');
            }
            System.out.println("");
        }
        System.out.println("Enter String :");
        Scanner reader =new Scanner(System.in);
        String ip_string=reader.next();
        String tmp;
        String ch;
        ch="$";
        for (int i = 0; i <ip_string.length() ; i++)
        {
            tmp = ip_string.substring(i,i+1);
            ch+=tab[map.get(ch.substring(ch.length()-1))].substring(map.get(tmp),map.get(tmp)+1);
            ch+=tmp;
        }
        ch+=">$";
        System.out.println(ch);
        System.out.println("Handle \t String");
        parsingString(ch);
    }
    private static void parsingString(String ch) {
        if(ch.contains(">")==true)
        {
            int flag = 0;
            //System.out.println("H");
            int i=0,i1;
            int j=0,j1;
            while(!ch.substring(i,i+1).equals(">"))
            {
                if(ch.substring(i,i+1).equals("<"))
                    j=i;
                i++;
            }
            j1=j;
            i1=i;
            if((ch.charAt(j-1)+"").equals("E"))
            {
                j1=j-1;
            }
            if((ch.charAt(i+1)+"").equals("E"))
            {
                i1=i+1;
            }
            String tt = tab[map.get(ch.charAt(j1-1)+"")].substring(map.get(ch.charAt(i1+1)+""),map.get(ch.charAt(i1+1)+"")+1);
            String handle = ch.substring(j1+1,i);
            if(i1!=i)
            {
                handle = ch.substring(j1+1,i1+1);
                handle= handle.replaceAll(">","");
            }
            for (int k = 0; k < 7 ; k++) {
                if(handle.equals(t[k]))
                {
                    flag = 1;
                    break;
                }
            }
            if(flag==1)
            {
                if(tt.equals("="))
                    tt="";
                ch=ch.substring(0,j)+tt+"E"+ch.substring(i1+1);
                ch=ch.replaceAll("\\s","");
                System.out.println(handle +"\t\t"+ch);
                parsingString(ch);
            }
            else
            {
                System.out.println(handle +"\t\t"+ch);
                System.out.println("Handle Not Matched");
            }
        }
        else
        {
            if(ch.equals("$E$"))
            {
                System.out.println("String Accepted");
            }
            else
                System.out.println("String Not Accepted");
        }
    }

}
