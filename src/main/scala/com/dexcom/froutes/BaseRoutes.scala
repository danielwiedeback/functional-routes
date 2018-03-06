package com.dexcom.froutes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

object BaseRoutes {

  private val extractHeadersFromRequest: RequestContext => String = { (requestContext: RequestContext) =>
    val request = requestContext.request
    val headers = request.headers
    val responseString = headers.foldLeft("")((string, header) =>
      string ++ s"Header name is ${header.name} with value ${header.value}\n")
    responseString
  }

  private val routeHandlingDirective = extract(extractHeadersFromRequest)

  private val helloRoute: Route =
    path("hello") {
      get {
        routeHandlingDirective { headerString =>
          complete("Headers:\n" + headerString)
        }
      }
    }

  private val integerRoute: Route =
    path("integer" / IntNumber) { number =>
      get {
        complete(s"The number you provided was $number")
      }
    }

  def routes: Route = helloRoute ~ integerRoute
}
