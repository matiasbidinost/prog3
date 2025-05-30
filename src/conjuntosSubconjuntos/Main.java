package conjuntosSubconjuntos;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> conjunto = Arrays.asList(1, 3, 4, 2);

        Integer sumaBuscada = 6;
        //Posibles salidas 4,2 | 1,3,2

        Backtracking back = new Backtracking(conjunto);

        System.out.println(back.solucionar2(sumaBuscada));

    }
}