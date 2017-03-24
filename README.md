# Router: Experiments with a raw gwt router

[![Build Status](https://secure.travis-ci.org/realityforge/gwt-router.png?branch=master)](http://travis-ci.org/realityforge/gwt-router)

## What is Router?

A project that experiments with simplified routing under gwt.

## Router API

This library provides a low-level library for implementing routing or places management within
a web application. The library is designed to be used in conjunction with other higher-level
routing libraries to implement routing within an application.

The routing library attempts to perform routing in 3 distinct phases.

* `PreRouting`: This phase occurs before routing actually occurs and may result in routing being
  aborted before being completed. This is where code that implements guards and security checks
  is expected to be located.
* `Routing`: This phase actually implements the routing operations. The updating of the UI typically
  occurs within this phase.
* `PostRouting`: This phase occurs after routing has completed. This phase usually triggers non-UI
  services such as loading data from the server.

Each phase consists of an list of callbacks that are invoked in succession. Each phase consists of
a unique callback interface that defines the operations allowed during that phase. Each callback has a
synchronous as well as an asynchronous variant. The asynchronous variant should be used sparingly
as it may lead to an unresponsive UI if the callback takes a long time to complete. The `PreRouting`
and `Routing` phases are given access to controls that can abort further route processing. This is
useful when the route processing has either completed or the callback has redirected to a different
location.

The current application bases the location on the hash in the url. When the "location" in an application
changes, the application calls into the `RouteManager` to generate the list of callbacks for each phase
and then iterates through each callback. The list of callbacks for each phase is generated by the 
`RouteManager`. The `RouteManager` contains a list of `RouteDefinition` objects. Each `RouteDefinition`
object includes a pattern that is matched against the new location. The `RouteDefinition` also has
mechanisms for extracting key-value parameters from the matched location. The `RouteDefinition` also
includes one or more callbacks that can be added to the respective phases.

## TODO:

* Start to document router.
* Write annotation processor that generates and configures 1 or more router managers based on the presence of annotations.
* Support routes that dynamically load routes. i.e. Add a route that dynamically loads a set of routes and replaces itself with them.
* Support either child or peer routers. These routers pick up optional routes that are suffixed to the main route and delegated to separate components. Typically these will be things like popup dialogs that contain some state. 
