/**
 * Tool:IntelliJ IDEA
 * Date:2024-08-23-9:02
 * Description:
 *
 * @ Author:两袖青蛇
 */

public class Test1 implements B1{
    public static void main(String args[]){
        int i;
        Test1 a1=new Test1();
        i =a1.k;
        System.out.println("i="+i);

        byte a = 127;
        byte b = 127;
        a+=b;
        System.out.println(a);
    }
}
interface B1{
    int k=10;
}
