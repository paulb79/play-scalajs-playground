package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

// Example component with props (string)
object Welcome {

  val Welcome = ScalaComponent
    .builder[String]
    .render_P(name => <.div("Hello ", name))
    .build
}
