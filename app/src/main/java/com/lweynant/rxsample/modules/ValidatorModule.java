package com.lweynant.rxsample.modules;

import com.lweynant.rxsample.EmailValidator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lweynant on 20/09/15.
 */
@Module
@Singleton
public class ValidatorModule {
    @Provides
    EmailValidator providesEmailValidator(){
        return new EmailValidator();
    }
}
