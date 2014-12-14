# URL Shortener - Werval Sample Application

The URL Shortener sample implement an extremely simple HTTP API using GET methods only and outputing JSON documents.
The shortened URLs are stored in-memory, not persisted.
They do not survive an application restart.

A proof of concept client UI implemented with [Twitter Bootstrap](http://getbootstrap.com/) and
[JQuery](http://jquery.com/) allow to play with the API.
It shows the list of shortened URLs and dump all JSON returned by the HTTP API.

This is the simplest sample and it is intended to be so.

It demonstrate the following:

- defining routes,
- basics of controller implementation,
- basics of Global object usage.

Even if this sample is damn simple, it has unit tests covering the whole HTTP API.
They are implemented using [rest-assured](https://code.google.com/p/rest-assured/) from [Jayway](http://www.jayway.com/)
making them short and expressive.

There is even tests for the UI, launching a browser, clicking on things and asserting that the UI behave well.
Thoses tests are implemented using [FluentLenium](http://fluentlenium.org/) from
[Mathilde Lemée](https://github.com/MathildeLemee) that provide a nice DSL on top of Selenium/WebDriver.

Whatever would be your Werval usage, you are encouraged to read this sample code.

To stage the application run:

    gradle installApp

To run from the staged application run:

    ./build/install/url-shortener/bin/url-shortener

To build a standalone distribution of the application run:

    gradle distZip

The distribution can be located at `build/distributions/url-shortener-0.zip`.

To run the whole test suite run:

    gradle test
