package com.dexcom.froutes

import akka.http.scaladsl.model.{HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._

import scala.concurrent.Future

object BaseRoutes {
  import scala.concurrent.ExecutionContext.Implicits.global

  private val routeHandlingFunction: Route = { requestContext =>
    val request = requestContext.request
    val headers = request.headers
    val responseString = headers.foldLeft("")((string, header) =>
      string ++ s"Header name is ${header.name} with value ${header.value}\n")
    val response = HttpResponse(entity = HttpEntity(responseString))
    Future(RouteResult.Complete(response))
  }

  private val extractHeadersFromRequest: RequestContext => String = { (requestContext: RequestContext) =>
    val request = requestContext.request
    val headers = request.headers
    val responseString = headers.foldLeft("")((string, header) =>
      string ++ s"Header name is ${header.name} with value ${header.value}\n")
    responseString
  }

  private val routeHandlingDirective = extract(extractHeadersFromRequest)

  val routes: Route =
    path("hello") {
      get {
        routeHandlingDirective { headerString =>
          complete("Lol I ignored the directive's result")
        }
      }
    }
}
