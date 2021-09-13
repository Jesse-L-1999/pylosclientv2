/*
 * Copyright (c) 2019, xperiaclash <https://github.com/xperiaclash>
 * Copyright (c) 2019, ganom <https://github.com/Ganom>
 * Copyright (c) 2019, gazivodag <https://github.com/gazivodag>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.rsps;

import net.runelite.api.Client;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.pf4j.Extension;

import javax.inject.Inject;
import java.math.BigInteger;

@Extension
@PluginDescriptor(
        name = "RSPS Plugin",
        enabledByDefault = true,
        description = "RSPS modulus",
        hidden = true
)
public class RSPSPlugin extends Plugin
{
    @Inject
    private Client client;

    private String modulus = "aabbb42d0811592800b76477d15fbd20696620ea288daee092b53dd4b97d2d36678e713e10e23f46b51e6a21f7566bcab754f7eaa97f3ad58def83b2b42d1e41383e265fd02690a4eecf38d9cbf34b3e131f1184b056f283983fcd53b598340e36eab86fcbce2186c0b155f1cad440aa1af801a46a675f7834d97f7aef80bff02a6e481e3289f9cd52f622171af3535a6c2c03848657f888e3a7d1471590898cb41c8b805f78aeb10b60ddf256e20ac227f25e77df908ae726b6b094ce5fb909e16627588f9252db81e74b9cf3c02b1e542b8aadc6fd14a1ea32d994b61546d93b5c01efe8ae82a071f1ba3a57509c07a08d7dde61d7fc1d00bf886bf93dc4b1";

    @Override
    protected void startUp()
    {
        client.setModulus(new BigInteger(modulus, 16));
    }

    @Override
    protected void shutDown()
    {
        client.setModulus(null);
    }
}