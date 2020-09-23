package net.redjamjar.playscalajs.components

import scalacss.DevDefaults._
import scalacss.ScalaCssReact._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import net.redjamjar.playscalajs.routes.Page

//import scalacss.internal.mutable.StyleSheet

import net.redjamjar.playscalajs.models.Menu

object TopNav {

  object Style extends StyleSheet.Inline {
    import dsl._

    val navMenu = style(
      display.flex,
      alignItems.center,
      backgroundColor(c"#32746D"),
      color(c"#fff"),
      margin.`0`,
      listStyle := "none",
      fontFamily := "Roboto, sans-serif"
    )

    val menuItem = styleF.bool { selected =>
      styleS(
        padding(20.px),
        fontSize(1.5.em),
        cursor.pointer
      )
    }
  }

  case class Props(menus: Vector[Menu], selectedPage: Page, ctrl: RouterCtl[Page])

  implicit val currentPageReuse = Reusability.by_==[Page]
  implicit val propsReuse       = Reusability.by((_: Props).selectedPage)

  val component = ScalaComponent
    .builder[Props]("TopNav")
    .render_P { P =>
      <.header(
        <.nav(
          <.ul(
            Style.navMenu,
            P.menus.toTagMod { item =>
              <.li(
                ^.key := item.name,
                Style.menuItem(item.route.getClass == P.selectedPage.getClass),
                item.name,
                P.ctrl setOnClick item.route
              )
            }
          )
        )
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def apply(props: Props) = component(props)

}
