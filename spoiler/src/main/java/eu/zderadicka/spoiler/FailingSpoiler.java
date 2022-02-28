package eu.zderadicka.spoiler;


public class FailingSpoiler implements Spoiler{

    long ttl;

    

    public FailingSpoiler(double mttf) {
        ttl = (long) new ExpRandom(1/mttf).next();
    }



    @Override
    public void spoil() {
        if (ttl<= 0) {
            throw new SpoilerFailure();
        } else {
            ttl--;
        }
        
    }
    
}
