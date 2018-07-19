package Homework_04;

class universalLetterPrintingThread extends Thread{
    myFlag f;
    char letter;
    int repeat;
    universalLetterPrintingThread(myFlag f,char letter, int repeat){
        this.f=f;
        this.letter=letter;
        this.repeat=repeat;
    }
    public void run(){
        for(int i=0;i<repeat;){
            if(f.currentLetterIsPreviousFrom(letter)){
                System.out.println(letter);
                f.toggleLetter(letter);
                i++;
            } else{
                f.sleepMe(this);
            }
        }
    }
}
