package com.sandip.user.service.UserService.controllers;


import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);


    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

}


public class TestClass {

    public static void main(String[] args) {
        /*HashSet<Person> set = new HashSet<>();
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);

        set.add(p1);
        set.add(p2);

        List list = Arrays.asList("Sandip","More");
        System.out.println("list - > "  +list);

        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "Apple");
        hashtable.put(2, "Banana");
        hashtable.put(3, "Cherry");

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(null, "Apple");
        hashMap.put(null, null);
        hashMap.put(3, null);

        HashSet<String>  hs = new HashSet<>();
        hs.add(null);
        hs.add(null);


        System.out.println("hashtable: " + hashtable); // Should print 1 if equals and hashCode are correctly implemented
        System.out.println("hashMap size: " + hs.size()); // Should print 1 if equals and hashCode are correctly implemented


        List<String> lista = new ArrayList<String>();

        lista.add("Eat");
        lista.add("Coffee");
        lista.add("Code");
        lista.add("Sleep");
        lista.add("Repeat");

        // Synchronizing ArrayList in Java
        lista = Collections.synchronizedList(lista);

        // we must use synchronize block to avoid
        // non-deterministic behavior
        synchronized (lista)
        {
            Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }*/


        List<Integer> list = Arrays.asList(100,10, -1,50,22 , 0,32,20);

        Optional<Integer> sum = list.stream().reduce((a, b)-> a+b);
        System.out.println(sum);

        double avg = list.stream().mapToInt(n->n).average().getAsDouble();

        List<Integer> strtWithList= list.stream().map(n->String.valueOf(n)).filter(n->n.startsWith("2")).map(Integer::valueOf).collect(Collectors.toList());

        System.out.println(strtWithList);

        int max = list.stream().max(Integer::compare).get();
        int min = list.stream().min(Comparator.comparing(Integer::valueOf)).get();
        System.out.println(max +" __min - " + min);

        int secondMax = list.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().get();

        System.out.println("scond -- " + secondMax);

        List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        List<String> result = IntStream.range(0, strings.size())
                .filter(i -> (i % 2 == 1))
                .mapToObj(strings::get)
                .collect(Collectors.toList());

        System.out.println("Selected strings are: " + result);

    }
}
