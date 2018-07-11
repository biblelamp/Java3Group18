package YakovAlekseev.Lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> content = new ArrayList<>();

    public void put (T fruit){
        content.add(fruit);
    }

    public T get() {
        return content.remove(0);  //убираем первый
    }

    public float getWeight(){
        float weight = 0;
        for (int i = 0; i < content.size(); i++) {
            weight += content.get(i).getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return (this.getWeight() == box.getWeight());
    }

    public void pourOutToBox(Box<T> box) {
        while (!content.isEmpty()) {
            box.put(this.get());
        }
    }

    public int size(){
        return content.size();
    }

}
