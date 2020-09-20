package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.SPAMain.{Home, Weather, Page}

object StaticNav {

  final case class Props(router: RouterCtl[Page], currentLoc: Page)

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
    .builder[Props]("MainMenu")
    .renderBackend[Backend]
    .build

//  val NoArgsNav = ScalaComponent
//    .builder[Unit]
//    .renderStatic(<.h1("Navigation "))
//    .build
}
