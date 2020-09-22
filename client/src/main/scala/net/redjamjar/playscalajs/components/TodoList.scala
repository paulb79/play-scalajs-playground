package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.vdom.html_<^._

object TodoList {
//
//  final case class Props(state: StateSnapshot[State]) {
//    @inline def render: VdomElement = TodoApp()
//  }

  val TodoListItem = ScalaFnComponent[List[String]] { props =>
    def createItem(itemText: String) = <.li(itemText)

    <.ul(props map createItem: _*)
  }

  final case class State(items: List[String], text: String)

  object State {
    def init: State =
      State(List("Read"), "Wibble")

  }

  final class Backend($ : BackendScope[Unit, State]) {
    def onChange(e: ReactEventFromInput) = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventFromInput) = {
      e.preventDefaultCB >>
        $.modState(s => State(s.items :+ s.text, ""))
    }

    def render(state: State): VdomNode = {
      <.div(
        <.h3("Todo"),
        TodoListItem(state.items),
        <.form(
          ^.onSubmit ==> handleSubmit,
          <.input(^.onChange ==> onChange, ^.value := state.text),
          <.button("Add ", state.items.length + 1)
        )
      )
    }
  }

  val TodoApp = ScalaComponent
    .builder[Unit]("todo")
    .initialState(State(Nil, ""))
    .renderBackend[Backend]
    .build
}
