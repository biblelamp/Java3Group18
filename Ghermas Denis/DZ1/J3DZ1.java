/**
 * Java 3. Lesson 1. generic
 *
 * @author Ghermas Denis / Гермаш Денис
 * @version dated Jul 9, 2018
 * @link https://github.com/firstmessage/
 */



import java.util.ArrayList;
import java.util.Arrays;

public class J3DZ1 {
    public static void main(String[] args) {

        GenericArray Test = new GenericArray();
        ArrayList<Integer> Ai = new ArrayList<>();
        Integer[] Aia = {1,2,3,4};

        System.out.println(Arrays.toString(Aia));

        Test.ChangeArrayPlace(Aia,2,0);

        System.out.println(Arrays.toString(Aia));

        Ai = Test.ChangeArrayToList(Aia);

        System.out.println(Ai.toString());


    }
}

class GenericArray<T> {
    private T obj;

    public void ChangeArrayPlace(T[] Ar, int i, int j) {
        int l = Ar.length;
        if ( (i>=0) && (j>=0) && (i<=l) && (j<=l) && (i!=j)){
            obj=Ar[j];
            Ar[j]=Ar[i];
            Ar[i]=obj;
        }
        //return Ar;
    }

    public ArrayList<T> ChangeArrayToList(T[] Ar) {
        ArrayList<T> AL = new ArrayList<>();

        for (int i = 0; i < Ar.length; i++) {
            AL.add(Ar[i]);
        }
        return AL;
    }

}