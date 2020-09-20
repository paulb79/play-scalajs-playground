package net.redjamjar.playscalajs.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object StaticNav {

//  final case class Props() {
//    @inline def render: VdomElement = Nav(this)
//  }
//
//  final class Backend($ : BackendScope[Props, Unit]) {
//    def render(p: Props): VdomNode =
//      <.div(<.h1("Navigation"))
//  }
//
//  val Nav = ScalaComponent
//    .builder[Props]
//    .renderBackend[Backend]
//    .build
//
//

  val NoArgsNav = ScalaComponent
    .builder[Unit]
    .renderStatic(<.h1("Navigation "))
    .build
}
