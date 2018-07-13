import java.util.Scanner;

public class DZWork {

    public static void main(String[] args) {
        String m = "";
        String[] message;
        Scanner scanner = new Scanner(System.in);


        SQLiteDZ.getConnection();
        SQLiteDZ.createTable();

        for (int i = 1; i <= 10000; i++) {
            SQLiteDZ.add("id_goods "+String.valueOf(i),"goods"+String.valueOf(i),String.valueOf(i*10));
        }

     //   SQLiteDZ.search("goods10");
     //   SQLiteDZ.updateGoods("goods10","1000");
    //    SQLiteDZ.search("goods10") ;
    //    SQLiteDZ.search("10","150") ;


        while (!m.toUpperCase().equals("EXIT"))
        {
            System.out.println("Введие комманду:");
            m = scanner.nextLine();
            message = m.split(" ");

            if (message[0].charAt(0)!='/' && !message[0].toLowerCase().equals("exit") ) { System.out.println("Wrong command"); }
            else if (message[0].equals("/цена")) {

                //System.out.println(message[1]);
                SQLiteDZ.search(message[1].trim());

            }
            else if (message[0].equals("/сменитьцену")) {

                SQLiteDZ.updateGoods(message[1].trim(),message[2].trim());

            }
            else if (message[0].equals("/товарыпоцене")) {

                SQLiteDZ.search(message[1].trim(),message[2].trim());

            }

        } ;

        SQLiteDZ.closeConnection();
    }

}
