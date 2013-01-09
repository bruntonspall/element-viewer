package com.gu.contentapi.model

import com.gu.contentapi.util.Annotations._
import com.googlecode.objectify.annotation.Entity
import cc.spray.json.{ JsValue, DefaultJsonProtocol }
import net.liftweb.json._

case class ApiContent(id: String, webTitle: String, fields: JObject, elements: JValue)

@Entity
case class User(
    @Id var email: String,
    @Index var password: String,
    var name: String) {

  // Only for Objectify creation
  private def this() { this(null, null, null) }

}
