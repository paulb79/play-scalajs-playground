package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object BasicBackend {

  type State = Vector[String]

  final class Backend($ : BackendScope[Unit, State]) {
    def render(s: State): VdomNode =
      <.div(<.div(s.length, " items found: "), <.ol(s.toTagMod(i => <.li(i))))
  }

  val Component = ScalaComponent
    .builder[Unit]
    .initialState(Vector("hello", "World"))
    .renderBackend[Backend]
    .build
}
