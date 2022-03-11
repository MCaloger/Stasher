package com.caloger.stasher.Secret.Repository;

import com.caloger.stasher.Secret.Model.SecretModel;
import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<SecretModel, Long> {
}
