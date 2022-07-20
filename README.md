# Final Lecture: Authentication

- Class we use to get and set session information: HttpServletRequest
- Class we extend to create a request filter: HandlerInterceptorAdapter, which in turn implements the interface
  HandlerIntercepter, with a few methods (note: can just implement the interface directly)
    - preHandle - before a request is handled
    - postHandle - after a request is handled but before the view is rendered
    - afterCompletion - after the view is rendered
- What is request filtering: check authentication status before any controller methods are called
- Reminder of what @Autowired does for us?
- Code-based configuration:
    - A java class can take care of configuring (in our case registering) app classes
