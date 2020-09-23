package net.redjamjar.playscalajs.css

import scalacss.DevDefaults._

object GlobalStyle extends StyleSheet.Inline {

  import dsl._

  style(unsafeRoot("body")(margin(0.px), padding(0.px), fontSize(14.px), fontFamily := "Roboto, sans-serif"))

}
