package com.seb.account.component.test.integration.service;

import com.lexicon.account.component.domain.Account;
import com.lexicon.account.component.test.common.domain.AccountTestBuilder;
import com.lexicon.account.component.test.common.domain.OrderTestBuilder;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.Assert;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;
import se.lexicon.account.component.service.AccountComponentService;

import java.math.BigDecimal;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class AccountComponentServiceIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = AccountComponentServiceIntegrationTestSuite.SUITE_RULE_CHAIN;

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(AccountComponentServiceIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));

    @Test
    public void testCreatingAccountBalance() {
        AccountComponentService accountComponentService = AccountComponentServiceIntegrationTestSuite.getImportContext().getBean(AccountComponentService.class);
        accountComponentService.createAccount(AccountTestBuilder.builder().withOrders(OrderTestBuilder.builder().build()).build());
        Account account = accountComponentService.getAccount("1111111111");
        Assert.assertNotNull(account);
        Assert.assertEquals(1, account.getOrders().size());
    }


    @Test
    public void testGettingTotalAmount() {
        AccountComponentService accountComponentService = AccountComponentServiceIntegrationTestSuite.getImportContext().getBean(AccountComponentService.class);
        accountComponentService.createAccount(AccountTestBuilder.builder().withSsn("account2").withAmount(BigDecimal.valueOf(100)).build());
        accountComponentService.createAccount(AccountTestBuilder.builder().withSsn("account1").withAmount(BigDecimal.valueOf(100)).build());
        Assert.assertEquals(BigDecimal.valueOf(200.0), accountComponentService.getTotalAmountOnAccounts());
    }

}
