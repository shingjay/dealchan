package com.dealchan.backend.dealsites.streetdeal.repository;

import org.springframework.stereotype.Repository;
import com.dealchan.backend.dealsites.streetdeal.entity.StreetdealDeal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: ong0
 * Date: 4/5/12
 * Time: 2:42 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public interface StreetdealRepository extends JpaRepository<StreetdealDeal, Long>{
}
