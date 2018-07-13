package Homework_01;

import java.util.ArrayList;

public class Box {
    public String boxID = "Empty";
    public ArrayList<Fruit> boxContent = new ArrayList<>();

    public Box() {
    }

    public boolean compare(Box b){
        if (this.boxWeight()==b.boxWeight()){
            return true;
        } else {
            return false;
        }
    }

    public void transfer(Box target){
        if(target.boxID.equals(this.boxID)||target.boxID.equals("Empty")){
            for(Fruit f:this.boxContent){
                target.boxContent.add(f);
            }
            target.boxID = this.boxID;
            this.boxContent = new ArrayList<>();
            this.boxID = "Empty";
        } else {
            System.out.println("Error transfering "+this.boxID+" to the box of "+target.boxID);
        }
    }

    public void addFruit (Fruit f){
        if (f.fruitName.equals(this.boxID)||this.boxID.equals("Empty")){
            this.boxID=f.fruitName;
            this.boxContent.add(f);
        } else {
            System.out.println("Error adding "+f.fruitName+" to the box of "+this.boxID);
        }
    }

    public float boxWeight(){
        float weight = 0;
        for (Fruit f: this.boxContent){
            weight+=f.fruitWeight;
        }
        return weight;
    }

    public void printBoxInfo(){
        System.out.println("Box content: "+this.boxID);
        System.out.println("Box weight: "+this.boxWeight());
    }
}
