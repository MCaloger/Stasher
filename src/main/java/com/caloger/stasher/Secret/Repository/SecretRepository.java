package com.caloger.stasher.Secret.Repository;

import com.caloger.stasher.Secret.Model.SecretModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SecretRepository extends CrudRepository<SecretModel, Long> {
    public SecretModel findByCode(String code);

    public boolean existsByCode(String code);

    @Query("select secret from SecretModel secret where secret.expiry <= :expiry")
    public List<SecretModel> findAllWithExpiredTimeBefore(@Param("expiry") LocalDateTime localDateTime);
}
