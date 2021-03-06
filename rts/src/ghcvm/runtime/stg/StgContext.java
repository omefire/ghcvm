package ghcvm.runtime.stg;

public class StgContext {
    public ArgumentStack argStack = new ArgumentStack();
    public StgTSO currentTSO;
    public Capability myCapability;
    public ReturnCode ret;

    public void merge(AbstractArgumentStack argStack) {
        AbstractArgumentStack stack =
            AbstractArgumentStack.Builder.from(argStack)
            .setSimple(false)
            .build();
        this.argStack = (ArgumentStack) stack;
    }

    public StgClosure R(int index) {
        return argStack.R(index);
    }

    public void R(int index, StgClosure closure) {
        argStack.R(index, closure);
    }

    public Object O(int index) {
        return argStack.O(index);
    }

    public void O(int index, Object closure) {
        argStack.O(index, closure);
    }

    public int I(int index) {
        return argStack.I(index);
    }

    public void I(int index, int closure) {
        argStack.I(index, closure);
    }

    public long L(int index) {
        return argStack.L(index);
    }

    public void L(int index, long closure) {
        argStack.L(index, closure);
    }

    public float F(int index) {
        return argStack.F(index);
    }

    public void F(int index, float closure) {
        argStack.F(index, closure);
    }

    public double D(int index) {
        return argStack.D(index);
    }

    public void D(int index, double closure) {
        argStack.D(index, closure);
    }

    public enum ReturnCode {
        HeapOverflow,
        StackOverflow,
        ThreadYielding,
        ThreadBlocked,
        ThreadFinished
    }
}
