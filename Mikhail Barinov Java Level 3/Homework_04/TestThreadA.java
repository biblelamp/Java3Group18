package Homework_04;

class TestThreadA extends Thread {
    myFlag f;
    TestThreadA(myFlag f) {
        this.f = f;
    }
    public void run() {
        for (int i = 0; i < 5; ) {
            if (f.getCurrentLetter() == 'C') {
                System.out.println("A");
                i++;
                f.toggleLetter('A');
            } else {
                f.sleepMe(this);
            }
        }
    }
}

