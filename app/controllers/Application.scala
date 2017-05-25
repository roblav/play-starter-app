package controllers

import javax.inject.Inject

import models.Widget
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}


class Application @Inject()(val messagesApi:MessagesApi) extends Controller with I18nSupport {

  def listWidgets = Action { implicit request =>
    // Pass an unpopulated form to the template
    Ok(views.html.listWidgets(Application.createWidgetForm))
  }

  def createWidget = TODO

}

object Application {

  val createWidgetForm = Form(
    mapping(
      "name" -> text,
      "price" -> number
    )(Widget.apply)(Widget.unapply)
  )

}