package distributions;

/**
 * A generic Generator class which delegates its Seedable ability to a <code>Distributions</code> object.
 */
public abstract class Generator implements Seedable {
    protected Distributions distrib;
    protected Generator() { distrib = new Distributions(); }
    protected Generator(long seed) { distrib = new Distributions(seed); }
    
    // ----- implements Seedable { -----
    public void setSeed(long seed) { distrib.source.setSeed(seed); }
    public long getSeed() { return distrib.source.getSeed(); }
    public void reseed() { distrib.source.reseed(); }
    // ----- } implements Seedable -----
}
