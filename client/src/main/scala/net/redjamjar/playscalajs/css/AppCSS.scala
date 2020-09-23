package net.redjamjar.playscalajs.css

import net.redjamjar.playscalajs.components.TopNav
import scalacss.DevDefaults._
import scalacss.internal.mutable.GlobalRegistry

object AppCSS {

  def load = {
    GlobalRegistry.register(GlobalStyle, TopNav.Style)
    GlobalRegistry.onRegistration(_.addToDocument())
  }
}
