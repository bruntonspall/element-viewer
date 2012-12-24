package com.gu.contentapi.model

import com.gu.contentapi.util.Annotations._
import com.googlecode.objectify.annotation.Entity
import cc.spray.json.DefaultJsonProtocol

@Entity
case class User(
    @Id var email: String,
    @Index var password: String,
    var name: String) {

  // Only for Objectify creation
  private def this() { this(null, null, null) }

}

case class Content(id: String, webTitle: String)
case class Response(status: String, total: Long, results: List[Content])
case class ResponseWrapper(response: Response)

object ContentJsonProtocol extends DefaultJsonProtocol {
  implicit val contentFormat = jsonFormat2(Content)
  implicit val responseFormat = jsonFormat3(Response)
  implicit val wrapperFormat = jsonFormat1(ResponseWrapper)
}