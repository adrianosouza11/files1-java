package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        Scanner sc =  new Scanner(System.in);
        System.out.print("Especifique o caminho do arquivo para leitura: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            List<String> list = new ArrayList<>();
            while (line != null){
                list.add(line);
                line = br.readLine();
            }

            String nameProduct[] = new String[list.size()];
            Double valueProduct[] = new Double[list.size()];
            Integer quantity[] = new Integer[list.size()];

            for(int i=0; i<list.size(); i++){
                nameProduct[i] = list.get(i).split(",")[0];
                valueProduct[i] = Double.parseDouble(list.get(i).split(",")[1]);
                quantity[i] = Integer.parseInt(list.get(i).split(",")[2]);
            }

            String product[] = new String[list.size()];

            for (int i =0; i < list.size(); i++){
                product[i] =  String.format("%s, %.2f", nameProduct[i],(valueProduct[i] * quantity[i] ));
            }

            String strPath = "c:\\temp";

            boolean success = new File(strPath + "\\out").mkdir();

            String pathOut = "c:\\temp\\out\\summary.csv";

            if(success){
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))){
                    for (String eachProduct : product){
                        bw.write(eachProduct);
                        bw.newLine();
                    }
                }catch (IOException e){
                    System.out.println("Error: " + e.getMessage());
                }
            }


        }catch (IOException e){
            System.out.print("Error: "+e.getMessage());
        }

        sc.close();
    }
}
