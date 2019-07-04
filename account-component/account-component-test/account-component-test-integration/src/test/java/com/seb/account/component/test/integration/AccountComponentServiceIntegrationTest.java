package com.seb.account.component.test.integration;

import com.google.common.collect.Sets;
import com.lexicon.account.component.domain.Account;
import com.lexicon.account.component.test.common.domain.AccountTestBuilder;
import com.seb.account.component.domain.AccountBalance;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.lexicon.account.component.service.AccountComponentService;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.Assert;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;

import java.time.Instant;
import java.util.Optional;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class AccountComponentServiceIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = AccountComponentIntegrationTestSuite.SUITE_RULE_CHAIN;

    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(AccountComponentIntegrationTestSuite.getExportContext().getBean(GigaSpace.class));

    @Test
    public void testCreatingAccountBalance(){
        AccountComponentService accountComponentService = AccountComponentIntegrationTestSuite.getImportContext().getBean(AccountComponentService.class);
        accountComponentService.createAccount(AccountTestBuilder.builder().build());
    }

}
