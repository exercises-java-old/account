package com.lexicon.account.component.domain;

import com.so4it.annotation.Allowed;
import com.so4it.common.domain.DomainClass;
import com.so4it.common.util.object.Required;
import com.so4it.common.util.object.ValueObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@DomainClass
public class Account extends ValueObject {

    private static final long serialVersionUID = 1L;


    private String id;


    private String ssn;

    private BigDecimal amount;


    private Account() {
    }

    private Account(Builder builder) {
        this.id = Required.notNull(builder.id,"id");
        this.ssn = Required.notNull(builder.ssn,"ssn");
        this.amount = Required.notNull(builder.amount,"amount");
    }


    public String getId() {
        return id;
    }


    public String getSsn() {
        return ssn;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    @Override
    protected Object[] getIdFields() {
        return new Object[]{id,amount};
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder implements com.so4it.common.builder.Builder<Account>{


        private String id;


        private String ssn;


        private BigDecimal amount;



        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withSsn(String ssn){
            this.ssn = ssn;
            return this;
        }


        public Builder withAmount(BigDecimal amount){
            this.amount = amount;
            return this;
        }

        @Override
        public  Account build() {
            return new Account(this);
        }
    }


}
