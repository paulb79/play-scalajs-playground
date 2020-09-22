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

    // todo define stop
    def stop(s: StateAccessPure[Long]): CallbackTo[Unit] = {
      clear
      reset(s)
    }

    def clear = Callback {
      interval.foreach(js.timers.clearInterval)
      interval = js.undefined
    }

    def reset(s: StateAccessPure[Long]): Callback = {
      s.modState(_ => 0)
    }

    def render(s: State): VdomNode = {
      val f =
        $.zoomState(_.secondsElapsed)(value => _.copy(secondsElapsed = value))
      <.div(
        "Seconds elpased: ",
        s.secondsElapsed,
        <.button("Reset", ^.onClick --> reset(f)),
        <.button("Stop", ^.onClick --> stop(f))
      )
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
