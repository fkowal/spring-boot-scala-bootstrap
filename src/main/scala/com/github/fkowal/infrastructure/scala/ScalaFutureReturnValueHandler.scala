package com.github.fkowal.infrastructure.scala

import org.springframework.core.MethodParameter
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.async.{DeferredResult, WebAsyncUtils}
import org.springframework.web.method.support.{AsyncHandlerMethodReturnValueHandler, ModelAndViewContainer}

import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * This handler enables support for scala.concurrent.Future as return type in Spring Controllers
  */
class ScalaFutureReturnValueHandler extends AsyncHandlerMethodReturnValueHandler {
  override def isAsyncReturnValue(returnValue: scala.Any, returnType: MethodParameter): Boolean =
    returnValue != null && returnValue.isInstanceOf[Future[_]]

  override def supportsReturnType(returnType: MethodParameter): Boolean =
    classOf[Future[_]].isAssignableFrom(returnType.getParameterType)

  override def handleReturnValue(
    returnValue: scala.Any,
    returnType: MethodParameter,
    mavContainer: ModelAndViewContainer,
    webRequest: NativeWebRequest
  ): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    if (returnValue == null) {
      mavContainer.setRequestHandled(true)
      return
    }

    val deferredResult = new DeferredResult[scala.Any]()
    WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(deferredResult, mavContainer)

    val future: Future[_] = returnValue.asInstanceOf[Future[_]]
    future.onComplete {
      case Success(result) => deferredResult.setResult(result)
      case Failure(ex) => deferredResult.setErrorResult(ex)
    }
  }
}
