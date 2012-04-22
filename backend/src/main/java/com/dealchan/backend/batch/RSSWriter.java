package com.dealchan.backend.batch;

import com.dealchan.backend.dealsource.entity.DealSource;
import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSWriter implements ItemWriter<DealSource> {

    @Autowired
    private DealSourceRepository dealSourceRepository;

    @Override
    public void write(List<? extends DealSource> dealSources) throws Exception {
        dealSourceRepository.save(dealSources);
    }
}
