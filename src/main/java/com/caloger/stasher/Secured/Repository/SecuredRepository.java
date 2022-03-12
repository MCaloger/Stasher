package com.caloger.stasher.Secured.Repository;

import com.caloger.stasher.Secured.Model.SecuredModel;
import org.springframework.data.repository.CrudRepository;

public interface SecuredRepository extends CrudRepository<SecuredModel, Long> {
    public SecuredModel findByCode(String code);
}
