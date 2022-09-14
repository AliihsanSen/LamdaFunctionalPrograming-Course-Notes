package lambda_functional_programing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Fp01 {

    public static void main(String[] args) {

        List<Integer> l = new ArrayList<>();
        l.add(8);
        l.add(9);
        l.add(131);
        l.add(10);
        l.add(9);
        l.add(10);
        l.add(2);
        l.add(8);
        System.out.println(l);

        /*
        1- ) Ardışık list elementlerini aynı satırda aralarında boşluk bırakarak
        yazdıran bir method oluşturun.(Structured)
         */

        listElemanlariniYazdirStructured(l); // 8 9 131 10 9 10 2 8
        System.out.println(" ");
        listElemanlariniYazdirFunctional(l); // 8 9 131 10 9 10 2 8
        System.out.println(" ");
        ciftElemanlariniYazdirStructured(l); // 8 10 10 2 8
        System.out.println(" ");
        ciftElemenlariniYazdirFunctional(l); // 8 10 10 2 8
        System.out.println(" ");
        tekElemanlarinKareleriniYazdirFunctional(l); // 81 17161 81
        System.out.println(" ");
        tekrarsizTekElemanlarinKupleriniYazdirFunctional(l); // 729 2248091
        System.out.println(" ");
        tekrarsizCiftElemanlarinKareToplamlariYazdirFunctional(l); // 168
        System.out.println(" ");
        tekrarsizCiftElemanlarinKupununCarpimiFunctional(l); // 4096000
        System.out.println();
        getMAxEleman01(l); // 131
        System.out.println();
        getMAxEleman02(l); // 131
        System.out.println();
        getYedidenBuyukMin01(l); // 8
        System.out.println();
        getYedidenBuyukMin02(l); // 8
        System.out.println();
        getYedidenBuyukMin03(l); // 8
        System.out.println( );
        getTersSiralamaylaTekrarsizElemanlarinYarisi(l); // [65.5, 5.0, 4.5, 4.0]
    }

    private static void listElemanlariniYazdirStructured(List<Integer> list) {

        for (Integer i : list) {
            System.out.print(i + " "); // [8, 9, 131, 10, 9, 10, 2, 8]
        }
    }


    /*
    1- )Ardışık list elementlerini aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Functional)
     */
    public static void listElemanlariniYazdirFunctional(List<Integer> list) {

        list.stream().forEach(t -> System.out.print(t + " ")); // 8 9 131 10 9 10 2 8

        /*
        --> stream() method collection'dan elementleri akışa dahil etmek için ve
            methodlara ulaşmak için kullanilir.

         */
    }


    /*
    2- )Cift list elemenlarini aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Structured)
     */
    public static void ciftElemanlariniYazdirStructured(List<Integer> list) {
        for (Integer i : list) {
            if (i % 2 == 0) {
                System.out.print(i + " "); // 8 10 10 2 8
            }
        }
    }


    /*
    2- )Cift list elemenlarini aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Functional)
     */
    public static void ciftElemenlariniYazdirFunctional(List<Integer> list) {
        list.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.print(t + " "));
    }


    /*
    3- ) Ardışık tek list elementlerinin karelerini aynı
    satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */
    public static void tekElemanlarinKareleriniYazdirFunctional(List<Integer> list) {
        list.stream().filter(t -> t % 2 != 0).map(t -> t * t).forEach(t -> System.out.print(t + " "));
    }


    /*
    4- ) Ardışık tek list elementlerinin küplerini tekrarsız olarak aynı
    satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.
     */
    public static void tekrarsizTekElemanlarinKupleriniYazdirFunctional(List<Integer> list) {
        list.stream().distinct().filter(t -> t % 2 != 0).map(t -> t * t * t).forEach(t -> System.out.print(t + " "));
    }


    /*
    5- ) Tekrarsız çift elementlerin karelerinin toplamını hesaplayan bir method
    oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKareToplamlariYazdirFunctional(List<Integer> list) {
        Integer toplam = list.stream().distinct().filter(t -> t % 2 == 0).map(t -> t * t).reduce(0, (t, u) -> t + u);
        System.out.print(toplam + " ");
    }


    /*
    6- ) Tekrarsız çift elemanların küpünün çarpımını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKupununCarpimiFunctional(List<Integer> list) {
        Integer carpim = list.stream().distinct().filter(t -> t % 2 == 0).map(t -> t * t * t).reduce(1, (t, u) -> t * u);
        System.out.print(carpim + " ");
    }


    /*
    7- ) List elemanları arasından en büyük değeri bulan bir method oluşturun.
     */
    public static void getMAxEleman01(List<Integer> list) {
        Integer max = list.stream().distinct().reduce(Integer.MIN_VALUE, (t, u) -> t > u ? t : u);
        System.out.print(max + " ");
    }

    public static void getMAxEleman02(List<Integer> list) {
        Integer max = list.stream().distinct().sorted().reduce(Integer.MIN_VALUE, (t, u) -> u);
        System.out.print(max + " ");
    }


    /*
    8- )List elemanları arasından en küçük değeri bulan bir method oluşturun.(2 Yol ile)
     */


    /*
    9- ) List elemanları arasından 7'den büyük, çift, en küçük değeri bulan bir
    method oluşturun.
     */
    public static void getYedidenBuyukMin01(List<Integer> list) {
        Integer min = list.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7).reduce(Integer.MAX_VALUE, (t, u) -> t < u ? t : u);
        System.out.print(min + " ");
    }

    public static void getYedidenBuyukMin02(List<Integer> list) {
        Integer min = list.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7).sorted(Comparator.reverseOrder()).reduce(Integer.MAX_VALUE, (t, u) -> u);
        System.out.print(min + " ");
    }

    public static void getYedidenBuyukMin03(List<Integer> list) {
        Integer min = list.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7).sorted().findFirst().get();
        System.out.println(min + " ");
    }


    /*
    10- ) Ters sıralama ile tekrarsız ve 5'ten büyük elemanların yarı değerlerini
    (elamanın ikiye bölüm sonucunu) bulan bir method oluşturun.
     */
    public static void getTersSiralamaylaTekrarsizElemanlarinYarisi(List<Integer> list) {
        List<Double> sonuc = list.stream().distinct().filter(t -> t > 5).map(t -> t / 2.0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sonuc + " ");
    }


}