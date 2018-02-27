package com.dexcom.froutes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object FunctionRoutes {
  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("my-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val bindingFuture = Http().bindAndHandle(BaseRoutes.routes, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
