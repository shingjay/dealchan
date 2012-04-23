package com.dealchan.backend.batch;

import com.dealchan.backend.dealsource.entity.DealSource;
import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSWriter implements ItemWriter<DealSource> {


    private DealSourceRepository dealSourceRepository;

    @Autowired
    public void setDealSourceRepository(@Qualifier("dealSourceRepository") DealSourceRepository dealSourceRepository) {
        this.dealSourceRepository = dealSourceRepository;
    }

    @Override
    public void write(List<? extends DealSource> dealSources) throws Exception {

        try {
            System.out.println("WRITING : " + dealSources.toString());
            dealSourceRepository.save(dealSources);
            //dealSourceRepository.flush();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
