package com.dealchan.backend.dealsource.repository;

import com.dealchan.backend.dealsource.entity.DealSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/11/12
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface DealSourceRepository extends JpaRepository<DealSource, Long> {
}
