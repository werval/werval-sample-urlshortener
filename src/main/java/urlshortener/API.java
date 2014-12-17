/*
 * Copyright (c) 2013-2014 the original author or authors
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

import io.werval.api.outcomes.Outcome;
import io.werval.modules.json.JSON;
import java.net.URL;
import java.util.Collection;

import static io.werval.api.context.CurrentContext.application;
import static io.werval.api.context.CurrentContext.outcomes;

/**
 * URL Shortener HTTP API.
 */
public class API
{
    private final ShortenerService shortener;
    private final JSON json;

    public API()
    {
        this.shortener = application().metaData().get( ShortenerService.class, "shortener" );
        this.json = application().plugin( JSON.class );
    }

    /**
     * List shortened urls and their hash.
     *
     * @return  application/json array filled with Link objects.
     */
    public Outcome list()
    {
        return outcomes().ok( json.toJSON( shortener.list() ) ).asJson().build();
    }

    /**
     * Shorten a URL.
     *
     * @param url Long URL
     *
     * @return application/json Link object.
     */
    public Outcome shorten( URL url )
    {
        Link link = shortener.shorten( url.toString().trim() );
        return outcomes().ok( json.toJSON( link ) ).asJson().build();
    }

    /**
     * Expand a hash to its corresponding long URL.
     *
     * @param hash Hash
     *
     * @return application/json Link object.
     */
    public Outcome expand( String hash )
    {
        Link link = shortener.link( hash.trim() );
        if( link == null )
        {
            return outcomes().notFound().build();
        }
        return outcomes().ok( json.toJSON( link ) ).asJson().build();
    }

    /**
     * Lookup existing shortened URLs from long URL.
     *
     * @param url Long URL
     *
     * @return application/json array filled with Link object.
     */
    public Outcome lookup( URL url )
    {
        String urlString = url.toString().trim();
        Collection<Link> list = shortener.lookup( urlString );
        if( list.isEmpty() )
        {
            return outcomes().notFound().build();
        }
        return outcomes().ok( json.toJSON( list ) ).asJson().build();
    }

    /**
     * Redirect to long URL from hash.
     *
     * @param hash Hash
     *
     * @return 303 Redirection to long URL
     */
    public Outcome redirect( String hash )
    {
        Link link = shortener.link( hash.trim() );
        if( link == null )
        {
            return outcomes().notFound().build();
        }
        link.clicks += 1;
        return outcomes().seeOther( link.longUrl ).build();
    }
}
