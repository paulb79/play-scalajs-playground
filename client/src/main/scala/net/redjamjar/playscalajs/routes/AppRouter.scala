package net.redjamjar.playscalajs.routes

import japgolly.scalajs.react.extra.router._
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.components.{BasicBackend, CityTemp, StaticNav, Timer, TodoList, TopNav, WeatherComp, Welcome}
import net.redjamjar.playscalajs.models.Menu
import net.redjamjar.playscalajs.pages.{HomePage, TodoPage, WeatherPage}

object AppRouter {

  val routerConfig = RouterConfigDsl[Page]
    .buildConfig { dsl =>
      import dsl._
      (emptyRule
        | staticRoute(root, Home) ~> render(HomePage())
        | staticRoute("#weather", Weather) ~> render(WeatherPage())
        | staticRoute("#todo", Todo) ~> render(TodoPage()))
        .notFound(redirectToPage(Home)(SetRouteVia.HistoryReplace))
    }
    .renderWith(layout)

  val mainMenu = Vector(
    Menu("Home", Home),
    Menu("Todo", Todo),
    Menu("Weather", Weather)
  )

  def layout(c: RouterCtl[Page], r: Resolution[Page]) =
    <.div(
      TopNav(TopNav.Props(mainMenu, r.page, c)),
      <.div(^.cls := "container", r.render())
    )

  val baseUrl = BaseUrl.fromWindowOrigin / ""
  val router  = Router(baseUrl, routerConfig.logToConsole)

}
