/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeboxlk.pexel.data.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

private const val KEY_AUTHORIZATION = "Authorization"
private const val AUTHORIZATION_TOKEN = "3do2W4LiuQAZZAX9QszjLEtUyornuFiCxQu2UvoR2UgMSaZH9onrDYr1"

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .url(originalRequest.url)
            .addHeader(KEY_AUTHORIZATION, AUTHORIZATION_TOKEN)
            .build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}