package com.lweynant.rxsample.components;

import com.lweynant.rxsample.RegisterFragment;
import com.lweynant.rxsample.modules.ValidatorModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lweynant on 21/09/15.
 */
@Component(modules = {ValidatorModule.class})
@Singleton
public interface RegisterFragmentComponent {
    void inject(RegisterFragment fragment);

}
