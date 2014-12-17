# URL Shortener - Werval Sample Application

The URL Shortener sample implement an extremely simple HTTP API using GET methods only and outputing JSON documents.
The shortened URLs are stored in-memory, not persisted.
They do not survive an application restart.

URL Shortener is implemented using the [Hashids](http://hashids.org/) implementation included in werval.

There is no user interfaces.
You'll find bellow the various [httpie](http://httpie.org) commands you can use to use the HTTP API.

- List all shortened urls: `http http://localhost:23023/api/list`
- Shorten a given url: `http "http://localhost:23023/api/shorten?url=http://example.com/"`
- Expand a url given its hash: `http "http://localhost:23023/api/expand?hash=9W6k"`
- Lookup existing short url for a given one: `http "http://localhost:23023/api/lookup?url=http://example.com/"`
- Open shortened url, get redirection: `http://localhost:23023/9W6k`

This is the simplest sample and it is intended to be so.

It demonstrate the following:

- defining routes,
- basics of controller implementation,
- basics of Global object usage.

Even if this sample is damn simple, it has unit tests covering the whole HTTP API.
They are implemented using [rest-assured](https://code.google.com/p/rest-assured/) from [Jayway](http://www.jayway.com/)
making them short and expressive.

Whatever would be your Werval usage, you are encouraged to read this sample code.

To run in development mode: `gradle devshell`

To run in production mode: `gradle start`

To run tests: `gradle check`

To build a production distribution: `gradle distZip`

To see all available tasks: `gradle tasks`
