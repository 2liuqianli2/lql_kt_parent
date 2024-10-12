import java.util.ArrayList;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-08-17-10:04
 * Description:
 *
 * @ Author:两袖青蛇
 */
public class Single {
    public static void main(String[] args) {
        int u;
//        System.out.println(u);

        System.out.println(8.0/3);
        System.out.println(8/3);

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(5);
        nums.add(3);
        nums.add(0,8);
        nums.remove(1);//下标为一的元素被删除

        Single single = new Single();
        System.out.println(single.add());

        double d1=-0.5;
        System.out.println("Ceil d1="+Math.ceil(d1));

//            int x;
//
//            System. out. println("Value is" + x);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        StringBuffer s0 = new StringBuffer();
        System.out.println(s0.capacity());
        StringBuffer s1 = new StringBuffer(5);
        s1.append("1234567890123");
        System.out.println(s1.capacity());
        ArrayList<Object> objects = new ArrayList<>();
        System.out.println(objects);
    }

    public int add(){
        int u =1;
        try{
            u=4;
            System.out.println(u);
            return u;

        }finally {
            u=++u;
            System.out.println(u+"finally");
            return u;
        }
    };

}

class  A{
float func3() {
    long i=3;
    return i;
    }
};
class B extends A{}
abstract class C{
    int add(){
        return 0;
    }

}
class D extends C{

}


//饿汉式单例模式

class GirlFriend{
    private GirlFriend(){};
    private static GirlFriend instance = new GirlFriend();

    public GirlFriend gerInstace(){
        return instance;
    }
}


// 懒汉式单例模式
class BoyFriend{
    private BoyFriend(){};
    private static volatile BoyFriend instance=null;

    public BoyFriend getIntance(){
        if(this.instance==null){
            synchronized(BoyFriend.class){
                if(this.instance==null){
                    instance=new BoyFriend();
                }
            }
        }

        return instance;
    }
}