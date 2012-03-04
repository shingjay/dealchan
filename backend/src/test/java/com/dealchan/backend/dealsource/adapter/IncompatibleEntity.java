package com.dealchan.backend.dealsource.adapter;

import com.dealchan.backend.dealsource.entity.DealSource;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 3/1/12
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class IncompatibleEntity extends DealSource {

    private double incompatibleField;

    public double getIncompatibleField() {
        return incompatibleField;
    }

    public void setIncompatibleField(double incompatibleField) {
        this.incompatibleField = incompatibleField;
    }
}
