package net.redjamjar.playscalajs.routes
import japgolly.scalajs.react.extra.router.RouterCtl

sealed trait Page
case object Home    extends Page
case object Todo    extends Page
case object Weather extends Page

final case class Props(router: RouterCtl[Page], currentLoc: Page)
