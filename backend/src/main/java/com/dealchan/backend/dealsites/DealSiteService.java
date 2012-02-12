package com.dealchan.backend.dealsites;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/11/12
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DealSiteService<T> {
    public List<T> getDealsOfTheDay();

}
