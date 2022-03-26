package com.caloger.stasher.Core.Code.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {

    @Value("${CODE_LENGTH}")
    private int CODE_LENGTH;

    /**
     * Generate a code for URLs to prevent enumeration
     * @return Strign of n length of allowed characters
     */
    public String generateCode() {
        char[] allowedCharacters = {
                'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F', 'G','H','J','K','L','Z','X','C','V','B','N',
                'M', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '1', '2',
                '3', '4', '5', '6', '7', '8', '9', '0'
        };

        final String[] compiled = {""};

        new Random().ints(0, allowedCharacters.length).limit(CODE_LENGTH).forEach(character -> compiled[0] += allowedCharacters[character]);

        return compiled[0];
    }

    public int getCodeLength() {
        return CODE_LENGTH;
    }
}
