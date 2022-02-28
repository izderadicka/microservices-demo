package eu.zderadicka.spoiler;

public class SpoilerFailure extends RuntimeException{
    public SpoilerFailure() {
        super("This is a random error introduced by Spoiler");
    }
}
