package com.dealchan.backend.batch;

import com.dealchan.backend.dealsource.adapter.DealSourceAdapter;
import com.dealchan.backend.dealsource.entity.DealSource;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3c.dom.Node;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrouponProcessor implements ItemProcessor<Node,DealSource> {


    private DealSourceAdapter dealSourceAdapter;

    @Autowired
    public void setDealSourceAdapter(@Qualifier("dealSourceAdapter") DealSourceAdapter dealSourceAdapter) {
        this.dealSourceAdapter = dealSourceAdapter;
    }

    @Override
    public DealSource process(Node node) throws Exception {

//        NodeList nodelist = node.getChildNodes();
//
//        for(int j = 0; j < list.getLength(); j++) {
//            //contents of item
//            if(child.item(j) instanceof Element) {
//
//            }
//        }


        System.out.println("PROCESSING " + node.toString());
        DealSource dealSource = new DealSource();
        dealSource.setCountry("MALAYSIA");
        dealSource.setTitle("AWESOME");
        dealSource.setDiscount(10.0);
        dealSource.setOriginalPrice(11.0);
        dealSource.setPrice(12.0);
        return dealSource;
// return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
