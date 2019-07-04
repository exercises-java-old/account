package com.seb.account.api.test.integration;

import com.google.common.collect.Sets;
import com.seb.account.api.client.AccountApiClient;
import com.seb.account.api.client.AccountApiProvider;
import com.seb.account.component.domain.AccountBalance;
import com.seb.account.component.domain.AccountTransaction;
import com.seb.account.component.domain.CreateAccountBalanceRequest;
import com.lexicon.account.component.test.common.domain.BalanceTestBuilder;
import com.lexicon.account.component.test.common.domain.CreateAccountTransactionRequestTestBuilder;
import com.so4it.api.interceptor.request.RequestContextClientInterceptor;
import com.so4it.api.interceptor.request.RequestContextServerInterceptor;
import com.so4it.api.test.common.ApiFrameworkBootstrapTestRule;
import com.so4it.api.test.common.ApiFrameworkCommonTest;
import com.so4it.api.test.common.SatisfiedWhenClientConnected;
import com.so4it.common.bean.BeanContext;
import com.so4it.test.category.IntegrationTest;
import com.so4it.test.common.probe.Poller;
import com.so4it.test.gs.rule.ClearGigaSpaceTestRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.openspaces.core.GigaSpace;

import java.time.Instant;

import static com.seb.account.api.test.integration.AccountApiIntegrationTestSuite.API_REGISTRY;
import static com.seb.account.api.test.integration.AccountApiIntegrationTestSuite.DYNAMIC_CONFIGURATION;

/**
 * @author Magnus Poromaa {@literal <mailto:magnus.poromaa@so4it.com/>}
 */
@Category(IntegrationTest.class)
public class AccountApiClientClientIntegrationTest {

    @ClassRule
    public static final RuleChain SUITE_RULE_CHAIN = AccountApiIntegrationTestSuite.SUITE_RULE_CHAIN;


    @Rule
    public ClearGigaSpaceTestRule clearGigaSpaceTestRule = new ClearGigaSpaceTestRule(AccountApiIntegrationTestSuite.getComponentRule().getBean(GigaSpace.class));




    private static final BeanContext BEAN_CONTEXT = ApiFrameworkCommonTest.createClientBeanContext(DYNAMIC_CONFIGURATION);



    @Rule
    public ApiFrameworkBootstrapTestRule apiFrameworkBootstrapTestRule = ApiFrameworkBootstrapTestRule.builder()
            .withBeanContext(BEAN_CONTEXT)
            .withDynamicConfiguration(DYNAMIC_CONFIGURATION)
            .withApiRegistryClient(API_REGISTRY)
            .withImports(AccountApiProvider.class)
            .withExports()
            .withClientInterceptors(new RequestContextClientInterceptor())
            .withServerInterceptors(new RequestContextServerInterceptor())
            .build();



    @Test
    public void testCreatingAccountBalance() throws Exception {
        AccountApiClient accountApiClient = BEAN_CONTEXT.getBean(AccountApiClient.class);
        Poller.pollAndCheck(SatisfiedWhenClientConnected.create(accountApiClient));

        AccountBalance accountBalanceOne = accountApiClient.createAccountBalance(CreateAccountBalanceRequest.builder()
                .withArrangementId("1")
                .withBatchId("aaa")
                .withInsertionTimestamp(Instant.parse("2019-06-27T09:00:00.000Z"))
                .withBalances(Sets.newHashSet(BalanceTestBuilder.builder().build())).build());
        AccountBalance accountBalanceTwo = accountApiClient.createAccountBalance(CreateAccountBalanceRequest.builder()
                .withArrangementId("1")
                .withBatchId("bbb")
                .withInsertionTimestamp(Instant.parse("2019-06-27T10:00:00.000Z"))
                .withBalances(Sets.newHashSet(BalanceTestBuilder.builder().build())).build());
        //Optional<AccountBalance> accountBalanceOptional = accountComponentService.getAccountBalance(accountBalanceOne.getArrangementId());
        //Assert.assertTrue(accountBalanceOptional.isPresent());
        //Assert.assertEquals(Integer.valueOf(2),accountBalanceOptional.getAccountBalance().getSequenceNumber());
    }

    @Test
    public void testCreatingAccountTransaction() throws Exception {
        AccountApiClient accountApiClient = BEAN_CONTEXT.getBean(AccountApiClient.class);
        Poller.pollAndCheck(SatisfiedWhenClientConnected.create(accountApiClient));

        AccountTransaction accountTransaction = accountApiClient.createAccountTransaction(CreateAccountTransactionRequestTestBuilder.builder().build());
    }

}