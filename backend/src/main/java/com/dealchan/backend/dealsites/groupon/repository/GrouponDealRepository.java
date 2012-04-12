package com.dealchan.backend.dealsites.groupon.repository;

import org.springframework.stereotype.Repository;
import com.dealchan.backend.dealsites.groupon.entity.GrouponDeal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: yingzhe
 * Date: 2/23/12
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public interface GrouponDealRepository extends JpaRepository<GrouponDeal, Long> {
}
