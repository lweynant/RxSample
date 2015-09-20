package com.lweynant.rxsample;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(EmailValidator.isValidEmail("name@email.com"), is(true));
    }

    @Test
    public void emailValidator_EmailMissesDomain_ReturnsFalse() {
        assertThat(EmailValidator.isValidEmail("name@email"), not(true));
    }

    @Test
    public void emailValidator_EmailMissesAtSymbol_ReturnFalse(){
        assertThat(EmailValidator.isValidEmail("name.email.com"), not(true));
    }
}