package net.redjamjar.playscalajs.controllers

import javax.inject._

import net.redjamjar.playscalajs.shared.SharedMessages
import play.api.mvc._

@Singleton
class Application @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }

}
