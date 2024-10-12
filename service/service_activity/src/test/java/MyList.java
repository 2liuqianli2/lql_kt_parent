import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-08-24-13:40
 * Description:列表去重的五种方法
 *
 *
 * @ Author:两袖青蛇
 */
public class MyList {

    List <Integer>list = Arrays.asList(70,70,-1,5,3,3,4,4,4,99);


    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.func1();
        myList.func2();
        myList.func3();
        myList.func4();
        myList.func5();
    }
    public void func1(){
        List<Integer> list1=new ArrayList<>();
        for(int i = 0; i<list.size();i++){
            if(!list1.contains(list.get(i))){
                list1.add(list.get(i));
            }
        }
        System.out.println(list1);
    }

    public void func2(){
        List<Integer> list1=new ArrayList<>();
        List list2;
        Set hashSet = new HashSet<>(list);
        list1 = new ArrayList(hashSet);
        System.out.println(list1);

        LinkedHashSet linkedHashSet = new LinkedHashSet<>(list);
        list2 = new ArrayList(linkedHashSet);
        System.out.println(list2);
    }

    public void func3(){
        List<Integer> list1=new ArrayList<>();
        list1 = list.stream().distinct().collect(Collectors.toList());
//        list1.forEach(s-> System.out.print(s+" "));
        System.out.println(list1);
    }

    public void func4(){
        List<Integer> list1 = new ArrayList<>(list);

        for (Integer i:list) {
            if(list1.indexOf(i)!=list1.lastIndexOf(i)){
                list1.remove(i);
            }
        }
        System.out.println(list1);
    }

    public void func5(){
        List<Integer> list1 = new ArrayList<>(list);
        for (int i = 0; i < list1.size()-1; i++) {

            for (int j = list1.size()-1; j >i; j--) {
                if (list1.get(i)==list1.get(j)){
                    list1.remove(j);
                }
            }

        }

        System.out.println(list1);
    }
}
