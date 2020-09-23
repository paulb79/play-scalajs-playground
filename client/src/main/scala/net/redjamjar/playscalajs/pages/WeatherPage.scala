package net.redjamjar.playscalajs.pages

import scalacss.DevDefaults._
import scalacss.ScalaCssReact._
import scalacss.internal.mutable.StyleSheet
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.components.{CityTemp, WeatherComp}
import net.redjamjar.playscalajs.components.WeatherComp.{Component, Props}

object WeatherPage {

  object Style extends StyleSheet.Inline {
    import dsl._

    val content = style(textAlign.left, fontSize(14.px))
  }

  private val component = ScalaComponent.builder
    .static("Weather Page")(
      <.div(
        Style.content,
        WeatherComp(CityTemp("London", 12))
      )
    )
    .build

  def apply() = component()
}
