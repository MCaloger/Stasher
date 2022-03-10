package com.caloger.stasher.Secrets;

import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<SecretModel, Long> {
}
