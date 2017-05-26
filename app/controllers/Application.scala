package controllers

import javax.inject.Inject

import models.Widget
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

import scala.collection.mutable.ArrayBuffer


class Application @Inject()(val messagesApi:MessagesApi) extends Controller with I18nSupport {

  private val widgets = ArrayBuffer(
    Widget("Widget 1", 123),
    Widget("Widget 2", 456),
    Widget("Widget 3", 789)
  )

  def listWidgets = Action { implicit request =>
    // Pass an unpopulated form to the template
    Ok(views.html.listWidgets(widgets.toSeq, Application.createWidgetForm))
  }

  def createWidget = Action { implicit request =>
    val formValidationResult = Application.createWidgetForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      // This is the bad case, where the form had validation errors.
      // Let's show the user the form again, with the errors highlighted.
      // Note how we pass the form with errors to the template.
      BadRequest(views.html.listWidgets(widgets.toSeq, formWithErrors))
    }, { widget =>
      // This is the good case, where the form was successfully parsed as a Widget.
      widgets.append(widget)
      Redirect(routes.Application.listWidgets)
    })
  }

}

object Application {

  val createWidgetForm = Form(
    mapping(
      "name" -> text,
      "price" -> number
    )(Widget.apply)(Widget.unapply)
  )

}