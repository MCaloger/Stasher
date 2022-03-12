package com.caloger.stasher.Core.Domain.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DomainService {
    @Value("${domain}")
    private String domain;

    public String getDomain() {
        return this.domain;
    }
}
