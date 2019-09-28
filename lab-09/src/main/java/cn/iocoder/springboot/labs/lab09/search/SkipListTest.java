package cn.iocoder.springboot.labs.lab09.search;

public class SkipListTest {

    public static void main2(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(3);
        skipList.insert(1);
        skipList.insert(1);
        skipList.insert(1);
        skipList.delete(1);
        skipList.printAll();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            SkipList2 skipList = new SkipList2();
//            SkipList skipList = new SkipList();
            // 测试添加
            skipList.insert(2);
            skipList.insert(1);

            // 测试查询
            System.out.println(skipList.find(1) != null);
            assert skipList.find(1) != null;
            System.out.println(skipList.find(2) != null);
            assert skipList.find(2) != null;
            System.out.println(skipList.find(3) == null); // null
            assert skipList.find(3) == null;

            // 测试删除
            skipList.delete(1);
            System.out.println(skipList.find(1) == null); // null
            assert skipList.find(1) == null;
            System.out.println(skipList.find(2) != null);
            assert skipList.find(2) != null;
        }
    }

}
