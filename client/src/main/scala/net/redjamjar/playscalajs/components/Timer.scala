package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js

object Timer {

  final case class State(secondsElapsed: Long)

  final class Backend($ : BackendScope[Unit, State]) {
    var interval: js.UndefOr[js.timers.SetIntervalHandle] = js.undefined

    def tick =
      $.modState(s => State(s.secondsElapsed + 1))

    def start = Callback {
      interval = js.timers.setInterval(1000)(tick.runNow())
    }

    def clear = Callback {
      interval.foreach(js.timers.clearInterval)
      interval = js.undefined
    }

    def render(s: State): VdomNode = {
      <.div("Seconds elpased: ", s.secondsElapsed)
    }
  }

  val Timer = ScalaComponent
    .builder[Unit]
    .initialState(State(0))
    .renderBackend[Backend]
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.clear)
    .build
}
