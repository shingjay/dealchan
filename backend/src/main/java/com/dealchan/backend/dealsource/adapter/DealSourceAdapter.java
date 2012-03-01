package com.dealchan.backend.dealsource.adapter;

import com.dealchan.backend.dealsource.entity.DealSource;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/12/12
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DealSourceAdapter<T> {
    public DealSource convert(T object);
    public Collection<DealSource> convert(Collection<T> objectList);
}
