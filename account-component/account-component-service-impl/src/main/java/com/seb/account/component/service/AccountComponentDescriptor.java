package com.seb.account.component.service;

import com.lexicon.account.component.service.AccountComponentServiceProvider;
import com.so4it.gs.rpc.Component;
import com.so4it.gs.rpc.ServiceBindingType;


@Component(
        name = "checkout",
        serviceProviders = {
                AccountComponentServiceProvider.class,
        },
        defaultServiceType = ServiceBindingType.GS_REMOTING
)
public class AccountComponentDescriptor {
}