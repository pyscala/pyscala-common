package com.learner.datenstrukturen;

import lombok.Data;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liufangliang on 2018/4/7.
 */
@Data
 class MinHeap {
    /**
     * 面试问题中机器的总量
     */
    private int maxSize;
    private int currentSize;
    private Node[] nodes;

    public MinHeap(int heapSize) {
        this.maxSize = heapSize;
        nodes = new Node[heapSize];
        currentSize = 0;
    }

    /**
     * 将任务分配到机器上
     *
     * @param num
     */
    public void add(int num) {
        if (currentSize < maxSize) {
            nodes[currentSize] = new Node(num);
            sortUp(currentSize);
            currentSize++;
        } else {
            nodes[0].update(num);
            sortDown(0);
        }
    }

    /**
     * 从下标为index的位置开始自下而上调整
     *
     * @param index
     */
    public void sortUp(int index) {
        if (index < 1) {
            return;
        }
        int fatherIndex = (index - 1) / 2;
        Node temp = nodes[index];
        while (index > 0) {
            if (temp.getSum() < nodes[fatherIndex].getSum()) {
                nodes[index] = nodes[fatherIndex];
                nodes[fatherIndex] = temp;
                index = fatherIndex;
                fatherIndex = (index - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * 从下标为index的位置开始自上而下的调整
     *
     * @param index
     */
    public void sortDown(int index) {
        if (index < 0) {
            return;
        }
        int sonIndex = index * 2 + 1;
        Node temp = nodes[index];
        while (sonIndex < currentSize) {
            if (sonIndex + 1 < currentSize) {
                if (nodes[sonIndex].getSum() > nodes[sonIndex + 1].getSum()) {
                    sonIndex += 1;
                }
            }
            if (temp.getSum() > nodes[sonIndex].getSum()) {
                nodes[index] = nodes[sonIndex];
                nodes[sonIndex] = temp;
                index = sonIndex;
                sonIndex = index * 2 + 1;
            } else {
                break;
            }

        }

    }

    /**
     * 启动任务
     */
    public void start() {
        for (int i = 0; i < currentSize; i++) {
            Node node = nodes[i];
            node.execute();
            System.out.println(node.getSum() + ":" + node.getResult());
        }
    }


    public static void main(String[] args) {

        Integer[] arr = Util.getInitNums(100);
        //init
        System.out.println("init:" + Arrays.toString(arr));

        Util.sort(arr, 0, arr.length - 1);
        //按照分钟数降序排列任务。
        System.out.println("sort:" + Arrays.toString(arr));
        //按照机器数量生成一个总量恒定的堆。
        MinHeap heap = new MinHeap(10);
        //任务分组
        for (Integer i : arr) {
            heap.add(i);
        }
        //执行任务
        heap.start();


    }
}


/**
 * 机器
 */
@Data
class Node {
    /**
     * 本机器上任务分钟数之和
     */
    private Integer sum;
    /**
     * 任务队列
     */
    private BlockingQueue queue = new LinkedBlockingQueue();

    private StringBuilder result;

    public Node(Integer num) {
        sum = num;
        queue.add(sum);
    }

    /**
     * 添加任务到本机器上
     *
     * @param num
     */
    public void update(Integer num) {
        queue.add(num);
        sum += num;
    }

    /**
     * 依次执行分配到的任务。
     */
    public void execute() {
        result = new StringBuilder();
        while (true) {
            Integer n = (Integer) queue.poll();
            if (n == null) {
                break;
            } else {
                //执行该任务。
                result.append("-").append(n);
            }
        }
    }

}

class Util {

    public static Integer[] getInitNums(int size) {
        Integer[] nums = new Integer[size];
        for (int i = 0; i < size; i++) {
            nums[i] = ((int) (Math.random() * size * 10));
        }
        return nums;
    }

    /**
     * 降序快排
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void sort(Integer[] arr, int start, int end) {
        if (start < end) {
            int middle = getMiddle(arr, start, end);
            if (middle - start > 1) {
                sort(arr, start, middle - 1);
            }
            if (end - middle > 1) {
                sort(arr, middle + 1, end);
            }
        }
    }

    public static int getMiddle(Integer[] arr, int start, int end) {
        int temp = arr[start];
        while (start < end) {
            while (start < end) {
                if (temp < arr[end]) {
                    arr[start] = arr[end];
                    start++;
                    break;
                } else {
                    end--;
                }
            }
            while (start < end) {
                if (temp > arr[start]) {
                    arr[end] = arr[start];
                    end--;
                    break;
                } else {
                    start++;
                }
            }
        }
        arr[start] = temp;
        return start;
    }

}
