/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.rs;

import java.io.*;
import lombok.AllArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

@AllArgsConstructor
class ClientConfigLoader
{
	private final OkHttpClient okHttpClient;

	static RSConfig fetch(HttpUrl url) throws IOException
	{
//		final Request request = new Request.Builder()
//			.url(url)
//			.build();
//
//
//
//		OkHttpClient okHttpClient = new OkHttpClient.Builder()
//			.connectTimeout(2000, TimeUnit.MILLISECONDS)
//			.build();
//
//		try (final Response response = okHttpClient.newCall(request).execute())
//		{
//			if (!response.isSuccessful())
//			{
//				throw new IOException("Unsuccessful response: " + response.message());
//			}

		final RSConfig config = new RSConfig();
		String str;
		final Reader params = new StringReader("title=RSPS\nadverturl=http://www.runescape.com/g=oldscape/bare_advert.ws\ncodebase=http://127.0.0.1/\ncachedir=oldschool\nstorebase=0\ninitial_jar=gamepack_7456815.jar\ninitial_class=client.class\ntermsurl=http://www.jagex.com/g=oldscape/terms/terms.ws\nprivacyurl=http://www.jagex.com/g=oldscape/privacy/privacy.ws\nviewerversion=124\nwin_sub_version=1\nmac_sub_version=2\nother_sub_version=2\nbrowsercontrol_win_x86_jar=browsercontrol_0_-1928975093.jar\nbrowsercontrol_win_amd64_jar=browsercontrol_1_1674545273.jar\ndownload=1335542\nwindow_preferredwidth=800\nwindow_preferredheight=600\nadvert_height=96\napplet_minwidth=765\napplet_minheight=503\napplet_maxwidth=5760\napplet_maxheight=2160\nmsg=lang0=English\nmsg=tandc=This game is copyright © 1999 - 2020 Jagex Ltd.\\Use of this game is subject to our [\"http://www.runescape.com/terms/terms.ws\"Terms and Conditions] and [\"http://www.runescape.com/privacy/privacy.ws\"Privacy Policy].\nmsg=options=Options\nmsg=language=Language\nmsg=changes_on_restart=Your changes will take effect when you next start this program.\nmsg=loading_app_resources=Loading application resources\nmsg=err_verify_bc64=Unable to verify browsercontrol64\nmsg=err_verify_bc=Unable to verify browsercontrol\nmsg=err_load_bc=Unable to load browsercontrol\nmsg=loading_app=Loading application\nmsg=err_create_target=Unable to create target applet\nmsg=err_create_advertising=Unable to create advertising\nmsg=err_save_file=Error saving file\nmsg=err_downloading=Error downloading\nmsg=ok=OK\nmsg=cancel=Cancel\nmsg=message=Message\nmsg=copy_paste_url=Please copy and paste the following URL into your web browser\nmsg=information=Information\nmsg=err_get_file=Error getting file\nmsg=new_version=Update available! You can now launch the client directly from the OldSchool website.\\nGet the new version from the link on the OldSchool homepage: http://oldschool.runescape.com/\nmsg=new_version_linktext=Open OldSchool Homepage\nmsg=new_version_link=http://oldschool.runescape.com/\nparam=6=0\nparam=16=false\nparam=5=1\nparam=8=true\nparam=12=1\nparam=9=ElZAIrq5NpKN6D3mDdihco3oPeYN2KFy2DCquj7JMmECPmLrDP3Bnw\nparam=2=https://payments.jagex.com/operator/v1/\nparam=7=0\nparam=10=5\nparam=15=0\nparam=17=http://www.runescape.com/g=oldscape/slr.ws?order=LPWM\nparam=14=0\nparam=13=.runescape.com\nparam=1=1\nparam=11=https://auth.jagex.com/\nparam=4=63141\nparam=3=true\nparam=18=\nparam=19=196515767263-1oo20deqm6edn7ujlihl6rpadk9drhva.apps.googleusercontent.com");
		final BufferedReader in = new BufferedReader(params);
		while ((str = in.readLine()) != null)
		{
			int idx = str.indexOf('=');

			if (idx == -1)
			{
				continue;
			}

			String s = str.substring(0, idx);

			switch (s)
			{
				case "param":
					str = str.substring(idx + 1);
					idx = str.indexOf('=');
					s = str.substring(0, idx);

					config.getAppletProperties().put(s, str.substring(idx + 1));
					break;
				case "msg":
					// ignore
					break;
				default:
					config.getClassLoaderProperties().put(s, str.substring(idx + 1));
					break;
			}
		}
//		}

		return config;
	}
}