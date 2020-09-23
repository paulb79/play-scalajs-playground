package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

case class CityTemp(cityname: String, temp: Int)

object WeatherComp {

  case class Props(model: CityTemp)

  case class State(city: String, temp: Int)

  final class Backend($ : BackendScope[Props, State]) {

    def onChange(e: ReactEventFromInput) = {
      val cityValue = e.target.value
      $.modState(_.copy(city = cityValue))
    }

    def handleSearch(e: ReactEventFromInput) =
      e.preventDefaultCB >>
        $.modState(s => State(city = s.city, temp = 12))

    // todo do api call?

    def render(p: Props, s: State): VdomNode =
      <.div(<.h1("Get Weather"), weatherForm(p, s), weatherMsg(p, s))

    def weatherForm(p: Props, s: State) =
      <.div(
        <.form(
          ^.onSubmit ==> handleSearch,
          <.input(
            ^.id := "city-name",
            ^.`type` := "text",
            ^.onChange ==> onChange
          ),
          <.button("Search")
        )
      )

    def weatherMsg(p: Props, s: State) =
      <.div(<.p(s"The temperature in ${s.city} is ${s.temp} today"))

  }

  private val Component = ScalaComponent
    .builder[Props]("Weather")
    .initialStateFromProps(p => State(p.model.cityname, p.model.temp))
    .renderBackend[Backend]
    .build

  def apply(model: CityTemp) =
    Component(Props(model))
}
