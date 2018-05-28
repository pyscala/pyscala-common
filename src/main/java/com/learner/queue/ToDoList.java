package com.learner.queue;


import java.util.PriorityQueue;

public class ToDoList extends PriorityQueue<ToDoList.ToDoItem>{

    static class ToDoItem implements Comparable<ToDoItem>{

        private char primary;
        private int sencondary;
        public  Base.BaseLisener item;

        public ToDoItem(char primary,int sencondary,Base.BaseLisener item){
            this.primary = primary;
            this.sencondary = sencondary;
            this.item = item;
        }

        @Override
        public int compareTo(ToDoItem o) {
            if(primary > o.primary){
                return 1;
            }
            if(primary == o.primary){
                if(sencondary > o.sencondary){
                    return 1;
                } else if(sencondary == o.sencondary){
                    return 0;
                }
            }
            return -1;
        }

        public String toString(){
            return String.valueOf(primary) + sencondary + ": " + item;
        }
    }

    public void add(char primary,int sencondary,Base.BaseLisener item){
        super.add(new ToDoItem(primary,sencondary,item));
    }

}

