package com.learner.queue;

import java.util.*;

public class DemoTest implements Base{

    public static Map<Integer,BaseLisener> map = new HashMap<Integer,BaseLisener>();

    public static List<BaseLisener> task = new ArrayList<BaseLisener>();

    public static List<String> list = new ArrayList<String>();

    public static ToDoList toDoList = new ToDoList();//优先级队列

    public static Collection<Person> people = new ArrayList<Person>();

    private static int x = 0;

    public static void get(int priority, Base.BaseLisener baseLisener){
        task.add(baseLisener);
        map.put(priority,baseLisener);
        toDoList.add('A',priority,baseLisener);
    }

    public static void requestByPriority(ToDoList.ToDoItem toDoItem){
        //执行操作。。。
        //回调操作
        toDoItem.item.success(1);
        //toDoItem.item.failed();
    }

    public static void request(int i, Base.BaseLisener baseLisener){
        //执行操作。。。
        //回调操作
        if(i >= 0){
            baseLisener.success(i);
        }else{
            baseLisener.failed();
        }

    }

    public void startTaskByPQ(){
        while(!toDoList.isEmpty()){
            requestByPriority(toDoList.remove());
        }
    }

    public void startTask(){
        for (int i = 0; i < task.size(); i++) {
            request(i,task.get(i));
        }
    }

    public void init(){

        for(int j = 0; j < 5; j++) {

            Person person = new Person();
            String s1 = x + "";

            person.setOld(x);
            person.setName(s1);

            get(x++,new Base.BaseLisener() {
                @Override
                public void success(Object o) {

                    get(x++, new BaseLisener() {
                        @Override
                        public void success(Object o) {
                            System.out.println(x);
                        }
                        @Override
                        public void failed() {}
                    });

//                    System.out.println(s1);//每次传进来的引用s1都是不一样的，s1指向唯一的String
//                    list.add(s1);
//                    people.add(person);//collection的add的方法传的是引用，容器里存的是引用，对象存在于堆里
//                    person.setOld(100);//传进来的person引用都指向同一地址，其是在for/循环外创建了一次，person属性改变，容器get获得的是指向person的引用
                }

                @Override
                public void failed() {
                    System.out.println("this is failed");
                }

            });
        }
    }

    public static void main(String[] args) {
        DemoTest demoTest = new DemoTest();
        demoTest.init();
        demoTest.startTaskByPQ();
       // demoTest.startTask();
    }

    @Override
    public void call(BaseLisener baseLisener) {
        init();
        System.out.println("execut this task...finish");
        baseLisener.failed();
        baseLisener.success(1);
    }
}
