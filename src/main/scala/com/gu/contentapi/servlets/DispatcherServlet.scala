package com.gu.contentapi.servlets

import org.scalatra.ScalatraServlet
import com.gu.contentapi.scalatra.TwirlSupport
import io.Source
import net.liftweb.json._
import net.liftweb.json.DefaultFormats
import com.weiglewilczek.slf4s.Logging
import com.gu.contentapi.model.ApiContent

class DispatcherServlet extends ScalatraServlet with TwirlSupport with Logging {
  //  val contentApiHost = "http://localhost:8700/content-api/api"
  val contentApiHost = "http://content.guardianapis.com"
  //  val contentApiHost = "http://localhost:8080"
  implicit val formats = DefaultFormats

  get("/") {

    val url = contentApiHost + "/search.json?tag=tone/minutebyminute&show-elements=all&show-fields=headline&api-key=" + params("api-key")
    logger.info("Requesting " + url)
    val responseString = Source.fromURL(url).getLines().mkString("")
    logger.info("Got response: " + responseString)
    val json = parse(responseString)
    val content = (json \ "response" \ "results").extract[List[ApiContent]]
    html.index.render(content)
  }

  get("/article/*") {
    val url = contentApiHost + "/" + params("splat") + ".json?show-fields=body&show-elements=all&api-key=" + params("api-key")
    val responseString = Source.fromURL(url).getLines().mkString("")
    logger.info("Got response: " + responseString)
    val response = (parse(responseString) \ "response" \ "content").extract[ApiContent]
    val b = (response.fields \ "body").extract[String]

    html.item.render(response, pretty(render(response.elements)))
  }

}