package eu.zderadicka.spoiler;

public class DelayingSpoiler implements Spoiler {

    private ExpRandom rng;

    public DelayingSpoiler(double averageDelay) {
        this.rng = new ExpRandom(1/averageDelay);
    }

    

    @Override
    public void spoil() {
        try {
            Thread.sleep((long) rng.next());
        } catch (InterruptedException e) {
           // ignore for now 
        }
        
    }
    
}
