public class ArrayModifer {


    public int[] in(int[] a) {
        int[] b;
        int four = 0;
        int k = 0;

        for (int i = 0; i < a.length; ++i) {
            if (a[i] == 4) {
                four = i;
            }
            ;
        }

        if (four == 0) {
            throw new RuntimeException();
        } else {
            k = a.length - four - 1;
        }
        ;

        b = new int[k];

        for (int i = 0; i < b.length; i++) {
            b[i] = a[1 + i + four];
        }
        return b;
    }

    public boolean in41(int[] a) {

        int f = 0;
        int o = 0;

        for (int i = 0; i < a.length; i++) {
            if ((a[i] == 1)) {
                ++o;
            } else if (a[i] == 4) {
                ++f;

            } else {
                return false;
            }
        }

        if (f == 0 || o == 0) {
            return false;
        }

        return true;

    }
}