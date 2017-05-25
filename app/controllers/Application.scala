package controllers

import play.api.mvc.{Action, Controller}


class Application extends Controller {

  def listWidgets = Action {
    // Pass an unpopulated form to the template
    Ok(views.html.listWidgets())
  }

}
