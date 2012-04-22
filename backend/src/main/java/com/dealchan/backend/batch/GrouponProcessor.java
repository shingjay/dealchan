package com.dealchan.backend.batch;

import com.dealchan.backend.dealsource.adapter.DealSourceAdapter;
import com.dealchan.backend.dealsource.entity.DealSource;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Node;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrouponProcessor implements ItemProcessor<Node,DealSource> {

    @Autowired
    private DealSourceAdapter dealSourceAdapter;

    @Override
    public DealSource process(Node node) throws Exception {

        DealSource dealSource = new DealSource();
        dealSource.setCountry("MALAYSIA");
        dealSource.setTitle("AWESOME");
        return dealSource;
// return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
