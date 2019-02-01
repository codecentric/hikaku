package de.codecentric.hikaku

import de.codecentric.hikaku.converter.EndpointConverter
import de.codecentric.hikaku.converter.spring.SpringConverter.Companion.IGNORE_ERROR_ENDPOINTS
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.matcher.EndpointMatchResult
import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.endpoints.QueryParameter
import de.codecentric.hikaku.matcher.MatchResultGroup
import de.codecentric.hikaku.reporter.NoOperationReporter
import de.codecentric.hikaku.reporter.Reporter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError
import kotlin.test.assertFailsWith

class HikakuTest {

    @Nested
    inner class PreCheckTests {

        @Test
        fun `specification and implementation having different amounts of endpoints in conversion results let the test fail`() {
            //given
            val specificationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporter = NoOperationReporter()
                    )
            )

            //when
            assertFailsWith<AssertionFailedError> {
                hikaku.match()
            }
        }
    }

    @Nested
    inner class EndpointBasicsTests {

        @Test
        fun `Paths in random order match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint("/c"),
                            Endpoint("/a"),
                            Endpoint("/b")

                    )
                    override val supportedFeatures = SupportedFeatures()
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint("/b"),
                            Endpoint("/c"),
                            Endpoint("/a")
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.endpointMatchResults).hasSize(3)
                assertThat(reporter.endpointMatchResults[0].matches()).isTrue()
                assertThat(reporter.endpointMatchResults[1].matches()).isTrue()
                assertThat(reporter.endpointMatchResults[2].matches()).isTrue()
        }

        @Test
        fun `http methods in random order match`() {
            //given
            val specificationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", GET)

                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", DELETE)
                )
                override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
            }

            val reporter = object : Reporter {
                var endpointMatchResults = emptyList<MatchResultGroup>()
                override fun report(endpointMatchResults: List<MatchResultGroup>) {
                    this.endpointMatchResults = endpointMatchResults
                }
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporter = reporter
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.endpointMatchResults).hasSize(3)
            assertThat(reporter.endpointMatchResults[0].matches()).isTrue()
            assertThat(reporter.endpointMatchResults[1].matches()).isTrue()
            assertThat(reporter.endpointMatchResults[2].matches()).isTrue()
        }
    }

    @Nested
    inner class FeatureTests {
        @Nested
        inner class PathParameterTests {

            @Test
            fun `Path parameter match if option is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("id")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("id")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.endpointMatchResults).hasSize(1)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].relatesTo).isEqualTo(Feature.PathParameter.toString())
                assertThat(reporter.endpointMatchResults[0].matchResults[0].specificationValue).isEqualTo("id")
                assertThat(reporter.endpointMatchResults[0].matchResults[0].implementationValue).isEqualTo("id")
                assertThat(reporter.endpointMatchResults[0].matchResults[0].matches).isTrue()
            }

            @Test
            fun `Path parameter are skipped if option is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("id")
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures()
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{id}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("othername")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).matchResults).isEmpty()
            }
        }

        @Nested
        inner class QueryParameterNameTests {

            @Test
            fun `Query parameter names match if option is supported by both converters`() {
                //given
                val queryParameterName = "filter"
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter(queryParameterName)
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterName)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter(queryParameterName)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterName)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.endpointMatchResults).hasSize(1)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].relatesTo).isEqualTo(Feature.QueryParameterName.toString())
                assertThat(reporter.endpointMatchResults[0].matchResults[0].specificationValue).isEqualTo(queryParameterName)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].implementationValue).isEqualTo(queryParameterName)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].matches).isTrue()
            }

            @Test
            fun `Query parameter names are skipped if option is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("filter")
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures()
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("tag")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterName)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).matchResults).isEmpty()
            }
        }

        @Nested
        inner class QueryParameterRequiredTests {

            @Test
            fun `Query parameter required matches if option is supported by both converters`() {
                //given
                val queryParameterName = "filter"
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter(queryParameterName, true)
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterRequired)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter(queryParameterName, true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterRequired)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.endpointMatchResults).hasSize(1)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].relatesTo).isEqualTo(Feature.QueryParameterRequired.toString())
                assertThat(reporter.endpointMatchResults[0].matchResults[0].specificationValue).isEqualTo(true)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].implementationValue).isEqualTo(true)
                assertThat(reporter.endpointMatchResults[0].matchResults[0].matches).isTrue()
            }

            @Test
            fun `Query parameter required is skipped if option is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("filter", true)
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures()
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("tag", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameterRequired)
                }

                val reporter = object : Reporter {
                    var endpointMatchResults = emptyList<MatchResultGroup>()
                    override fun report(endpointMatchResults: List<MatchResultGroup>) {
                        this.endpointMatchResults = endpointMatchResults
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = reporter
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).matchResults).isEmpty()
            }
        }
    }

    @Nested
    inner class ConfigTests {

        @Test
        fun `ignoreHttpMethodHead must ignore endpoints with http method HEAD on both specification and implementation`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                var endpointMatchResults = emptyList<MatchResultGroup>()
                override fun report(endpointMatchResults: List<MatchResultGroup>) {
                    this.endpointMatchResults = endpointMatchResults
                }
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverter,
                    HikakuConfig(
                            ignoreHttpMethodHead = true,
                            reporter = reporter
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.endpointMatchResults).hasSize(1)
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).httpMethodMatchResult.specificationValue).isEqualTo(GET)
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).httpMethodMatchResult.implementationValue).isEqualTo(GET)
        }

        @Test
        fun `ignoreHttpMethodOptions must ignore endpoints with http method OPTIONS on both specification and implementation`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", OPTIONS)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                var endpointMatchResults = emptyList<MatchResultGroup>()
                override fun report(endpointMatchResults: List<MatchResultGroup>) {
                    this.endpointMatchResults = endpointMatchResults
                }
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverter,
                    HikakuConfig(
                            ignoreHttpMethodOptions = true,
                            reporter = reporter
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.endpointMatchResults).hasSize(1)
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).httpMethodMatchResult.specificationValue).isEqualTo(GET)
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).httpMethodMatchResult.implementationValue).isEqualTo(GET)
        }

        @Test
        fun `ignore specific paths`() {
            //given
            val specificationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/error", GET),
                        Endpoint("/error", HEAD),
                        Endpoint("/error", OPTIONS)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                var endpointMatchResults = emptyList<MatchResultGroup>()
                override fun report(endpointMatchResults: List<MatchResultGroup>) {
                    this.endpointMatchResults = endpointMatchResults
                }
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            ignorePaths = IGNORE_ERROR_ENDPOINTS,
                            reporter = reporter
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.endpointMatchResults).hasSize(1)
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).pathMatchResult.specificationValue).isEqualTo("/todos")
            assertThat((reporter.endpointMatchResults[0] as EndpointMatchResult).pathMatchResult.implementationValue).isEqualTo("/todos")
        }
    }

    @Nested
    inner class ReporterTests {

        @Test
        fun `Reporter has to be informed about a positive match`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos")
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                var hasBeenCalled: Boolean = false
                override fun report(endpointMatchResults: List<MatchResultGroup>) {
                    hasBeenCalled = true
                }
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverter,
                    HikakuConfig(
                            reporter = reporter
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.hasBeenCalled).isTrue()
        }
    }
}