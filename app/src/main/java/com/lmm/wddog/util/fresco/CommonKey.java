package com.lmm.wddog.util.fresco;

import android.net.Uri;

import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskStorage;

/**
 * Created by Administrator on 2017/1/28.
 */

public class CommonKey implements CacheKey {

    private String mKey;

    public CommonKey(String key){
        mKey = key;
    }

    @Override
    public boolean equals(Object o) {
        return ((String)o).equals(mKey);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String getUriString() {
        return null;
    }

    @Override
    public boolean containsUri(Uri uri) {
        return false;
    }

    @Override
    public int hashCode() {
        return mKey.hashCode();
    }
}
