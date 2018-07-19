package Homework_04;

class TestThreadB extends Thread{
    myFlag f;
    TestThreadB(myFlag f){
        this.f=f;
    }
    public void run(){
        for (int i = 0; i < 5; ) {
            if(f.getCurrentLetter()=='A') {
                System.out.println("B");
                i++;
                f.toggleLetter('B');
            }else {
                f.sleepMe(this);
            }
        }
    }
}
