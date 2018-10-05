// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/jajubina/Documents/RestFulWebService/restfulwebservice/conf/routes
// @DATE:Thu Oct 04 15:12:33 EDT 2018

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def confirmFail: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.confirmFail",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "debug/confirm_fail"})
        }
      """
    )
  
    // @LINE:18
    def getSellOffer: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getSellOffer",
      """
        function(offerid0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "selloffers/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("offerid", offerid0))})
        }
      """
    )
  
    // @LINE:20
    def buy: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.buy",
      """
        function(maxrate0,amount1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "buy/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("maxrate", maxrate0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("amount", amount1))})
        }
      """
    )
  
    // @LINE:12
    def getTransactions: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getTransactions",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transactions"})
        }
      """
    )
  
    // @LINE:10
    def getBalance: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getBalance",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getbalance"})
        }
      """
    )
  
    // @LINE:24
    def confirmNoResponse: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.confirmNoResponse",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "debug/confirm_no_response"})
        }
      """
    )
  
    // @LINE:14
    def getTransaction: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getTransaction",
      """
        function(transactionID0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "transactions/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("transactionID", transactionID0))})
        }
      """
    )
  
    // @LINE:26
    def confirmReset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.confirmReset",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "debug/reset"})
        }
      """
    )
  
    // @LINE:8
    def addBalanceUSD: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.addBalanceUSD",
      """
        function(amount0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addbalance/usd/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("amount", amount0))})
        }
      """
    )
  
    // @LINE:16
    def getSellOffers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.getSellOffers",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "selloffers"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:28
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:28
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
