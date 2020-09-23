package net.redjamjar.playscalajs

import net.redjamjar.playscalajs.css.AppCSS
import net.redjamjar.playscalajs.routes.AppRouter
import org.scalajs.dom

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

object SPAMain {

  @JSExportTopLevel("PlayScalaJSReactBoilerplateApp")
  protected def getInstance(): this.type = this
  @JSExport
  def main(args: Array[String]): Unit = {
    println("Application Starting...")
    val container = dom.document.getElementById("root")
    AppCSS.load
    AppRouter.router().renderIntoDOM(container)
  }
}
