package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.routes.{Home, Props, Weather}

object StaticNav {

  final class Backend($ : BackendScope[Props, Unit]) {
    def render(p: Props): VdomNode =
      <.div(
        <.nav(
          <.div(<.div("Playground learning app")),
          <.div(
            <.ul(
              <.li(p.router.link(Home)("Home")),
              <.li(p.router.link(Weather)("Weather"))
            )
          )
        )
      )
  }

  val Nav = ScalaComponent
    .builder[Props]("Static Navigation")
    .renderBackend[Backend]
    .build

  def apply(props: Props) = Nav(props).vdomElement

//  val NoArgsNav = ScalaComponent
//    .builder[Unit]
//    .renderStatic(<.h1("Navigation "))
//    .build
}
