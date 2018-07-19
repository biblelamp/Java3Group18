package Homework_04;
// Этот класс координирует между собой потоки
class myFlag {
    // Здесь необходимо указать со сколькими потоками мы будем работать
    static final int NUMBER_OF_LETTER_PRINTING_THREADS = 3; // 3 - для классической задачи с А В и С

    static char currentLetter=(char)(NUMBER_OF_LETTER_PRINTING_THREADS+'A'-1);
    synchronized char getCurrentLetter(){
        return currentLetter;
    }
    synchronized void toggleLetter(char letter){
            currentLetter = letter;
            notifyAll();
    }
    synchronized void sleepMe(Thread t){
        try {
            t.wait();
        } catch (Exception e) {
        }
    }
    synchronized boolean currentLetterIsPreviousFrom(char letter){
        if (letter=='A') {
            if(currentLetter==(char)(NUMBER_OF_LETTER_PRINTING_THREADS+'A'-1)){
                return true;
            }
        } else {
            if((letter-currentLetter)==1){
                return true;
            }
        }
        return false;
    }
}