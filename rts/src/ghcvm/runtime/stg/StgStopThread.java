package ghcvm.runtime.stg;

import java.util.Deque;
import java.util.ListIterator;

import ghcvm.runtime.stg.StgTSO;
import ghcvm.runtime.stg.StgContext;
import ghcvm.runtime.exception.StgException;
import static ghcvm.runtime.stg.StgTSO.WhatNext.ThreadComplete;
import static ghcvm.runtime.stg.StgContext.ReturnCode.ThreadFinished;

public class StgStopThread extends StackFrame {

    @Override
    public void stackEnter(StgContext context) {
        StgTSO currentTSO = context.currentTSO;
        ListIterator<StackFrame> sp = context.sp;
        sp.remove();
        sp.add(new StgEnter(context.R1));
        currentTSO.whatNext = ThreadComplete;
        context.ret = ThreadFinished;
        throw StgException.stgReturnException;
    }

    @Override
    public RaiseAsyncResult doRaiseAsync() {
        tso.whatNext = ThreadKilled;
        sp.remove();
        return Finish;
    }
}