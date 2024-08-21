package primeirob.Aulas;

import java.io.FileWriter
import java.io.IOException

public class JReader {

    public static void main(String[] args){

        try{
            Scanner input = new Scamner(System.in);
            System.out.println("Insira o caminho do arquivo");

            String path = input.next();

            FileWriter writer = new FileWriter(path)
        }
            writer.write("Pega!😎\n");
            writer.write("Bem vindo a leitura e escrita com Java");

}