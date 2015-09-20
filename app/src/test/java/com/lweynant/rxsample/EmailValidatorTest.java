package com.lweynant.rxsample;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class EmailValidatorTest {
    private EmailValidator sut;

    @Before
    public void setUp(){
        sut = new EmailValidator();
    }

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(sut.isValidEmail("name@email.com"), is(true));
    }

    @Test
    public void emailValidator_EmailMissesDomain_ReturnsFalse() {
        assertThat(sut.isValidEmail("name@email"), not(true));
    }

    @Test
    public void emailValidator_EmailMissesAtSymbol_ReturnFalse(){
        assertThat(sut.isValidEmail("name.email.com"), not(true));
    }
}