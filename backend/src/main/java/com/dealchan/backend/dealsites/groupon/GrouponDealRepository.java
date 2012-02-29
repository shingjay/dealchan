package com.dealchan.backend.dealsites.groupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
