package com.lmm.wddog.event;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2017/1/22.
 */

public class FragmentJumpEvent {
    public SupportFragment fragment;

    public FragmentJumpEvent(SupportFragment fragment){
        this.fragment = fragment;
    }
}
