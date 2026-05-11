package org.oops.basics;

public class DynamicArray {

    int[] data;
    int nextIndex;

    public DynamicArray() {
        data = new int[5];
        nextIndex = 0;
    }

    public int size(){
        return nextIndex;
    }

    public boolean isEmpty(){
        return nextIndex == 0;
    }

    public void add(int element){
        if(nextIndex == data.length) {
            restructure();
        }
        data[nextIndex] = element;
        nextIndex++;
    }

    public void set(int index, int element){
        if(index > nextIndex) return;
        if(index < nextIndex){
            data[index] = element;
        } else {
            add(element);
        }
    }

    public int get(int index){
        if(index < nextIndex){
            return data[nextIndex];
        }
        return -1;
    }

    public int removeLast(){
        if(nextIndex == 0) return -1;
        nextIndex--;
        int value = data[nextIndex];
        data[nextIndex] = 0;
        return value;
    }

    private void restructure() {
        int[] temp = data;
        data = new int[data.length * 2];
        for(int i = 0; i < data.length; i++){
            data[i] = temp[i];
        }
    }
}
