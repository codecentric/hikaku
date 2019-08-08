package de.codecentric.hikaku

import de.codecentric.hikaku.SupportedFeatures.Feature
import de.codecentric.hikaku.converters.EndpointConverter
import de.codecentric.hikaku.endpoints.*
import de.codecentric.hikaku.endpoints.HttpMethod.*
import de.codecentric.hikaku.reporters.MatchResult
import de.codecentric.hikaku.reporters.NoOperationReporter
import de.codecentric.hikaku.reporters.Reporter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.opentest4j.AssertionFailedError
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
                    implementationDummyConverter,
                    HikakuConfig(
                            reporters = listOf(NoOperationReporter())
                    )
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
                        Endpoint("/c", GET),
                        Endpoint("/a", GET),
                        Endpoint("/b", GET)

                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/b", GET),
                        Endpoint("/c", GET),
                        Endpoint("/a", GET)
                )
                override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporters = listOf(NoOperationReporter())
                    )
            )

            //when
            hikaku.match()
        }

        @Test
        fun `same number of Endpoints, but paths don't match`() {
            //given
            val specificationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/c", GET),
                        Endpoint("/a", GET),
                        Endpoint("/b", GET)

                )
                override val supportedFeatures = SupportedFeatures()
            }

            val implementationDummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/y", GET),
                        Endpoint("/z", GET),
                        Endpoint("/a", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporters = listOf(NoOperationReporter())
                    )
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
                override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            reporters = listOf(NoOperationReporter())
                    )
            )

            //when
            hikaku.match()
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
                    implementationDummyConverter,
                    HikakuConfig(
                            reporters = listOf(NoOperationReporter())
                    )
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
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.PathParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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

                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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

                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.QueryParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                                            HeaderParameter("allow-cache")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache"),
                                            HeaderParameter("x-b3-traceid")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            HeaderParameter("allow-cache")
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
                                            HeaderParameter("x-b3-traceid")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            HeaderParameter("cache")
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
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
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                                            HeaderParameter("allow-cache", true)
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache", true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            HeaderParameter("allow-cache", false)
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
                                            HeaderParameter("allow-cache", true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            HeaderParameter("allow-cache", true)
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    headerParameters = setOf(
                                            HeaderParameter("allow-cache", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.HeaderParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class MatrixParameterNameTests {

            @Test
            fun `matrix parameter names in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("tag"),
                                            MatrixParameter("done")
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("done"),
                                            MatrixParameter("tag")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `matrix parameter names are skipped if the feature is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("done")
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
                                    matrixParameters = setOf(
                                            MatrixParameter("tag")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `matrix parameter names don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("tag")
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("done")
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }

            @Test
            fun `matrix parameter required matches if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("tag", true)
                                    )
                            )

                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("tag", true)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `matrix parameter required is skipped if option is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("tag", true)
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
                                            HeaderParameter("tag", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `matrix parameter required don't match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("allow-cache", true)
                                    )
                            )

                    )

                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    matrixParameters = setOf(
                                            MatrixParameter("allow-cache", false)
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.MatrixParameters)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                                            "text/plain"
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
                                            "text/plain",
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Produces)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            "text/plain"
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

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            "text/plain"
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

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
                                            "text/plain"
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
                                            "text/plain",
                                            "application/json"
                                    )
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Consumes)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            "text/plain"
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

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
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
                                            "text/plain"
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

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                assertFailsWith<AssertionFailedError> {
                    hikaku.match()
                }
            }
        }

        @Nested
        inner class DeprecationTests {

            @Test
            fun `deprecation info in random order match if the feature is supported by both converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = false
                            ),
                            Endpoint(
                                    path = "/todos/tags",
                                    httpMethod = GET,
                                    deprecated = true
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Deprecation)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos/tags",
                                    httpMethod = GET,
                                    deprecated = true
                            ),
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = false
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Deprecation)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `deprecation info is skipped if the feature is not supported by one of the converters`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = false
                            )
                    )

                    override val supportedFeatures = SupportedFeatures()
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = true
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Deprecation)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
                        )
                )

                //when
                hikaku.match()
            }

            @Test
            fun `deprecation info does not match`() {
                //given
                val specificationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = false
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Deprecation)
                }

                val implementationDummyConverter = object : EndpointConverter {
                    override val conversionResult: Set<Endpoint> = setOf(
                            Endpoint(
                                    path = "/todos",
                                    httpMethod = GET,
                                    deprecated = true
                            )
                    )
                    override val supportedFeatures = SupportedFeatures(Feature.Deprecation)
                }

                val hikaku = Hikaku(
                        specificationDummyConverter,
                        implementationDummyConverter,
                        HikakuConfig(
                                reporters = listOf(NoOperationReporter())
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
        fun `ignore endpoints with http method HEAD and OPTIONS on specification`() {
            //given
            val dummyConverterWithHeadAndOptions = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD),
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

            val hikaku = Hikaku(
                    dummyConverterWithHeadAndOptions,
                    dummyConverter,
                    HikakuConfig(
                            filters = listOf (
                                { endpoint -> endpoint.httpMethod == HEAD },
                                { endpoint -> endpoint.httpMethod == OPTIONS }
                            ),
                            reporters = listOf(NoOperationReporter())
                    )
            )

            //when
            hikaku.match()
        }

        @Test
        fun `ignore endpoints with http method HEAD and OPTIONS on implementation`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val dummyConverterWithHeadAndOptions = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET),
                        Endpoint("/todos", HEAD),
                        Endpoint("/todos", OPTIONS)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    dummyConverter,
                    dummyConverterWithHeadAndOptions,
                    HikakuConfig(
                            filters = listOf (
                                    { endpoint -> endpoint.httpMethod == HEAD },
                                    { endpoint -> endpoint.httpMethod == OPTIONS }
                            ),
                            reporters = listOf(NoOperationReporter())
                    )
            )

            //when
            hikaku.match()
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
                        Endpoint("/error", OPTIONS),
                        Endpoint("/actuator/health", OPTIONS)
                )
                override val supportedFeatures = SupportedFeatures()
            }

            val hikaku = Hikaku(
                    specificationDummyConverter,
                    implementationDummyConverter,
                    HikakuConfig(
                            filters = listOf (
                                    { endpoint -> endpoint.path == "/error" },
                                    { endpoint -> endpoint.path.startsWith("/actuator") }
                            ),
                            reporters = listOf(NoOperationReporter())
                    )
            )

            //when
            hikaku.match()
        }
    }

    @Nested
    inner class ReporterTests {

        @Test
        fun `MatchResult has to be passed to the Reporter`() {
            //given
            val dummyConverter = object : EndpointConverter {
                override val conversionResult: Set<Endpoint> = setOf(
                        Endpoint("/todos", GET)
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
                            reporters = listOf(reporter)
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
                        Endpoint("/todos", GET)
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
                            reporters = listOf(firstReporter, secondReporter)
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