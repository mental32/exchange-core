/*
 * Copyright 2019 Maksim Zheravin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exchange.core2.tests.integration;

import exchange.core2.core.common.config.InitialStateConfiguration;
import exchange.core2.tests.util.ExchangeTestContainer;
import exchange.core2.tests.util.TestConstants;
import exchange.core2.tests.util.ThroughputTestsModule;
import org.junit.Test;


public class ITMultiOperation {

    @Test(timeout = 60000L)
    public void shouldPerformMarginOperations() throws Exception {
        ThroughputTestsModule.throughputTestImpl(
                () -> new ExchangeTestContainer(2 * 1024, 1, 1, 1536, InitialStateConfiguration.TEST_CONFIG),
                1_000_000,
                1_000,
                2_000,
                2,
                TestConstants.CURRENCIES_FUTURES,
                1,
                ExchangeTestContainer.AllowedSymbolTypes.FUTURES_CONTRACT);
    }

    @Test(timeout = 60000L)
    public void shouldPerformExchangeOperations() throws Exception {
        ThroughputTestsModule.throughputTestImpl(
                () -> new ExchangeTestContainer(2 * 1024, 1, 1, 1536, InitialStateConfiguration.TEST_CONFIG),
                1_000_000,
                1_000,
                2_000,
                2,
                TestConstants.CURRENCIES_EXCHANGE,
                1,
                ExchangeTestContainer.AllowedSymbolTypes.CURRENCY_EXCHANGE_PAIR);
    }


    @Test(timeout = 60000L)
    public void shouldPerformSharded() throws Exception {
        ThroughputTestsModule.throughputTestImpl(
                () -> new ExchangeTestContainer(32 * 1024, 2, 2, 1536, InitialStateConfiguration.TEST_CONFIG),
                1_000_000,
                10_000,
                50_000,
                2,
                TestConstants.ALL_CURRENCIES,
                32,
                ExchangeTestContainer.AllowedSymbolTypes.BOTH);

    }
}
