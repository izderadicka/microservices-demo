package eu.zderadicka.spoiler;

public class FailingAndDelayingSpoiler implements Spoiler {

    private FailingSpoiler failingSpoiler;
    private DelayingSpoiler delayingSpoiler;

    public FailingAndDelayingSpoiler(double mttf, double averageDelay) {
        failingSpoiler = new FailingSpoiler(mttf);
        delayingSpoiler = new DelayingSpoiler(averageDelay);
    }

    @Override
    public void spoil() {
        failingSpoiler.spoil();
        delayingSpoiler.spoil();
    }
    
}
