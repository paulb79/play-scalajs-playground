package net.redjamjar.playscalajs.pages

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.components.{BasicBackend, Timer, Welcome}
import scalacss.DevDefaults._
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.StyleSheet

object HomePage {

  object Style extends StyleSheet.Inline {
    import dsl._

    val content = style(textAlign.left, fontSize(14.px))
  }

  private val component = ScalaComponent.builder
    .static("Todo Page")(
      <.div(
        Style.content,
        <.h1("Hello World"),
        Welcome.Welcome("Paul"),
        Welcome.Welcome("Sarah"),
        BasicBackend.Component(),
        Timer.Timer()
      )
    )
    .build

  def apply() = component()
}
