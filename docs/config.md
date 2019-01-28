# Hikaku (比較) - Config

The following is a list if configurations.

| config parameter | since | default value | description |
| --- | --- | --- | --- |
| ignorePaths | 1.0.0 | `emptySet()` | Takes a set of paths that should be omitted during matching. |
| ignoreHttpMethodHead | 1.0.0 | `false` | If set to `true` matching will omit endpoints providing http method `HEAD`. |
| ignoreHttpMethodOptions | 1.0.0 | `false` | If set to `true` matching will omit endpoints providing http method `OPTIONS`. |
| reporter | 1.0.0 | `CommandLineReporter()` | A reporter receives the match results before the assertion is called. Default value is the `CommandLineReporter` which simply prints the results to command line. Another built-in option is the `NoOperationReporter` which does nothing. |