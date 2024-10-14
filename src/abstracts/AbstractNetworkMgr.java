package abstracts;

/**
 * Abstract class managing network-level operations of a node.
 */
public abstract class AbstractNetworkMgr {

    public abstract void tick();

    public abstract void connectionRequest();

    public abstract void connectionBreak();
}
