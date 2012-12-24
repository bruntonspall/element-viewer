package com.gu.contentapi.servlets

import org.scalatra.ScalatraServlet
import com.gu.contentapi.scalatra.TwirlSupport
import cc.spray.json.JsonReader
import cc.spray.json._
import io.Source
import com.gu.contentapi.model.{ ResponseWrapper, Response }
import com.gu.contentapi.model.ContentJsonProtocol._
import com.weiglewilczek.slf4s.Logging

class DispatcherServlet extends ScalatraServlet with TwirlSupport with Logging {

  get("/") {
    //    val url = "http://content.guardianapis.com/search.json?tag=tone/minutebyminute"
    val url = "http://localhost:8700/content-api/api/search.json?tag=tone/minutebyminute"
    val responseString = Source.fromURL(url).getLines().mkString("")
    logger.info("Got: " + responseString)
    val response = responseString.asJson.convertTo[ResponseWrapper]
    html.index.render(response.response)
  }

}