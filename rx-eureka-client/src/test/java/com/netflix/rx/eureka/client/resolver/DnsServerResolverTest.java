/*
 * Copyright 2014 Netflix, Inc.
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

package com.netflix.rx.eureka.client.resolver;

import com.netflix.rx.eureka.client.resolver.ServerResolver.Server;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * TODO: The test is using public DNSes, to resolve public domain names.
 * It would be good to have integration tests pool separate from pure unit tests.
 *
 * @author Tomasz Bak
 */
public class DnsServerResolverTest {

    @Test
    @Ignore // Because the DNS resolution fails on CloudBees
    public void testPublicAddressResolution() throws Exception {
        // Google has a long list of addresses.
        DnsServerResolver resolver = new DnsServerResolver("google.com", 80);
        List<ServerResolver.Server> serverEntries = resolver.resolve().take(2).toList().toBlocking().toFuture().get(30, TimeUnit.SECONDS);
        assertEquals("Expected server list with more than one entry", 2, serverEntries.size());
    }

    @Test
    @Ignore
    public void testLocalhost() throws Exception {
        DnsServerResolver resolver = new DnsServerResolver("localhost", 7300);
        List<Server> serverEntries = resolver.resolve().take(1).toList().toBlocking().toFuture().get(30, TimeUnit.SECONDS);
        assertEquals("Expected single localhost entry", 1, serverEntries.size());
    }
}