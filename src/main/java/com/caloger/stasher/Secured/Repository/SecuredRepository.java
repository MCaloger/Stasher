package com.caloger.stasher.Secured.Repository;


import com.caloger.stasher.Secured.Model.SecuredModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SecuredRepository extends CrudRepository<SecuredModel, Long> {
    public SecuredModel findByCode(String code);
    public boolean existsByCode(String code);

    @Query("SELECT secured FROM SecuredModel secured WHERE secured.expiry <= :expiry")
    public List<SecuredModel> findAllWithExpiredTimeBefore(@Param("expiry") LocalDateTime localDateTime);
}
