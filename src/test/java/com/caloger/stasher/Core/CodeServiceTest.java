package com.caloger.stasher.Core;

import com.caloger.stasher.Core.Code.Service.CodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class CodeServiceTest {

    private CodeService codeService;

    @Autowired
    public CodeServiceTest(CodeService codeService) {
        this.codeService = codeService;
    }

    @Test
    void generateCode() {
        // given

        // when
        String code = codeService.generateCode();

        // then
        then(code).isNotEmpty();
        then(code.length()).isEqualTo(codeService.getCodeLength());
    }
}