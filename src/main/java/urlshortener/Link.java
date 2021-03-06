/*
 * Copyright (c) 2013 the original author or authors
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
package urlshortener;

import com.fasterxml.jackson.annotation.JsonProperty;

import static io.werval.api.context.CurrentContext.reverseRoutes;

/**
 * Link POJO, intended to be JSON serialized.
 */
public final class Link
{
    public static Link newInstance( String hash, String longUrl )
    {
        Link link = new Link();
        link.hash = hash;
        link.longUrl = longUrl;
        return link;
    }

    @JsonProperty( "hash" )
    public String hash;

    @JsonProperty( "long_url" )
    public String longUrl;

    @JsonProperty( "clicks" )
    public int clicks = 0;

    @JsonProperty( "short_url" )
    public String shortUrl()
    {
        return reverseRoutes().get( API.class, c -> c.redirect( hash ) ).httpUrl();
    }
}
