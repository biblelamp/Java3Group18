package Homework_04;

class TestThreadC extends Thread {
    myFlag f;
    TestThreadC(myFlag f) {
        this.f = f;
    }
    public void run() {
        for (int i = 0; i < 5; ) {
            if (f.getCurrentLetter() == 'B') {
                System.out.println("C");
                i++;
                f.toggleLetter('C');
            } else {
                f.sleepMe(this);
            }
        }
    }
}
