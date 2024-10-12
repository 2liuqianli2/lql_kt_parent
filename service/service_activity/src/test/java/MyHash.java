import java.util.ArrayList;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-08-23-16:21
 * Description:
 *
 * @ Author:两袖青蛇
 */
public class MyHash {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();

//        for (int i = 1 ; i <= 15 * 10000;i++){
//            int hash = new Object().hashCode();
//            if(!objects.contains(hash)){
//                objects.add(hash);
//            }else{
//                System.out.println("发生hash冲突在"+i+"次"+"值为"+hash);
//            }
//        }
//        System.out.println(objects.size());


        //Integer比较的坑
        Integer integer0 = new Integer(55);
        Integer integer1 = 55;
        Integer integer2 = new Integer(55);
        Integer integer3 = Integer.valueOf(55);

        System.out.println(integer1 == integer0);
        System.out.println(integer0==integer2);
        System.out.println(integer1==integer3);

        double i1 = 0.11 ;
        double i2 = 0.2;
        double i3 = 0.12;
        System.out.println(i1);
        System.out.println(i1-i2);
        System.out.println(i1-i3);


    }


}
