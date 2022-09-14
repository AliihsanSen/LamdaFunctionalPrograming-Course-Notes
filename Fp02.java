package lambda_functional_programing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Fp02 {

    /*
  1- ) t-> "Logic" , (t,u) -> "logic"
       Bu yapiya "Lambda Expession"

  2- ) Functional Programming kapsaminda "Lambda Expession" kullanilabilir ama onerilmez.
       "Lambda Expession" yerine "FunctionMethod Reference" tercih edilir.

  3- ) "Method Reference" kullanimi "Class Name :: Method Name

        Ayni zamanda kendi class'larinizi da kullanabilirsiniz.
        Ornegin bir Animal class'ınız var ve bu class "eat()" methoduna sahip ==> "Animal :: eat"
     */

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
        listElemanlariniYazdirFunctional(l); // 8 9 131 10 9 10 2 8
        System.out.println();
        ciftElemanlariYAzdirFunctional(l); // 8 10 10 2 8
        System.out.println();
        tekElemanlarinKareleriniYazdir(l); // 81 17161 81
        System.out.println();
        tekrarsizTekElemanlarinKupunuYazdir(l); // 729 2248091 729
        System.out.println();
        tekrarsizCiftElemanlarinKareToplami01(l); // 168
        System.out.println();
        tekrarsizCiftElemanlarinKareToplami02(l); // 168
        System.out.println();
        tekrarsizCiftElemanlarinKareToplami03(l); // 168
        System.out.println();
        tekrarsizCiftElemanlarinKupununCarpimi(l); // 4096000
        System.out.println();
        getMaxEleman(l); // 131
        System.out.println();
        yedidenBuyukCiftMin(l); // 8
        System.out.println();
        tersSiralamaylaTekrarsizElemanlarinYarisi(l); // [65.5, 5.0, 4.5, 4.0]
    }


    /*
    1- ) Ardışık list elemanlarını aynı satırda aralarında boşluk bırakarak yazdıran
    bir method oluşturun.(Functional ve method reference)
    */
    public static void listElemanlariniYazdirFunctional(List<Integer> list) {

        list.stream().forEach(Utils::ayniSatirdaBosluklaYazdir);
    }


    /*
    2- )Ardışık çift list elementlerini aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Functional)
     */
    public static void ciftElemanlariYAzdirFunctional(List<Integer> list) {
        list.stream().filter(Utils::ciftElemanlariSec).forEach(Utils::ayniSatirdaBosluklaYazdir);
    }


    /*
    3) Ardışık tek list elemanlarının karelerini aynı satırda aralarında
    boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */
    public static void tekElemanlarinKareleriniYazdir(List<Integer> list) {
        list.stream().filter(Utils::tekElemanlariSec).map(Utils::karesiniAl).forEach(Utils::ayniSatirdaBosluklaYazdir);
    }


    /*
    4- ) Ardışık tek list elemanlarının küplerini tekrarsız olarak aynı satırda
    aralarında boşluk bırakarak yazdıran bir method oluşturun.
     */
    public static void tekrarsizTekElemanlarinKupunuYazdir(List<Integer> list) {
        list.stream().filter(Utils::tekElemanlariSec).map(Utils::kupunuAl).forEach(Utils::ayniSatirdaBosluklaYazdir);
    }


    /*
    5- ) Tekrarsız çift elemanların karelerinin toplamını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKareToplami01(List<Integer> list) {
        Integer toplam = list.stream().distinct().filter(Utils::ciftElemanlariSec).map(Utils::karesiniAl).reduce(0, Math::addExact);
        System.out.print(toplam);
    }

    public static void tekrarsizCiftElemanlarinKareToplami02(List<Integer> list) {
        Integer toplam = list.stream().distinct().filter(Utils::ciftElemanlariSec).map(Utils::karesiniAl).reduce(Math::addExact).get();
        System.out.print(toplam);
    }

    public static void tekrarsizCiftElemanlarinKareToplami03(List<Integer> list) {
        Integer toplam = list.stream().distinct().filter(Utils::ciftElemanlariSec).map(Utils::karesiniAl).reduce(0, Integer::sum);
        System.out.print(toplam);
    }


    /*
    6- ) Tekrarsız çift elemanların küpünün çarpımını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKupununCarpimi(List<Integer> list) {
        Integer carpim = list.stream().distinct().filter(Utils::ciftElemanlariSec).map(Utils::kupunuAl).reduce(1, Math::multiplyExact);
        System.out.print(carpim);
    }


    /*
    7- ) List elemanları arasından en büyük değeri bulan bir method oluşturun.
     */
    public static void getMaxEleman(List<Integer> list) {
        Integer max = list.stream().distinct().reduce(Math::max).get();
        System.out.print(max);
    }


    /*
    8- )List elemanları arasından en küçük değeri bulan bir method oluşturun.
    (Method Reference)
     */


    /*
    9- ) List elemanları arasından 7'den büyük, çift, en küçük değeri bulan bir method oluşturun.
     */
    public static void yedidenBuyukCiftMin(List<Integer> list) {
        Integer min = list.stream().distinct().filter(t -> t > 7).filter(Utils::ciftElemanlariSec).reduce(Math::min).get();
        System.out.print(min);
    }


    /*
    10) Ters sıralama ile tekrarsız ve 5'ten büyük elemanların yarı değerlerini
    (elamanın ikiye bölüm sonucunu) bulan bir method oluşturun.
     */
    public static void tersSiralamaylaTekrarsizElemanlarinYarisi(List<Integer> list) {
        List<Double> sonuc = list.
                stream().//Gerekli methodları kullanmamızı sağlar
                distinct().//Tekarlı olanları almaz
                filter(t -> t > 5).//Koşula göre filtreleme yapar
                map(Utils::yarisiniAl).//Her bir elemanın değerini değiştirmeye yarar
                sorted(Comparator.reverseOrder()).//Sıralama yapar
                collect(Collectors.toList());//Elamanları collection'a çevirir.
        System.out.print(sonuc);

    }
}