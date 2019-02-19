package de.codecentric.hikaku

import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converter.EndpointConverter
import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HeaderParameter
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.endpoints.PathParameter
import de.codecentric.hikaku.endpoints.QueryParameter
import de.codecentric.hikaku.reporter.MatchResult
import de.codecentric.hikaku.reporter.Reporter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError
import java.lang.IllegalStateException
import kotlin.test.assertFailsWith

class HikakuTest {

    @Nested
    inner class EndpointBasicsTests {

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
                    implementationDummyConverter
            )

            //when
            assertFailsWith<AssertionError> {
                hikaku.match()
            }
        }

        @Test
        fun `paths in random order match`() {
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
                lateinit var matchResult: MatchResult

                override fun report(endpointMatchResult: MatchResult) {
                    this.matchResult = endpointMatchResult
                }
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.matchResult.notExpected).isEmpty()
            assertThat(reporter.matchResult.notFound).isEmpty()
        }

        @Test
        fun `same number of Endpoints, but paths don't match`() {
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
                        Endpoint("/y"),
                        Endpoint("/z"),
                        Endpoint("/a")
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter
            )

            //when
            assertFailsWith<AssertionFailedError> {
                hikaku.match()
            }
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
                lateinit var matchResult: MatchResult

                override fun report(endpointMatchResult: MatchResult) {
                    this.matchResult = endpointMatchResult
                }
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.matchResult.notFound).isEmpty()
            assertThat(reporter.matchResult.notExpected).isEmpty()
        }

        @Test
        fun `same number of Endpoints, but http methods don't match`() {
            //given
            val specificationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", PUT),
                        Endpoint("/todos", DELETE),
                        Endpoint("/todos", GET)

                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", POST),
                        Endpoint("/todos", HEAD)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter
            )

            //when
            assertFailsWith<AssertionFailedError> {
                hikaku.match()
            }
        }
    }

    @Nested
    inner class FeatureTests {
        @Nested
        inner class PathParameterTests {

            @Test
            fun `path parameter in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{organizationId}/{accountId}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("accountId"),
                                            PathParameter("organizationId")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{organizationId}/{accountId}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("organizationId"),
                                            PathParameter("accountId")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `path parameter are skipped if the feature is not supported by one of the converters`() {
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
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `path parameter don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/{accountId}",
                                    httpMethod = GET,
                                    pathParameters = setOf(
                                            PathParameter("accountId")
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
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class QueryParameterNameTests {

            @Test
            fun `query parameter names in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("filter"),
                                            QueryParameter("tag"),
                                            QueryParameter("query")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("query"),
                                            QueryParameter("filter"),
                                            QueryParameter("tag")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `query parameter names are skipped if the feature is not supported by one of the converters`() {
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `query parameter names don't match`() {
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

                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }

            @Test
            fun `query parameter required matches if the feature is supported by both converters`() {
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("filter", true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `query parameter required is skipped if option is not supported by one of the converters`() {
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
                                            QueryParameter("filter", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `query parameter required don't match`() {
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

                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    queryParameters = setOf(
                                            QueryParameter("filter", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class HeaderParameterNameTests {

            @Test
            fun `header parameter names in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("x-b3-traceid"),
                                            HeaderParameter("use-cache")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache"),
                                            HeaderParameter("x-b3-traceid")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `header parameter names are skipped if the feature is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache")
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
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `header parameter names don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache")
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }

            @Test
            fun `header parameter required matches if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache", true)
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache", true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `header parameter required is skipped if option is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache")
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
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `header parameter required don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache", true)
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("use-cache", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameter)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class ProducesTests {

            @Test
            fun `media types in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(
                                            "application/json",
                                            "plain/text"
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(
                                            "plain/text",
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `produces is skipped if the feature is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(
                                            "application/xml",
                                            "plain/text"
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
                                    produces = setOf(
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `media types don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(
                                            "application/xml",
                                            "plain/text"
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    produces = setOf(
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class ConsumesTests {

            @Test
            fun `media types in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    consumes = setOf(
                                            "application/json",
                                            "plain/text"
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    consumes = setOf(
                                            "plain/text",
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `produces is skipped if the feature is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    consumes = setOf(
                                            "application/xml",
                                            "plain/text"
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
                                    consumes = setOf(
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                hikaku.match()

                //then
                assertThat(reporter.matchResult.notFound).isEmpty()
                assertThat(reporter.matchResult.notExpected).isEmpty()
            }

            @Test
            fun `media types don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    consumes = setOf(
                                            "application/xml",
                                            "plain/text"
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    consumes = setOf(
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val reporter = object : Reporter {
                    lateinit var matchResult: MatchResult

                    override fun report(endpointMatchResult: MatchResult) {
                        this.matchResult = endpointMatchResult
                    }
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporter = listOf(reporter)
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }
    }

    @Nested
    inner class ConfigTests {

        @Test
        fun `ignoreHttpMethodHead must ignore endpoints with http method HEAD on both specification and implementation`() {
            //given
            val dummyConverterWithHead = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                lateinit var matchResult: MatchResult

                override fun report(endpointMatchResult: MatchResult) {
                    this.matchResult = endpointMatchResult
                }
            }

            val hikaku = Hikaku(
                    dummyConverterWithHead,
                    dummyConverter,
                    HikakuConfig(
                            ignoreHttpMethodHead = true,
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.matchResult.notFound).isEmpty()
            assertThat(reporter.matchResult.notExpected).isEmpty()
        }

        @Test
        fun `ignoreHttpMethodOptions must ignore endpoints with http method OPTIONS on both specification and implementation`() {
            //given
            val dummyConverterWithOptions = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", OPTIONS)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val reporter = object : Reporter {
                lateinit var matchResult: MatchResult

                override fun report(endpointMatchResult: MatchResult) {
                    this.matchResult = endpointMatchResult
                }
            }

            val hikaku = Hikaku(
                    dummyConverterWithOptions,
                    dummyConverter,
                    HikakuConfig(
                            ignoreHttpMethodOptions = true,
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.matchResult.notFound).isEmpty()
            assertThat(reporter.matchResult.notExpected).isEmpty()
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
                lateinit var matchResult: MatchResult

                override fun report(endpointMatchResult: MatchResult) {
                    this.matchResult = endpointMatchResult
                }
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            ignorePaths = setOf("/error"),
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.matchResult.notFound).isEmpty()
            assertThat(reporter.matchResult.notExpected).isEmpty()
        }
    }

    @Nested
    inner class ReporterTests {

        @Test
        fun `MatchResult has to be passed to the Reporter`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos")
                )
                override val supportedFeatures = SupportedFeatures()
            }


            val reporter = object : Reporter {
                var hasBeenCalled: Boolean = false

                override fun report(endpointMatchResult: MatchResult) {
                    hasBeenCalled = true
                }
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverter,
                    HikakuConfig(
                            reporter = listOf(reporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(reporter.hasBeenCalled).isTrue()
        }

        @Test
        fun `MatchResult can be passed to multiple Reporter`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos")
                )
                override val supportedFeatures = SupportedFeatures()
            }


            val firstReporter = object : Reporter {
                var hasBeenCalled: Boolean = false

                override fun report(endpointMatchResult: MatchResult) {
                    hasBeenCalled = true
                }
            }

            val secondReporter = object : Reporter {
                var hasBeenCalled: Boolean = false

                override fun report(endpointMatchResult: MatchResult) {
                    hasBeenCalled = true
                }
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverter,
                    HikakuConfig(
                            reporter = listOf(firstReporter, secondReporter)
                    )
            )

            //when
            hikaku.match()

            //then
            assertThat(firstReporter.hasBeenCalled).isTrue()
            assertThat(secondReporter.hasBeenCalled).isTrue()
        }
    }
}